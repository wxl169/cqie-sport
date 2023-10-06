package com.ruoyi.client.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeamDto {
    private List<Integer> students;
    private String teamName;

    private Long typeId;
    private Long teamId;
}
