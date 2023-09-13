package com.ruoyi.project.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目管理 对象 tb_project
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_project")
public class TbProject extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 项目id 项目表的主键
     */
    @TableId(value = "project_id")
    private Long projectId;
    /**
     * 项目编号
     */
    private String number;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 规则介绍
     */
    private String introduction;
    /**
     * 比赛类型 0：个人，1：团体
     */
    private String type;
    /**
     * 男子/女子赛 0：女子，1：男子
     */
    private String gtype;
    /**
     * 招收人数 / 队数 根据比赛类型字段判断人数 / 队数
     */
    private Long upnum;
    /**
     * 裁判员数量
     */
    private Long renum;
    /**
     * 是否删除
     */
    private String isCancel;
    /**
     * 其他 备用字段
     */
    private String other;

}
