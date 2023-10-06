package com.ruoyi.client.controller;

import com.ruoyi.client.domain.entity.TCompetition;
import com.ruoyi.client.service.RefereeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther Mr lIU
 * @explanation
 * @date 2023/9/26 16:47
 */
@Controller
@RequestMapping("/client/system")
public class RefereeController {
    @Resource
    private RefereeService refereeService;
    //选择比赛项目
    @RequestMapping("/toReferee")
    public String toReferee(Model model){
        //查询比赛项目列表
        model.addAttribute("projectList",refereeService.find());
        return "/referee/referee";
    }
    //验证是否具有操作此比赛的权限
    @RequestMapping("/toVerify")
    public boolean toVerify(String projectId){
        return refereeService.toVerify(projectId);
    }
    //查询比赛学生信息
    @RequestMapping("/findPlayer")
    public String findPlayer(String projectId,Model model){
        model.addAttribute("PlayerList",refereeService.findPlayer(projectId));
        model.addAttribute("projectId",projectId);
        return "/referee/inputScores";
    }

    //批量录入学生成绩
    @RequestMapping("/toInputScores")
    public String toInputScores(String[] score, String[] is_effective,String[] reason,String projectId,Model model){
        //获取选手
        List<TCompetition> PlayerList = refereeService.findPlayer(projectId);
        int i = 0;
        for (TCompetition competition:PlayerList) {
            competition.setScore(Integer.valueOf(score[i]));
            competition.setIsEffective(is_effective[i]);
            competition.setReason(reason[i]);
            competition.setResult("正确");
            i++;
        }
        model.addAttribute("inputMsg",refereeService.inputScores(PlayerList));
        return "/referee/inputScores";
    }
}
