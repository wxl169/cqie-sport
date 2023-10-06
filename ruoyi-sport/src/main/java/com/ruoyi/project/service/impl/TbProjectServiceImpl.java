package com.ruoyi.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.constant.CacheNames;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.match.domain.TbArrangement;
import com.ruoyi.match.mapper.TbArrangementMapper;
import com.ruoyi.project.domain.TbClass;
import com.ruoyi.project.domain.TbProject;
import com.ruoyi.project.domain.bo.TbProjectBo;
import com.ruoyi.project.domain.vo.TbProjectVo;
import com.ruoyi.project.mapper.TbProjectMapper;
import com.ruoyi.project.service.ITbProjectService;
import com.ruoyi.system.mapper.SysDictDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private final TbArrangementMapper arrangementMapper;

    private final SysDictDataMapper sysDictDataMapper;

    /**
     * 查询项目管理
     */
    @Override
    public TbProjectVo queryById(Long projectId) {
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
    @CacheEvict(cacheNames = CacheNames.SYS_DICT, key = "'project_name'")
    public Boolean insertByBo(TbProjectBo bo) {
        TbProject add = BeanUtil.toBean(bo, TbProject.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setProjectId(add.getProjectId());
            //添加项目信息到字典
            SysDictData sysDictData = new SysDictData();
            sysDictData.setDictLabel(bo.getName());
            sysDictData.setDictValue(String.valueOf(bo.getProjectId()));
            sysDictData.setDictType("project_name");
            sysDictDataMapper.insert(sysDictData);
        }
        return flag;
    }

    /**
     * 修改项目管理
     */
    @Override
    @CacheEvict(cacheNames = CacheNames.SYS_DICT, key = "'project_name'")
    public Boolean updateByBo(TbProjectBo bo) {
        TbProject update = BeanUtil.toBean(bo, TbProject.class);
        LambdaQueryWrapper<TbArrangement> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TbArrangement::getProjectId, update.getProjectId());
        List<TbArrangement> tbArrangements = arrangementMapper.selectList(queryWrapper);
        if(!(tbArrangements.size() == 0)){
            if (tbArrangements.size() > update.getUpnum()) {
                throw new BaseException("运动员已报名人数为 " + tbArrangements.size() + "人大于" + update.getUpnum() + "人,无法修改");
            }
            TbArrangement tbArrangement = tbArrangements.get(0);
            String refereeId = tbArrangement.getRefereeId();
            int count = refereeId.split(",").length;
            if (count > update.getRenum()) {
                throw new BaseException("裁判员已报名人数为 " + count + "人大于" + update.getRenum() + "人,无法修改");
            }
        }
        String name = baseMapper.selectById(update.getProjectId()).getName();
        LambdaUpdateWrapper<SysDictData> updateWrapper = new LambdaUpdateWrapper<>();
        if(baseMapper.updateById(update) > 0){
            updateWrapper.eq(SysDictData::getDictType,"project_name")
                .eq(SysDictData::getDictLabel,name)
                .set(SysDictData::getDictLabel,update.getName());
        }
        return sysDictDataMapper.update(null,updateWrapper) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TbProject entity) {
        //TODO 做一些数据校验,如唯一约束
        LambdaQueryWrapper<TbProject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TbProject::getName, entity.getName());
        TbProject tbProject = baseMapper.selectOne(queryWrapper);
        if (tbProject!= null){
            throw new BaseException("项目已存在");
        }
    }

    /**
     * 批量删除项目管理
     */
    @Override
    @CacheEvict(cacheNames = CacheNames.SYS_DICT, key = "'project_name'")
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }

        List<TbProject> tbProjects = baseMapper.selectBatchIds(ids);
        List<String> collect = tbProjects.stream().map(TbProject::getName).collect(Collectors.toList());
        LambdaQueryWrapper<SysDictData> projectQueryWrapper = new LambdaQueryWrapper<>();
        projectQueryWrapper.in(SysDictData::getDictLabel, collect);
        sysDictDataMapper.delete(projectQueryWrapper);


        LambdaQueryWrapper<TbArrangement> queryWrapper;
        List<TbArrangement> toDelete = new ArrayList<>();
        for (Long id : ids) {
            queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TbArrangement::getProjectId, id);
            List<TbArrangement> arrangements = arrangementMapper.selectList(queryWrapper);
            toDelete.addAll(arrangements);
        }
        if (!toDelete.isEmpty()) {
            arrangementMapper.deleteBatchIds(toDelete.stream()
                .map(TbArrangement::getArrangementId)
                .collect(Collectors.toList()));
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
