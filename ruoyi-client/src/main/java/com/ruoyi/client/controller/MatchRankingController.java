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
@RequestMapping("/client/matchRanking")
public class MatchRankingController {
    @Resource
    private MatchRankingService matchRankingService;

    /**
     * 查询排行榜
     *
     * @param projectName 项目名称
     * @param type 项目类型
     * @param model 页面跳转
     * @return
     */
    @RequestMapping("/")
    public String matchRanking(String projectName,String type,Model model){
        //如果没有指定类型，默认为个人比赛
        if (type == null) {
            type = "0";
        }
        //查询排行
        model.addAttribute("matchPanking",matchRankingService.find(type,projectName));
        //查询筛选条件
        model.addAttribute("matchType",matchRankingService.findTerms());
        return "match/matchRanking";
    }
}
