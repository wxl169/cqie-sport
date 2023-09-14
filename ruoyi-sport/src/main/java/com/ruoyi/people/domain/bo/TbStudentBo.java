package com.ruoyi.people.domain.bo;

import com.ruoyi.common.annotation.Sensitive;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.enums.SensitiveStrategy;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;

import java.util.Date;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生管理业务对象 tb_student
 *
 * @author ruoyi
 * @date 2023-09-14
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TbStudentBo extends BaseEntity {

    /**
     * 学生id 学生表的主键
     */
    @NotNull(message = "学生id 学生表的主键不能为空", groups = {EditGroup.class})
    private Long studentId;

    /**
     * 班级id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull(message = "班级id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long classId;

    /**
     * 学院id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull(message = "学院id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long collegeId;

    /**
     * 运动员类型
     */
    private String isAthlete;

    /**
     * 学号
     */
    @NotBlank(message = "学号不能为空", groups = {AddGroup.class, EditGroup.class})
    private String studentNumber;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空", groups = {AddGroup.class, EditGroup.class})
    private String name;

    /**
     * 性别
     */
    @NotBlank(message = "性别不能为空", groups = {AddGroup.class, EditGroup.class})
    private String gender;

    /**
     * 身份证号 18位号码
     */
    @Sensitive(strategy = SensitiveStrategy.ID_CARD)
    @NotBlank(message = "身份证号 18位号码不能为空", groups = {AddGroup.class, EditGroup.class})
    private String idnumber;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空", groups = {AddGroup.class, EditGroup.class})
    private String phoneNumber;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 信用值
     */
    private Long creditScore;


}
