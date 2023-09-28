package com.ruoyi.client.service;

import com.ruoyi.client.domain.dto.TeamDto;
import com.ruoyi.client.domain.vo.ResultVO;


public interface TeamService {
    ResultVO createTeam(TeamDto teamDto);

    ResultVO selectTeamByStudentId(Integer typeId);

    ResultVO deleteTeam(TeamDto teamDto);

}
