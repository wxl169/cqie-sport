package com.ruoyi.client.service;


import com.ruoyi.client.domain.dto.UserInfoDTO;
import com.ruoyi.client.domain.dto.UserUpdateDTO;
import com.ruoyi.client.domain.entity.User;
import com.ruoyi.client.domain.vo.UserInfoVO;
import com.ruoyi.common.core.domain.R;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 16956
 */
public interface UserService {
    /**
     * 根据前端传来的 邮箱 和 密码 来确定是否存在用户
     * @param email 邮箱
     * @param password 密码
     * @return 是否登录成功
     */
    public R getUserByEmailAndPassword(String email, String password);

    /**
     * 用户注册，可能是学生，裁判，或另外身份，分别处理
     * @param info 注册信息
     * @return 是否注册成功
     */
    public R addUserToRegister(Map<String, String> info);

    /**
     * 退出登录
     *
     * @return 是否退出成功
     */
    R logout(String token);

    /**
     * 判断当前用户是否登录
     *
     * @param token
     * @return
     */
     boolean judgeLogin(String token);

    /**
     * 获取当前登陆用户信息
     *
     * @param userInfoDTO 用户的id和类型
     * @return 用户信息
     */
    R getUserInfo(UserInfoDTO userInfoDTO);

    /**
     * 修改用户信息
     *
     * @param userUpdateDTO 传入的信息参数
     * @return 修改是否成功
     */
    R updateUserInfo(UserUpdateDTO userUpdateDTO);
}
