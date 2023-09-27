package com.ruoyi.client.controller;

import com.ruoyi.client.service.MatchRankingService;
import com.ruoyi.common.core.domain.R;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 16956
 * @Auther 邬文莱
 * @explanation
 * @date 2023/9/15 11:55
 */
@RestController
@RequestMapping("/client/matchRanking")
public class MatchRankingController {
    @Resource
    private MatchRankingService matchRankingService;


    /**
     * 查看比赛排行榜
     *
     * @param projectName 项目名
     * @return 比赛信息
     */
    @GetMapping("/get/{projectName}")
    public R getMatchRanking(@PathVariable("projectName") String projectName){
        return matchRankingService.getMatchRanking(projectName);
    }
}
