package com.ruoyi.client.controller;

import com.ruoyi.client.service.RefereeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

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
    //批量录入学生成绩
    @RequestMapping("/toInputScores")
    public String toInputScores(){

        return "/referee/inputScores";
    }
}
