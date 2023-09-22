package com.ruoyi.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.client.domain.entity.Project;
import com.ruoyi.common.core.domain.R;

public interface ProjectService extends IService<Project> {

    public R listSignUpNeedInfo();

    public R listProjectByNumber(String projectNumber);

    public R listProjectsByName(String projectName);

    public R listProjectsByType(String projectType);
}
