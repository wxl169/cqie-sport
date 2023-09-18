package com.ruoyi.client.controller;

import com.ruoyi.client.service.MatchRankingService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @Auther 邬文莱
 * @explanation
 * @date 2023/9/15 11:55
 */
//比赛排行榜
public class MatchRankingController {
    int i;
    @Resource
    private MatchRankingService matchRankingService;
    @RequestMapping("/matchRanking")
    public void matchRanking(String arrangement,int type,Model model){
        System.out.println(type);
        System.out.println(arrangement);
       // matchRankingService.find(arrangement);
    }
}
