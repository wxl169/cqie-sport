package com.ruoyi.client.domain.vo;


import lombok.Data;


/**
 * @author 16956
 */
@Data
public class UserLoginVO {
    private Long userId;
    /**
     * 用户名
     */
    private String username;

    /**
     * 性别 1：男，0：女
     */
    private String gender;

    /**
     * 头像 存储图片相对地址，初始应指定默认头像
     */
    private String img;
    /**
     * 类型
     */
    private String type;
    /**
     * 学生id
     */
    private Long typeId;
}
