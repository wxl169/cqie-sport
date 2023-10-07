package com.ruoyi.client.domain.entity;

import java.util.Date;
import lombok.Data;

/**
 * tb_arrangement
 * @author
 */
@Data
public class TArrangement {
    /**
     * 安排id 安排表的主键
     */
    private Long arrangementId;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 裁判员id
     */
    private String refereeId;

    /**
     * 运动员id / 团体id
     */
    private Integer typeId;

    /**
     * 安排信息单元id
     */
    private Long infoId;

    /**
     * 类别 0：个人赛，1：团体赛
     */
    private String type;

    /**
     * 是否取消 0：取消，1：未取消
     */
    private String isCancel;

    /**
     * 取消原因 若未取消则为NULL
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
     * 其他 备用字段
     */
    private String other;

}
