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
    @RequestMapping("/client/matchRanking")
    public String matchRanking(String projectName,String type,Model model){
        if (type == null)
            type = "0";
        //查询排行
        model.addAttribute("matchPanking",matchRankingService.find(type,projectName));
        //查询筛选条件
        model.addAttribute("matchType",matchRankingService.findTerms());
        return "match/matchRanking";
    }
}
