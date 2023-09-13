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
 * 学生管理业务对象 tb_student
 *
 * @author ruoyi
 * @date 2023-09-13
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TbStudentBo extends BaseEntity {

    /**
     * 学生id 学生表的主键
     */
    @NotNull(message = "学生id 学生表的主键不能为空", groups = { EditGroup.class })
    private Long studentId;

    /**
     * 班级id
     */
    @NotNull(message = "班级id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long classId;

    /**
     * 学院id
     */
    @NotNull(message = "学院id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long collegeId;

    /**
     * 是否为运动员 0：不是，1：是
     */
    @NotBlank(message = "是否为运动员 0：不是，1：是不能为空", groups = { AddGroup.class, EditGroup.class })
    private String isAthlete;

    /**
     * 学号
     */
    @NotBlank(message = "学号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String studentNumber;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 性别 1：男，0：女
     */
    @NotBlank(message = "性别 1：男，0：女不能为空", groups = { AddGroup.class, EditGroup.class })
    private String gender;

    /**
     * 身份证号 18位号码
     */
    @NotBlank(message = "身份证号 18位号码不能为空", groups = { AddGroup.class, EditGroup.class })
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

    /**
     * 运动会信用 默认100，若出现多次退选，舞弊等行为，扣除一定分数
     */
    @NotNull(message = "运动会信用 默认100，若出现多次退选，舞弊等行为，扣除一定分数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long creditScore;

    /**
     * 其他 备用字段
     */
    @NotBlank(message = "其他 备用字段不能为空", groups = { AddGroup.class, EditGroup.class })
    private String other;


}
