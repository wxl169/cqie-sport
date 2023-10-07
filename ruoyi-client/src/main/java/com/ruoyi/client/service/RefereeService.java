package com.ruoyi.client.service;

import com.ruoyi.client.domain.entity.Project;
import com.ruoyi.client.domain.entity.TCompetition;

import java.util.List;

/**
 * @Auther Mr lIU
 * @explanation
 * @date 2023/9/27 10:54
 */
public interface RefereeService {
    List<Project> find();

    boolean toVerify(String projectId);

    List<TCompetition> findPlayer(String projectId);

    int inputScores(List<TCompetition> playerList);
}
