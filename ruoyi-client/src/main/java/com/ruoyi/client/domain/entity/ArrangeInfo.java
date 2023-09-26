package com.ruoyi.client.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 安排信息单元表
 * @TableName tb_arrange_info
 */
@TableName(value ="tb_arrange_info")
@Data
public class ArrangeInfo implements Serializable {
    /**
     * 安排信息单元id 安排信息单元表的主键
     */
    @TableId(value = "arrange_info_id",type = IdType.ASSIGN_ID)
    private Long arrangeInfoId;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 比赛时间
     */
    private Date time;

    /**
     * 比赛地点
     */
    private String place;

    /**
     * 其他安排信息
     */
    private String content;

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

    private static final long serialVersionUID = 1L;

}
