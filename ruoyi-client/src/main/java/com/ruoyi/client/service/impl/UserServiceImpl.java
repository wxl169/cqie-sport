package com.ruoyi.client.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.client.domain.dto.UserInfoDTO;
import com.ruoyi.client.domain.dto.UserLoginDTO;
import com.ruoyi.client.domain.dto.UserUpdateDTO;
import com.ruoyi.client.domain.entity.Referee;
import com.ruoyi.client.domain.entity.Student;
import com.ruoyi.client.domain.entity.User;
import com.ruoyi.client.domain.vo.ResStatus;
import com.ruoyi.client.domain.vo.ResultVO;
import com.ruoyi.client.domain.vo.UserInfoVO;
import com.ruoyi.client.domain.vo.UserLoginVO;
import com.ruoyi.client.mapper.RefereeMapper;
import com.ruoyi.client.mapper.StudentMapper;
import com.ruoyi.client.mapper.UserMapper;
import com.ruoyi.client.service.UserService;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.BeanCopyUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 16956
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{

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
        if (user == null){
            return R.fail("请注册账户");
        }
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
                Long studentId = user.getTypeId();
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
        user.setPassword(BCrypt.hashpw(password));
        user.setEmail(email);
        user.setImg("default.png");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
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
                //判断该裁判員是否注册账号
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
        return value != null;
    }

    @Override
    public R getUserInfo(UserInfoDTO userInfoDTO) {
        //判断传入数据是否为空
        if (!judgeUserInfo(userInfoDTO.getUserId(),userInfoDTO.getType())){
            return R.fail("请求参数错误");
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        UserInfoVO copy ;
        if (UserConstants.USER_TYPE_STUDENT.equals(userInfoDTO.getType()) && userInfoDTO.getTypeId() != null){
            //如果查找本校学生的信息，需要查学生表信息
            copy = userMapper.selectStudentInfo(userInfoDTO.getUserId());
        }else{
            //如果不是学生查用户表的信息
            queryWrapper.eq(User::getUserId,userInfoDTO.getUserId());
            User user = userMapper.selectOne(queryWrapper);
            copy = BeanCopyUtils.copy(user, UserInfoVO.class);
        }
        return R.ok(copy);
    }

    @Override
    public R updateUserInfo(UserUpdateDTO userUpdateDTO) {
        if (!judgeUserInfo(userUpdateDTO.getUserId(),userUpdateDTO.getType())){
            return R.fail("请求参数错误");
        }
        if (userUpdateDTO.getValue() == null || userUpdateDTO.getChangeType() == null){
            return R.fail("请求参数错误");
        }
        //判断如果是本校学生，则修改学生表信息
        boolean judge = false;
        //判断选择的是否为系统提供的修改字段
        boolean pd  = false;
        if (UserConstants.USER_TYPE_STUDENT.equals(userUpdateDTO.getType()) && userUpdateDTO.getTypeId() != null && "phonenumber".equals(userUpdateDTO.getChangeType())){
            //如果是修改学生手机号的信息，需要修改学生表的信息
            judge = studentMapper.updateSutdentPhone(userUpdateDTO.getTypeId(),userUpdateDTO.getValue());
        }else {
            //如果是其他信息，则修改信息表
            LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            //更改用户名
        if ("username".equals(userUpdateDTO.getChangeType())){
            updateWrapper.set(User::getUsername,userUpdateDTO.getValue());
            pd = true;
        }
        //更改邮箱
        if ("email".equals(userUpdateDTO.getChangeType())){
            queryWrapper.eq(User::getEmail,userUpdateDTO.getValue());
            User user = this.getOne(queryWrapper);
            if (user != null){
                return R.fail("该邮箱已注册");
            }
            updateWrapper.set(User::getEmail,userUpdateDTO.getValue());
            pd = true;
        }
        //更改密码
        if ("password".equals(userUpdateDTO.getChangeType())){
            updateWrapper.set(User::getPassword,userUpdateDTO.getValue());
            pd = true;
        }
        //更改手机号
        if ("phonenumber".equals(userUpdateDTO.getChangeType())){
            queryWrapper.eq(User::getPhoneNumber,userUpdateDTO.getValue());
            User user = this.getOne(queryWrapper);
            if (user != null){
                return R.fail("该手机号已注册");
            }
            updateWrapper.set(User::getPhoneNumber,userUpdateDTO.getValue());
            pd = true;
        }
        if ("image".equals(userUpdateDTO.getChangeType())){
            updateWrapper.set(User::getImg,userUpdateDTO.getValue());
            pd = true;
        }
        if (!pd){
            return  R.fail("请选择提供的修改字段");
        }
            updateWrapper.set(User::getUpdateTime,LocalDateTime.now());
            updateWrapper.eq(User::getUserId,userUpdateDTO.getUserId());
            judge = this.update(updateWrapper);
        }
        if (!judge){
            return R.fail("修改失败");
        }
        return R.ok("修改成功");
    }

    @Override
    public R getLoginUserInfo(String token) {
        String email = redisTemplate.opsForValue().get(token);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getUserId,User::getUsername,User::getImg,User::getType,User::getTypeId,User::getGender);
        queryWrapper.eq(User::getEmail,email);
        User user = this.getOne(queryWrapper);
        //如果当前用户是学生，则返回学生性别
        if (UserConstants.USER_TYPE_STUDENT.equals(user.getType())){
            LambdaQueryWrapper<Student> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.select(Student::getGender)
                    .eq(Student::getStudentId,user.getTypeId());
            Student student = studentMapper.selectOne(queryWrapper1);
            user.setGender(student.getGender());
        }
        //如果当前用户是裁判员，则查询裁判员信息
        if (UserConstants.USER_TYPE_REFEREE.equals(user.getType()) ){
            LambdaQueryWrapper<Referee> queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.select(Referee::getGender)
                .eq(Referee::getRefereeId,user.getTypeId());
            Referee referee = refereeMapper.selectOne(queryWrapper2);
            user.setGender(referee.getGender());
        }
        UserLoginVO copy = BeanCopyUtils.copy(user, UserLoginVO.class);
        return R.ok(copy);
    }

    @Override
    public ResultVO selectStudent(Integer id) {
        List<Student> students = studentMapper.selectStudent(id);
        System.out.println("<<<<<<<<<<<<<<<");
        System.out.println("<<<<<<<<<<<<<<<");
        System.out.println(students);

        if (students.size() > 0) {
            return new ResultVO(ResStatus.OK, "success", students);
        } else {
            return new ResultVO(ResStatus.NO, "fail", students);
        }
    }


    /**
     * 判断用户的id和类型是否符合要求
     *
     * @param userId 用户id
     * @param type 用户类型
     * @return 是否正确
     */
    private boolean judgeUserInfo(Long userId,String type){
        if (userId == null || userId <= 0){
            return false;
        }
        return type != null && Integer.parseInt(type) <= Integer.parseInt(UserConstants.USER_TYPE_NOT_SCHOOL);
    }

}
