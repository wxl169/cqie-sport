package com.ruoyi.client.domain.dto;

import lombok.Data;

/**
 * @author 16956
 */
@Data
public class UserUpdateDTO {
    private Integer userId;
    private String type;
    private Integer typeId;
    private String changeType;
    private String value;
    private String token;
}
