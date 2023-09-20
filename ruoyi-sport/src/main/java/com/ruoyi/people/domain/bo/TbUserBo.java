package com.ruoyi.people.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户管理业务对象 tb_user
 *
 * @author ruoyi
 * @date 2023-09-15
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TbUserBo extends BaseEntity {

    /**
     * 用户id 用户表的主键
     */
    private Long userId;

    /**
     * 身份id
     */
    private Long typeId;

    /**
     * 身份类型
     */
    @NotBlank(message = "身份类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String type;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String username;

    /**
     * 真实姓名
     */
    @NotBlank(message = "真实姓名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String realname;

    /**
     * 性别
     */
    @NotBlank(message = "性别不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 邮箱地址
     */
    private String email;


}
