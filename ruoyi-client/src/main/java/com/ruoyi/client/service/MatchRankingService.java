package com.ruoyi.client.service;

import com.ruoyi.client.domain.vo.MatchPankingVO;

import java.util.List;

/**
 * @Auther 邬文莱
 * @explanation
 * @date 2023/9/15 12:01
 */
public interface MatchRankingService {
    List<MatchPankingVO> find(String arrangement);
}
