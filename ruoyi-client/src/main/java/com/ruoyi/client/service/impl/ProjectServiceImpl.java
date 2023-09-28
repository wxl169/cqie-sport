package com.ruoyi.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.client.domain.entity.Project;
import com.ruoyi.client.domain.vo.ResStatus;
import com.ruoyi.client.domain.vo.ResultVO;
import com.ruoyi.client.domain.vo.SignUpVO;
import com.ruoyi.client.domain.vo.ProjectNameVO;
import com.ruoyi.client.mapper.ProjectMapper;
import com.ruoyi.client.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.common.constant.ProjectConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 项目表  服务实现类
 * </p>
 *
 * @author 16956
 * @since 2023-09-25
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Override
    public R getProjectName(String projectType) {
        //如果项目类型为空，则返回请选择项目名,否则返回指定条件内容
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Project::getProjectId,Project::getName);

        if (projectType != null && !ProjectConstants.NOT_PROJECT.equals(projectType)){
            queryWrapper.eq(Project::getType,projectType);
        }

        List<Project> list = this.list(queryWrapper);
        return R.ok(BeanCopyUtils.copyList(list, ProjectNameVO.class));
    }
    @Autowired
    private ProjectMapper projectMapper;

    /**
     * 申请报名时返回比赛项目的信息列表
     *
     * @return
     */
    @Override
    public ResultVO listSignUpNeedInfo() {
        List<SignUpVO> list = projectMapper.selectSignUpNeedInfo();
        return new ResultVO(ResStatus.OK, "success", list);
    }
}
