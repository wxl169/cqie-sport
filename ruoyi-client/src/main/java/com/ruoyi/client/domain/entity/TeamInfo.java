package com.ruoyi.client.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class TeamInfo implements Serializable {

    private static final long serialVersionUID = 1L;

   @TableId(value = "team_info_id",type = IdType.AUTO)
    private Long teamInfoId;


    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "athlete_id")
    private Long athleteId;

    private Team team;
    private String teamLeader;
}
