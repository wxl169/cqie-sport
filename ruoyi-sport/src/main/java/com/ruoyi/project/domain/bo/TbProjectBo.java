package com.ruoyi.project.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目管理 业务对象 tb_project
 *
 * @author ruoyi
 * @date 2023-09-14
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TbProjectBo extends BaseEntity {

    /**
     * 项目id 项目表的主键
     */
    private Long projectId;

    /**
     * 项目编号
     */
    @NotBlank(message = "项目编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String number;

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 规则介绍
     */
    private String introduction;

    /**
     * 比赛类型 0：个人，1：团体
     */
    @NotBlank(message = "比赛类型 0：个人，1：团体不能为空", groups = { AddGroup.class, EditGroup.class })
    private String type;

    /**
     * 男子/女子赛 0：女子，1：男子
     */
    @NotBlank(message = "男子/女子赛 0：女子，1：男子不能为空", groups = { AddGroup.class, EditGroup.class })
    private String gtype;

    /**
     * 招收人数 / 队数 根据比赛类型字段判断人数 / 队数
     */
    @NotNull(message = "招收人数 / 队数 根据比赛类型字段判断人数 / 队数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long upnum;

    /**
     * 裁判员数量
     */
    @NotNull(message = "裁判员数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long renum;


}
