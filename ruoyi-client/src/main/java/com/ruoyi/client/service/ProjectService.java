package com.ruoyi.client.service;

import com.ruoyi.client.domain.entity.Project;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.client.domain.vo.ResultVO;
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
    /**
     * 申请报名时返回比赛项目的信息列表
     * @return
     */
    public ResultVO listSignUpNeedInfo();

    /**
     * 查询项目名
     *
     * @param projectType 项目类型
     * @return 项目名列表
     */
    R getProjectName(String projectType);
}
