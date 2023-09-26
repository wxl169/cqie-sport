package com.ruoyi.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.client.domain.entity.Project;
import com.ruoyi.common.core.domain.R;
/**
 * <p>
 * 项目表  服务类
 * </p>
 *
 * @author 16956
 * @since 2023-09-25
 */
public interface ProjectService extends IService<Project> {

    public R listSignUpNeedInfo();

    public R listProjectByNumber(String projectNumber);

    public R listProjectsByName(String projectName);

    public R listProjectsByType(String projectType);
}
