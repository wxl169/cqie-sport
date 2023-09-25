package com.ruoyi.client.controller;

import com.ruoyi.client.domain.vo.SportsVo;
import com.ruoyi.client.service.ProjectService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户端：比赛项目模块
 * @author 20664
 */
@Controller
@RequestMapping("/client/sports")
public class SportsController extends BaseController {

    @Autowired
    private ProjectService projectService;

    //访问赛事公布页面
    @GetMapping("/toSportsList")
    public String toList(){
        return "user/sports";
    }

    //赛事列表
    @GetMapping("/sportsList")
    @ResponseBody
    public R listSignUpNeedInfo() {
        R sportsVo = projectService.listSignUpNeedInfo();
        return sportsVo;
    }

    //通过赛事编号查询
    @GetMapping("/searchSportByNumber")
    @ResponseBody
    public R searchProjectsByNumber(String projectNumber) {
        R resultVO = projectService.listProjectByNumber(projectNumber);
        return resultVO;
    }

    //通过赛事名查询
    @GetMapping("/searchSportsByName")
    @ResponseBody
    public R searchSportsByName(String projectName) {
        R resultVO = projectService.listProjectsByName(projectName);
        return resultVO;
    }

    //通过赛事类别查询
    @GetMapping("/searchSportsByType")
    @ResponseBody
    public R searchProjectsByType(String projectType) {
        R resultVO = projectService.listProjectsByType(projectType);
        return resultVO;
    }

}
