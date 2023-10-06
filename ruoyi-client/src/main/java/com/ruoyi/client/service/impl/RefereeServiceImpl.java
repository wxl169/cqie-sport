package com.ruoyi.client.service.impl;

import com.ruoyi.client.domain.entity.Project;
import com.ruoyi.client.domain.entity.TCompetition;
import com.ruoyi.client.mapper.ProjectMapper;
import com.ruoyi.client.mapper.TbArrangementDao;
import com.ruoyi.client.mapper.TbCompetitionDao;
import com.ruoyi.client.service.RefereeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther Mr lIU
 * @explanation
 * @date 2023/9/27 11:01
 */
@Service
public class RefereeServiceImpl implements RefereeService {
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private TbArrangementDao arrangementDao;
    @Resource
    private TbCompetitionDao competitionDao;
    @Override
    public List<Project> find() {
        return projectMapper.find();
    }

    @Override
    public boolean toVerify(String projectId) {
        return projectMapper.verify(projectId);
    }

    @Override
    public List<TCompetition> findPlayer(String projectId) {
        return arrangementDao.selectByProjectId(projectId);
    }

    @Override
    public int inputScores(List<TCompetition> playerList) {
        for (TCompetition competition:playerList) {
            competitionDao.insertSelective(competition);
        }
        return 1;
    }
}
