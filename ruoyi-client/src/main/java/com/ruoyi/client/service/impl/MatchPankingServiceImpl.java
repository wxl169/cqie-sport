package com.ruoyi.client.service.impl;

import com.ruoyi.client.domain.vo.MatchPankingVO;
import com.ruoyi.client.domain.vo.MatchTypeVO;
import com.ruoyi.client.mapper.MatchPankingMapper;
import com.ruoyi.client.service.MatchRankingService;
import com.ruoyi.common.core.domain.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 16956
 * @Auther 邬文莱
 * @explanation
 * @date 2023/9/18 10:06
 */
@Service
public class MatchPankingServiceImpl implements MatchRankingService {
    @Resource
    private MatchPankingMapper matchPankingMapper;

    @Override
    public R getMatchRanking(String projectName) {
        //如果项目名为空 则返回 空
        if (projectName == null || "null".equals(projectName)){
            return null;
        }
        return R.ok( matchPankingMapper.find(projectName));
    }
}

