package com.ruoyi.client.domain.dto;


import lombok.Data;


/**
 * @author 16956
 */
@Data
public class UserLoginDTO {
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 密码
     */
    private String password;
}
