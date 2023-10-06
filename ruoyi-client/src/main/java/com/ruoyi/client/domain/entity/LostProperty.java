package com.ruoyi.client.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author 16956
 * @since 2023-10-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_lost_property")
public class LostProperty implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 失物id
     */
    @TableId(value = "lost_id", type = IdType.ASSIGN_ID)
    private Long lostId;

    /**
     * 丢失物品名
     */
    private String lostName;

    /**
     * 丢失地点
     */
    private String lostPlace;

    /**
     * 拾取时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lostTime;

    /**
     * 拾取人姓名
     */
    private String pickUpName;

    /**
     * 拾取人手机号
     */
    private String pickUpPhone;

    /**
     * 是否领取（0——未领取，1——已领取）
     */
    private Integer status;

    /**
     * 认领人
     */
    private String claimName;

    /**
     * 认领人手机号
     */
    private String claimPhone;

    /**
     * 认领时间
     */
    private LocalDateTime claimTime;


}
