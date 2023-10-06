package com.ruoyi.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.client.domain.entity.Competition;
import com.ruoyi.client.domain.vo.ResultVO;
import com.ruoyi.common.core.domain.R;

public interface CompetitionService extends IService<Competition> {

    /**
     * 查找比赛结果列表
     * @return
     */
    public ResultVO listCompetitions();

    /**
     * 比赛结果列表
     */
    public ResultVO listCompetitionResults();

}
