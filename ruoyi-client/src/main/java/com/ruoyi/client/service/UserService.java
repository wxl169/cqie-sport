package com.ruoyi.client.service;


import com.ruoyi.common.core.domain.R;

import java.util.Map;

/**
 * @author 16956
 */
public interface UserService {
    /**
     * 根据前端传来的 邮箱 和 密码 来确定是否存在用户
     * @param email
     * @param password
     * @return
     */
    public R getUserByEmailAndPassword(String email, String password);

    /**
     * 用户注册，可能是学生，裁判，或另外身份，分别处理
     * @param info
     * @return
     */
    public R addUserToRegister(Map<String, String> info);

}
