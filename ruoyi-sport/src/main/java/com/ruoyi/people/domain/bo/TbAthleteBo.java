package com.ruoyi.people.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 运动员管理 业务对象 tb_athlete
 *
 * @author ruoyi
 * @date 2023-09-19
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TbAthleteBo extends BaseEntity {

    /**
     * 运动员id 运动员表的主键
     */
    private Long athleteId;

    /**
     * 学生id
     */
    @NotNull(message = "学生id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long studentId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 运动员编号
     */
    @NotBlank(message = "运动员编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String number;


}
