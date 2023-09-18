package com.ruoyi.client.service.impl;

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
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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


    /**
     * 登录验证
     * 根据前端传来的 邮箱 和 密码 来确定是否存在用户
     *
     * @param email
     * @param password
     * @return
     */
    @Override
    public R getUserByEmailAndPassword(String email, String password) {
        if (StringUtils.isBlank(email) || StringUtils.isBlank(password)){
            return R.fail("请输入邮箱或密码");
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail,email)
                .eq(User::getPassword,password);
        User user = userMapper.selectOne(queryWrapper);
        R resultVO = null;
        if (user != null) {
            //如果登录验证成功，则生成令牌token
            //还需传入用户性别，若用户为学生，则需要根据学生id去学生表中查询-----
            //因为之后在前端需要将学生性别传入，从而在报名界面可用根据性别和项目进行校验
            if (UserConstants.USER_TYPE_STUDENT.equals(user.getType())) {
                //是学生
                if (user.getTypeId() == null){
                    return R.fail("系统内部错误");
                }
                Integer studentId = user.getTypeId();
                LambdaQueryWrapper<Student> studentQueryWrapper = new LambdaQueryWrapper<>();
                studentQueryWrapper.eq(Student::getStudentId,studentId);
                Student student = studentMapper.selectOne(studentQueryWrapper);
                //得到此学生用户对应学生表中的学生实体
                if (student == null){
                    return R.fail("请注册账号");
                }
                user.setGender(student.getGender());
            }

            JwtBuilder builder = Jwts.builder();
            HashMap<String, Object> map = new HashMap<>(16);
            map.put("key1", "value1");
            map.put("key2", "value2");
            //token中携带的数据
            String token = builder.setSubject(email)
                    //token的生成时间
                    .setIssuedAt(new Date())
                    //token id 设置为用户 id
                    .setId(user.getUserId() + "")
                    //map中存放用户的角色权限信息
                    .setClaims(map)
                    // 设置token过期时间
                    .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                    //设置加密方式和加密密码
                    .signWith(SignatureAlgorithm.HS256, "zlc777")
                    .compact();

            //验证成功
            return R.ok(token, user);
        } else {
            //邮箱或密码错误
            return R.fail("请注册账号");
        }
    }


    @Override
    public R addUserToRegister(Map<String, String> info) {
        if (StringUtils.isBlank(info.get("type"))){
            return R.fail("请选择角色");
        }
        String type = info.get("type");
        //若是学生注册
        if (UserConstants.USER_TYPE_STUDENT.equals(type)) {
            //首先根据学号得到学生在学生表中的主键id，从而插入用户表中的type_id字段
            if (StringUtils.isBlank(info.get("number"))){
                return R.fail("请输入学号");
            }
            String studentNumber = info.get("number");
            LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Student::getStudentNumber,studentNumber);
            Student student = studentMapper.selectOne(queryWrapper);
            //如果学号正确
            if (student != null) {
                //则构建此学生用户，并插入用户表

                User user = new User();
                user.setType(UserConstants.USER_TYPE_STUDENT);
                user.setTypeId(student.getStudentId());
                user.setUsername(info.get("username"));
                user.setPassword(info.get("password"));
                user.setEmail(info.get("email"));
                user.setImg("default.png");
                int i = userMapper.insert(user);
                if (i > 0) {
                    return R.ok("success");
                } else {
                    return R.fail();
                }
            }
        } else if (UserConstants.USER_TYPE_REFEREE.equals(type)) {
            //若是裁判员
            //首先根据工号得到裁判员在裁判员表中的主键id，从而插入用户表中的type_id字段
            if (StringUtils.isBlank(info.get("number"))){
                return R.fail("请填写工号");
            }
            String workNumber = info.get("number");
            LambdaQueryWrapper<Referee> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Referee::getNumber,workNumber);
            Referee referee = refereeMapper.selectOne(queryWrapper);
            //如果工号正确
            if (referee != null) {
                //则构建此裁判员用户，并插入用户表
                User user = new User();
                user.setType(UserConstants.USER_TYPE_REFEREE);
                user.setTypeId(referee.getRefereeId());
                user.setUsername(info.get("username"));
                user.setPassword(info.get("password"));
                user.setEmail(info.get("email"));
                user.setImg("default.png");
                int i = userMapper.insert(user);
                if (i > 0) {
                    return R.ok("success");
                } else {
                    return R.fail();
                }
            }

        } else {
            // 如果不是学生、不是裁判
            //则构建此非学生用户，并插入用户表
            User user = new User();
            user.setType(info.get("type"));
            user.setUsername(info.get("username"));
            user.setPassword(info.get("password"));
            user.setEmail(info.get("email"));
            user.setRealname(info.get("realname"));
            user.setGender(info.get("gender"));
            user.setIdnumber(info.get("idnumber"));
            user.setPhoneNumber(info.get("phoneNumber"));
            user.setImg("default.png");
            String birthday = info.get("birthday");

            birthday = birthday.substring(0, 10);
            user.setBirthday(LocalDate.parse(birthday));

            int i = userMapper.insert(user);
            if (i > 0) {
                return R.ok("success");
            } else {
                return R.fail();
            }
        }
        return R.fail();
    }

}
