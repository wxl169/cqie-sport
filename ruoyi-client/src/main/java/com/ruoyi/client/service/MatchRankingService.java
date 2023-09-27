package com.ruoyi.client.service;

import com.ruoyi.client.domain.vo.MatchPankingVO;
import com.ruoyi.client.domain.vo.MatchTypeVO;
import com.ruoyi.common.core.domain.R;

import java.util.List;

/**
 * @author 16956
 * @Auther 邬文莱
 * @explanation
 * @date 2023/9/15 12:01
 */

public interface MatchRankingService {


    /**
     * 获取比赛排行榜信息
     *
     * @param projectName 项目名
     * @return
     */
    R getMatchRanking(String projectName);
}
