package com.ruoyi.client.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_project")
public class Project {
    /**
     * 项目id 项目表的主键
     */
    @TableId(value = "project_id",type = IdType.ASSIGN_ID)
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
    private Integer upnum;

    /**
     * 招收裁判员数量
     */
    private Integer renum;

    /**
     * 是否删除
     */
    private String isCancel;


    /**
     * 录入时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 其他 备用字段
     */
    private String other;

}
