package com.ruoyi.client.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 项目表
 * </p>
 *
 * @author 16956
 * @since 2023-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目id 项目表的主键
     */
    @TableId(value = "project_id", type = IdType.AUTO)
    private Integer projectId;

    /**
     * 项目编号
     */
    @TableField("number")
    private String number;

    /**
     * 项目名称
     */
    @TableField("name")
    private String name;

    /**
     * 规则介绍
     */
    @TableField("introduction")
    private String introduction;

    /**
     * 比赛类型 0：个人，1：团体
     */
    @TableField("type")
    private String type;

    /**
     * 男子/女子赛 0：女子，1：男子
     */
    @TableField("gtype")
    private String gtype;

    /**
     * 招收人数 / 队数 根据比赛类型字段判断人数 / 队数
     */
    @TableField("upnum")
    private Integer upnum;

    /**
     * 裁判员数量
     */
    @TableField("renum")
    private Integer renum;

    /**
     * 是否删除
     */
    @TableField("is_cancel")
    private String isCancel;

    /**
     * 录入时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 其他 备用字段
     */
    @TableField("other")
    private String other;


}
