package com.ruoyi.client.domain.dto;


import lombok.Data;

/**
 * @author 16956
 */
@Data
public class UserInfoDTO {
    private Long userId;
    private String type;
    private Integer typeId;
    private String token;
}
