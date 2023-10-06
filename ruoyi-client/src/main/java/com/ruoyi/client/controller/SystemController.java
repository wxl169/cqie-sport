package com.ruoyi.client.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 16956
 */
@Controller
@RequestMapping("/client/system")
public class SystemController {

    /**
     * 跳转到登录
     *
     * @return 跳转到登录页面
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "user/login";
    }
    /**
     * 跳转到注册
     *
     * @return 注册界面
     */
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "user/register";
    }

    /**
     * 前往个人详情页
     *
     * @return 个人详情页
     */
    @RequestMapping("/toMyInfo")
    public String toMyInfo(HttpServletRequest request){
        return "user/my";
    }


    @RequestMapping("/toSignUp")
    public String toSignUp(){
        return "user/signup";
    }

    /**
     * 跳转到主页
     *
     * @param reason 判断是否登录
     * @return 主页面
     */
    @RequestMapping("/toIndex")
    public ModelAndView toIndex(String reason){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("reason",reason);
        modelAndView.setViewName("user/index");
        return  modelAndView;
    }
    /**
     * 跳转到赛事公布
     *
     * @return 赛事公布
     */
    @RequestMapping("/toSportsList")
    public String toSports(){
        return "sports/sports";
    }
    /**
     * 跳转到比赛成绩查询
     *
     * @return 比赛成绩查询
     */
    @RequestMapping("/toMatchRanking")
    public String toMatchRanking(){
        return "match/matchRanking";
    }

    @RequestMapping("/toScore")
    public String toScore(){
        return "sports/score";

    }

}
