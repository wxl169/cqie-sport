package com.ruoyi.client.service;


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
}
