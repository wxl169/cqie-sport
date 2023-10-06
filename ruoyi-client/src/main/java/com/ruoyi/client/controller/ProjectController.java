package com.ruoyi.client.controller;

import com.ruoyi.client.service.ProjectService;
import com.ruoyi.common.core.domain.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 16956
 */
@RestController
@RequestMapping("/client/project")
public class ProjectController {
    @Resource
    private ProjectService projectService;


    /**
     * 获取项目名
     * @return 返回项目名列表
     */
    @GetMapping("/get/{projectType}")
    public R getProjectName(@PathVariable("projectType") String projectType){
        return projectService.getProjectName(projectType);
    }

    /**
     * 获取预报名的比赛名
     *
     * @param pageNum 页码
     * @param pageSize 每页数据量
     * @return 分页数据
     */
    @GetMapping("/get")
    public R getProjectPage(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize") Integer pageSize){
        if (pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null){
            pageSize = 5;
        }
        return  projectService.getProjectPage(pageNum,pageSize);
    }
}
