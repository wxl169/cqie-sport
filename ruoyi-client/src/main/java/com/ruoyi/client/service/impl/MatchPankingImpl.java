package com.ruoyi.client.service.impl;

import com.ruoyi.client.domain.vo.MatchPankingVO;
import com.ruoyi.client.mapper.MatchPankingMapper;
import com.ruoyi.client.service.MatchRankingService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther 邬文莱
 * @explanation
 * @date 2023/9/18 10:06
 */
public class MatchPankingImpl implements MatchRankingService {
    @Resource
    MatchPankingMapper matchPankingMapper;
    @Override
    public List<MatchPankingVO> find(String arrangement) {
        return matchPankingMapper.find(arrangement);
    }
}
