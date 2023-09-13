package com.ruoyi.match.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 比赛记录 对象 tb_competition
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_competition")
public class TbCompetition extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 比赛id 比赛表的主键
     */
    @TableId(value = "competition_id")
    private Long competitionId;
    /**
     * 安排id
     */
    private Long arrangementId;
    /**
     * 运动员id / 团体id
     */
    private String typeId;
    /**
     * 类别 0：个人赛，1：团体赛
     */
    private String type;
    /**
     * 比赛结果
     */
    private String result;
    /**
     * 成绩
     */
    private Long score;
    /**
     * 成绩是否有效 0：无效，1：有效
     */
    private String isEffective;
    /**
     * 成绩无效原因 若有效则为NULL
     */
    private String reason;
    /**
     * 其他 备用字段
     */
    private String other;

}
