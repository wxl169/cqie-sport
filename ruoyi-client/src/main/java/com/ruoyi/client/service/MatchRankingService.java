package com.ruoyi.client.service;

import com.ruoyi.client.domain.dto.MatchTypeDTO;
import com.ruoyi.client.domain.vo.MatchPankingVO;

import java.util.List;
import java.util.Map;

/**
 * @Auther 邬文莱
 * @explanation
 * @date 2023/9/15 12:01
 */
public interface MatchRankingService {
    List<MatchPankingVO> find(int type,String projectName);

    List<MatchTypeDTO> findTerms();
}
