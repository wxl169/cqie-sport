package com.ruoyi.project.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学院管理业务对象 tb_college
 *
 * @author ruoyi
 * @date 2023-09-13
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TbCollegeBo extends BaseEntity {

    /**
     * 学院id 学院表的主键
     */
    @NotNull(message = "学院id 学院表的主键不能为空", groups = { EditGroup.class })
    private Long collegeId;

    /**
     * 学院名
     */
    @NotBlank(message = "学院名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 学院人数
     */
    @NotNull(message = "学院人数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long snum;

    /**
     * 学院总分 初始为0
     */
    @NotNull(message = "学院总分 初始为0不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long score;

    /**
     * 其他 备用字段
     */
    @NotBlank(message = "其他 备用字段不能为空", groups = { AddGroup.class, EditGroup.class })
    private String other;


}
