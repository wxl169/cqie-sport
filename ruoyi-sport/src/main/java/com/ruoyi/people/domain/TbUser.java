package com.ruoyi.people.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户管理对象 tb_user
 *
 * @author ruoyi
 * @date 2023-09-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_user")
public class TbUser extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 用户id 用户表的主键
     */
    @TableId(value = "user_id")
    private Long userId;
    /**
     * 身份id
     */
    private Long typeId;
    /**
     * 身份类型
     */
    private String type;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实姓名
     */
    private String realname;
    /**
     * 性别
     */
    private String gender;
    /**
     * 身份证号 18位号码
     */
    private String idnumber;
    /**
     * 联系电话
     */
    private String phoneNumber;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 头像 存储图片相对地址，初始应指定默认头像
     */
    private String img;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 其他 备用字段
     */
    private String other;

}
