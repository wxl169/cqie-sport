package com.ruoyi.client.controller;

import com.ruoyi.client.service.MatchRankingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @Auther 邬文莱
 * @explanation
 * @date 2023/9/15 11:55
 */
//比赛排行榜
@Controller
public class MatchRankingController {
    @Resource
    private MatchRankingService matchRankingService;
    @RequestMapping("/user/matchRanking")
    public void matchRanking(String arrangement,int type,Model model){
        matchRankingService.find(arrangement,type);
    }
}
