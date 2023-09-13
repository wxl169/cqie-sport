package com.ruoyi.match.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 安排 对象 tb_arrangement
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_arrangement")
public class TbArrangement extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 安排id 安排表的主键
     */
    @TableId(value = "arrangement_id")
    private Long arrangementId;
    /**
     * 项目id
     */
    private Long projectId;
    /**
     * 裁判员id
     */
    private Long refereeId;
    /**
     * 运动员id / 团体id
     */
    private Long typeId;
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
     * 其他 备用字段
     */
    private String other;

}
