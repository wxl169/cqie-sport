package com.ruoyi.client.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ruoyi.client.domain.entity.Referee;
import com.ruoyi.client.domain.entity.Student;
import com.ruoyi.client.domain.entity.User;
import com.ruoyi.client.mapper.RefereeMapper;
import com.ruoyi.client.mapper.StudentMapper;
import com.ruoyi.client.mapper.UserMapper;
import com.ruoyi.client.service.UserService;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.R;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 16956
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private RefereeMapper refereeMapper;
    @Resource
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public R getUserByEmailAndPassword(String email, String password) {
        if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
            return R.fail("请输入邮箱或密码");
        }
        User user = userMapper.selectUserByEmail(email);
        if (BCrypt.checkpw(password,user.getPassword())){
//        if (user != null) {
            //如果登录验证成功，则生成令牌token
            //还需传入用户性别，若用户为学生，则需要根据学生id去学生表中查询-----
            //因为之后在前端需要将学生性别传入，从而在报名界面可用根据性别和项目进行校验
            if (UserConstants.USER_TYPE_STUDENT.equals(user.getType())) {
                //是学生
                if (user.getTypeId() == null) {
                    return R.fail("系统内部错误");
                }
                Integer studentId = user.getTypeId();
                LambdaQueryWrapper<Student> studentQueryWrapper = new LambdaQueryWrapper<>();
                studentQueryWrapper.eq(Student::getStudentId, studentId);
                Student student = studentMapper.selectOne(studentQueryWrapper);
                //得到此学生用户对应学生表中的学生实体
                if (student == null) {
                    return R.fail("请注册账号");
                }
                user.setGender(student.getGender());
            }
            //将登录数据存入redis
            String oldToke = (String) redisTemplate.opsForValue().get(email);
            if (oldToke != null) {
                redisTemplate.delete(oldToke);
                redisTemplate.delete(email);
            }

            String token = SecureUtil.md5(email + System.currentTimeMillis());
            redisTemplate.opsForValue().set(token, email, 60 * 60, TimeUnit.SECONDS);
            redisTemplate.opsForValue().set(email, token, 60 * 60, TimeUnit.SECONDS);
            //验证成功
            return R.ok(token, user);
        } else {
            //邮箱或密码错误
            return R.fail("请注册账号");
        }
    }


    @Override
    public R addUserToRegister(Map<String, String> info) {
        if (StringUtils.isBlank(info.get("type"))) {
            return R.fail("请选择角色");
        }
        String type = info.get("type");

        if (StringUtils.isBlank(info.get("password"))) {
            return R.fail("设置密码");
        }
        String password = info.get("password");
        if (StringUtils.isBlank(info.get("username"))) {
            return R.fail("设置用户名");
        }
        String username = info.get("username");
        if (StringUtils.isBlank(info.get("email"))) {
            return R.fail("设置邮箱");
        }
        String email = info.get("email");
        //判断当前邮箱是否注册
        User userByEmail = userMapper.selectUserByEmail(email);
        if (userByEmail != null) {
            return R.fail("该邮箱已被注册");
        }

        User user = new User();
        user.setType(type);
        user.setUsername(username);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        user.setEmail(email);
        user.setImg("default.png");

        //若是学生注册
        if (UserConstants.USER_TYPE_STUDENT.equals(type)) {
            //首先根据学号得到学生在学生表中的主键id，从而插入用户表中的type_id字段
            if (StringUtils.isBlank(info.get("number"))) {
                return R.fail("请输入学号");
            }
            String studentNumber = info.get("number");
            LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Student::getStudentNumber, studentNumber);
            Student student = studentMapper.selectOne(queryWrapper);
            //如果学号正确
            if (student != null) {
                //判断该学生是否注册账号
                User userStudent = userMapper.selectUserByType(student.getStudentId(),UserConstants.USER_TYPE_STUDENT);
                if (userStudent != null) {
                    return R.fail("该学生已创建账号");
                }
                //则构建此学生用户，并插入用户表
                user.setTypeId(student.getStudentId());
            }else{
                return R.fail("您不是本校学生");
            }
        } else if (UserConstants.USER_TYPE_REFEREE.equals(type)) {
            //若是裁判员
            //首先根据工号得到裁判员在裁判员表中的主键id，从而插入用户表中的type_id字段
            if (StringUtils.isBlank(info.get("number"))) {
                return R.fail("请填写工号");
            }
            String workNumber = info.get("number");
            LambdaQueryWrapper<Referee> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Referee::getNumber, workNumber);
            Referee referee = refereeMapper.selectOne(queryWrapper);
            //如果工号正确
            if (referee != null) {
                //判断该学生是否注册账号
                User userStudent = userMapper.selectUserByType(referee.getRefereeId(),UserConstants.USER_TYPE_REFEREE);
                if (userStudent != null) {
                    return R.fail("该裁判员已创建账号");
                }
                //则构建此裁判员用户，并插入用户表
                user.setTypeId(referee.getRefereeId());
            }else {
                return R.fail("您不是裁判员");
            }
        } else {
            // 如果不是学生、不是裁判
            //则构建此非学生用户，并插入用户表
            user.setRealname(info.get("realname"));
            user.setGender(info.get("gender"));
            user.setIdnumber(info.get("idnumber"));
            user.setPhoneNumber(info.get("phoneNumber"));
            String birthday = info.get("birthday");
            birthday = birthday.substring(0, 10);
            user.setBirthday(LocalDate.parse(birthday));
        }
        int i = userMapper.insert(user);
        if (i > 0) {
            return R.ok("success");
        } else {
            return R.fail();
        }
    }

    @Override
    public R logout(String token) {
        //获取当前登录信息
        System.out.println("token:" + token);
        if (token == null) {
            return R.fail("请先登录账号");
        }
        String value = (String) redisTemplate.opsForValue().get(token);
        if (value != null) {
            System.out.println("token:" + value);
            redisTemplate.delete(token);
            redisTemplate.delete(value);
            return R.ok("退出成功");
        }
        return R.fail("退出失败");
    }


    @Override
    public boolean judgeLogin(String token) {
        //如果token为空则该用户没有登录
        if (token == null){
            return false;
        }
        String value = (String) redisTemplate.opsForValue().get(token);
        //该账号已登录
        if(value !=null){
            return true;
        }else {
            return false;
        }
    }

}
