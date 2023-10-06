package com.ruoyi.client.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LostPropertyAddDTO {
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
}
