package com.ruoyi.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.client.domain.entity.Team;
import com.ruoyi.client.domain.general.GeneralDAO;

import org.springframework.stereotype.Repository;

@Repository
public interface TeamMapper extends BaseMapper<Team> {
    int createTime(Team team);

    void selectTeamByStudentId(Integer typeId);
}
