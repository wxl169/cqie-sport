package com.ruoyi.client.domain.dto;

import lombok.Data;

/**
 * @author 16956
 */
@Data
public class UserUpdateDTO {
    private Long userId;
    private String type;
    private Long typeId;
    private String changeType;
    private String value;
}
