package com.ruoyi.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.client.domain.entity.ArrangeInfo;
import com.ruoyi.client.domain.entity.Project;
import com.ruoyi.client.domain.vo.*;
import com.ruoyi.client.mapper.ArrangeInfoMapper;
import com.ruoyi.client.mapper.ProjectMapper;
import com.ruoyi.client.service.ProjectService;
import com.ruoyi.common.constant.ProjectConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.StringUtils;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ArrangeInfoMapper arrangeInfoMapper;


    @Override
    public R listSignUpNeedInfos() {
        List<ArrangeInfo> arrangeInfos = arrangeInfoMapper.selectList(null);
        List<SportsVo> sportsVos = new ArrayList<>();

        for (ArrangeInfo arrangeInfo : arrangeInfos){
            SportsVo sportsVo = new SportsVo();
            LambdaQueryWrapper<Project> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Project::getProjectId,arrangeInfo.getProjectId())
                .eq(Project::getIsCancel,0);
            Project project = projectMapper.selectOne(lambdaQueryWrapper);

            //设置比赛项目工具类属性
            sportsVo.setProjectId(project.getProjectId());
            sportsVo.setProjectNumber(project.getNumber());
            sportsVo.setProjectName(project.getName());
            sportsVo.setProjectIntroduction(project.getIntroduction());
            sportsVo.setProjectType(project.getType());
            sportsVo.setProjectGType(project.getGtype());
            sportsVo.setProjectNeedNum(project.getUpnum());
            sportsVo.setProjectNeedReNum(project.getRenum());

            //安排属性
            sportsVo.setArrangeInfoId(arrangeInfo.getArrangeInfoId().intValue());
            sportsVo.setArrangeInfoTime(arrangeInfo.getTime());
            sportsVo.setArrangeInfoPlace(arrangeInfo.getPlace());
            sportsVo.setArrangeInfoContent(arrangeInfo.getContent());

            sportsVos.add(sportsVo);
        }
        return R.ok(sportsVos);
    }

    @Override
    public R listProjectByNumber(String projectNumber) {
        SportsVo sportsVo = new SportsVo();
        LambdaQueryWrapper<Project> prolqw = new LambdaQueryWrapper<>();
        prolqw.eq(StringUtils.isNotEmpty(projectNumber),Project::getNumber,projectNumber)
            .eq(Project::getIsCancel,"0");
        Project project = projectMapper.selectOne(prolqw);
        LambdaQueryWrapper<ArrangeInfo> arrlqw = new LambdaQueryWrapper<>();
        arrlqw.eq(ArrangeInfo::getProjectId,project.getProjectId());
        ArrangeInfo arrangeInfo = arrangeInfoMapper.selectOne(arrlqw);


        //设置比赛项目工具类属性
        sportsVo.setProjectId(project.getProjectId());
        sportsVo.setProjectNumber(project.getNumber());
        sportsVo.setProjectName(project.getName());
        sportsVo.setProjectIntroduction(project.getIntroduction());
        sportsVo.setProjectType(project.getType());
        sportsVo.setProjectGType(project.getGtype());
        sportsVo.setProjectNeedNum(project.getUpnum());
        sportsVo.setProjectNeedReNum(project.getRenum());

        //安排属性
        sportsVo.setArrangeInfoId(arrangeInfo.getArrangeInfoId().intValue());
        sportsVo.setArrangeInfoTime(arrangeInfo.getTime());
        sportsVo.setArrangeInfoPlace(arrangeInfo.getPlace());
        sportsVo.setArrangeInfoContent(arrangeInfo.getContent());

        return R.ok(sportsVo);
    }

    @Override
    public R listProjectsByName(String projectName) {
        List<SportsVo> sportsVos = new ArrayList<>();
        LambdaQueryWrapper<Project> plwq = new LambdaQueryWrapper<>();
        plwq.like(Project::getName,projectName)
            .eq(Project::getIsCancel,0);
        List<Project> projects = projectMapper.selectList(plwq);

        return getR(sportsVos, projects);

    }

    @Override
    public R listProjectsByType(String projectType) {
        List<SportsVo> sportsVos = new ArrayList<>();
        LambdaQueryWrapper<Project> plwq = new LambdaQueryWrapper<>();
        plwq.eq(Project::getType,projectType)
            .eq(Project::getIsCancel,0);
        List<Project> projects = projectMapper.selectList(plwq);

        return getR(sportsVos, projects);
    }

    @Override
    public R getProjectPage(Integer pageNum, Integer pageSize) {
        if (pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null){
            pageSize = 5;
        }
        //查询正在报名的比赛
        List<ProjectPageVO> projectPageVOS = projectMapper.getProjectPage((pageNum-1)*pageSize,pageSize);
        projectPageVOS = projectPageVOS.stream().map(projectPageVO -> {

            return projectPageVO;
        }).collect(Collectors.toList());
        //页数
        Long projectPageTotal = projectMapper.getProjectPageTotal();
        PageVO pageVO = new PageVO(projectPageVOS,projectPageTotal);
        return R.ok(pageVO);
    }

    @NotNull
    private R getR(List<SportsVo> sportsVos, List<Project> projects) {
        for (Project project : projects){
            LambdaQueryWrapper<ArrangeInfo> arrlqw = new LambdaQueryWrapper<>();
            arrlqw.eq(ArrangeInfo::getProjectId,project.getProjectId());
            ArrangeInfo arrangeInfo = arrangeInfoMapper.selectOne(arrlqw);
            if (arrangeInfo != null){
                SportsVo sportsVo = new SportsVo();
                //设置比赛项目工具类属性
                sportsVo.setProjectId(project.getProjectId());
                sportsVo.setProjectNumber(project.getNumber());
                sportsVo.setProjectName(project.getName());
                sportsVo.setProjectIntroduction(project.getIntroduction());
                sportsVo.setProjectType(project.getType());
                sportsVo.setProjectGType(project.getGtype());
                sportsVo.setProjectNeedNum(project.getUpnum());
                sportsVo.setProjectNeedReNum(project.getRenum());

                //安排属性
                sportsVo.setArrangeInfoId(arrangeInfo.getArrangeInfoId().intValue());
                sportsVo.setArrangeInfoTime(arrangeInfo.getTime());
                sportsVo.setArrangeInfoPlace(arrangeInfo.getPlace());
                sportsVo.setArrangeInfoContent(arrangeInfo.getContent());

                sportsVos.add(sportsVo);
            }
        }
        return R.ok(sportsVos);
    }


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
