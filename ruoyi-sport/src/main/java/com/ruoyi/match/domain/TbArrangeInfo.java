package com.ruoyi.match.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 安排信息单元 对象 tb_arrange_info
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_arrange_info")
public class TbArrangeInfo extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 安排信息单元id 安排信息单元表的主键
     */
    @TableId(value = "arrange_info_id")
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
     * 其他 备用字段
     */
    private String other;

}
