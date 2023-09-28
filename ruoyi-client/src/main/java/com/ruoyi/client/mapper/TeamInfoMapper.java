package com.ruoyi.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.client.domain.entity.TeamInfo;

import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TeamInfoMapper extends BaseMapper<TeamInfo> {
    int createTeamInfo(List<TeamInfo> list);

    List<TeamInfo> selectTeamByStudentId(Integer typeId);
}
