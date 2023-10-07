package com.ruoyi.client.domain.entity;

import java.util.Date;
import lombok.Data;

/**
 * tb_competition
 * @author
 */
@Data
public class TCompetition {
    /**
     * 比赛id 比赛表的主键
     */
    private Long competitionId;

    /**
     * 安排id
     */
    private Long arrangementId;

    /**
     * 运动员id / 团体id
     */
    private Integer typeId;

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
    private Integer score;

    /**
     * 成绩是否有效 0：无效，1：有效
     */
    private String isEffective;

    /**
     * 成绩无效原因 若有效则为NULL
     */
    private String reason;

    /**
     * 录入时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 其他 备用字段 （存放用户名）
     */
    private String other;


}
