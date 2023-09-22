package com.ruoyi.client.service.impl;

import com.ruoyi.client.domain.vo.MatchPankingVO;
import com.ruoyi.client.domain.vo.MatchTypeVO;
import com.ruoyi.client.mapper.MatchPankingMapper;
import com.ruoyi.client.service.MatchRankingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther 邬文莱
 * @explanation
 * @date 2023/9/18 10:06
 */
@Service
public class MatchPankingImpl implements MatchRankingService {
    @Resource
    private MatchPankingMapper matchPankingMapper;
    @Override
    public List<MatchPankingVO> find(String type, String projectName) {
        return matchPankingMapper.find(type,projectName);
    }

    @Override
    public List<MatchTypeVO> findTerms() {
        return matchPankingMapper.findTerms();
    }
}

