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
 * 裁判员管理业务对象 tb_referee
 *
 * @author ruoyi
 * @date 2023-09-19
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TbRefereeBo extends BaseEntity {

    /**
     * 裁判员id 裁判员表的主键
     */
    private Long refereeId;

    /**
     * 裁判员编号
     */
    @NotBlank(message = "裁判员编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String number;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 性别
     */
    @NotBlank(message = "性别不能为空", groups = { AddGroup.class, EditGroup.class })
    private String gender;

    /**
     * 身份证号 18位号码
     */
    @NotBlank(message = "身份证号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String idnumber;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空", groups = { AddGroup.class, EditGroup.class })
    private String phoneNumber;

    /**
     * 出生日期
     */
    @NotNull(message = "出生日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date birthday;


}
