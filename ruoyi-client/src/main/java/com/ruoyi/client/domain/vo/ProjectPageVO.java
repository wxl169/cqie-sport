package com.ruoyi.client.domain.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPageVO {
    /**
     * 项目id 项目表的主键
     */
    @TableId(value = "project_id", type = IdType.ASSIGN_ID)
    private Long projectId;

    /**
     * 项目名称
     */
    @TableField("name")
    private String name;

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
    @TableField("time")
    private LocalDateTime time;
}
