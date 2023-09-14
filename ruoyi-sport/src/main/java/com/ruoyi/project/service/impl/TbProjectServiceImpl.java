package com.ruoyi.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.project.domain.bo.TbProjectBo;
import com.ruoyi.project.domain.vo.TbProjectVo;
import com.ruoyi.project.domain.TbProject;
import com.ruoyi.project.mapper.TbProjectMapper;
import com.ruoyi.project.service.ITbProjectService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 项目管理 Service业务层处理
 *
 * @author ruoyi
 * @date 2023-09-14
 */
@RequiredArgsConstructor
@Service
public class TbProjectServiceImpl implements ITbProjectService {

    private final TbProjectMapper baseMapper;

    /**
     * 查询项目管理
     */
    @Override
    public TbProjectVo queryById(Long projectId){
        return baseMapper.selectVoById(projectId);
    }

    /**
     * 查询项目管理 列表
     */
    @Override
    public TableDataInfo<TbProjectVo> queryPageList(TbProjectBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TbProject> lqw = buildQueryWrapper(bo);
        Page<TbProjectVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询项目管理 列表
     */
    @Override
    public List<TbProjectVo> queryList(TbProjectBo bo) {
        LambdaQueryWrapper<TbProject> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TbProject> buildQueryWrapper(TbProjectBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TbProject> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getProjectId() != null, TbProject::getProjectId, bo.getProjectId());
        lqw.like(StringUtils.isNotBlank(bo.getNumber()), TbProject::getNumber, bo.getNumber());
        lqw.like(StringUtils.isNotBlank(bo.getName()), TbProject::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getType()), TbProject::getType, bo.getType());
        lqw.eq(StringUtils.isNotBlank(bo.getGtype()), TbProject::getGtype, bo.getGtype());
        return lqw;
    }

    /**
     * 新增项目管理
     */
    @Override
    public Boolean insertByBo(TbProjectBo bo) {
        TbProject add = BeanUtil.toBean(bo, TbProject.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setProjectId(add.getProjectId());
        }
        return flag;
    }

    /**
     * 修改项目管理
     */
    @Override
    public Boolean updateByBo(TbProjectBo bo) {
        TbProject update = BeanUtil.toBean(bo, TbProject.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TbProject entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除项目管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
