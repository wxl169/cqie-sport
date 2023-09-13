package com.ruoyi.project.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 班级管理业务对象 tb_class
 *
 * @author ruoyi
 * @date 2023-09-13
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TbClassBo extends BaseEntity {

    /**
     * 班级id 班级表的主键
     */
    @NotNull(message = "班级id 班级表的主键不能为空", groups = { EditGroup.class })
    private Long classId;

    /**
     * 学院id
     */
    @NotNull(message = "学院id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long collegeId;

    /**
     * 班级名称
     */
    @NotBlank(message = "班级名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 班级人数
     */
    @NotNull(message = "班级人数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long snum;

    /**
     * 班级总分 初始为0
     */
    @NotNull(message = "班级总分 初始为0不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long score;

    /**
     * 其他 备用字段
     */
    @NotBlank(message = "其他 备用字段不能为空", groups = { AddGroup.class, EditGroup.class })
    private String other;


}
