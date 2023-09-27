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

}
