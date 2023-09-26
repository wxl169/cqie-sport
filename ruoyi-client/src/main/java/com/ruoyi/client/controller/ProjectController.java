package com.ruoyi.client.controller;

import com.ruoyi.client.service.ProjectService;
import com.ruoyi.common.core.domain.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 16956
 */
@RestController
@RequestMapping("/client/project")
public class ProjectController {
    @Resource
    private ProjectService projectService;


    @GetMapping("/get")
    public R getProjectName(){

        return R.ok();
    }


}
