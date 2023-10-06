package com.ruoyi.client.domain.vo;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LostPropertyVO {
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
    private LocalDateTime lostTime;

    /**
     * 拾取人姓名
     */
    private String pickUpName;
}
