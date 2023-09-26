package com.ruoyi.client.controller;

import com.ruoyi.client.service.ProjectService;
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





}
