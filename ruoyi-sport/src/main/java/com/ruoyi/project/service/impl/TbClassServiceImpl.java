package com.ruoyi.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.constant.CacheNames;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.domain.TbClass;
import com.ruoyi.project.domain.bo.TbClassBo;
import com.ruoyi.project.domain.vo.TbClassVo;
import com.ruoyi.project.mapper.TbClassMapper;
import com.ruoyi.project.service.ITbClassService;
import com.ruoyi.system.mapper.SysDictDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 班级管理Service业务层处理
 *
 * @author ruoyi
 * @date 2023-09-14
 */
@RequiredArgsConstructor
@Service
public class TbClassServiceImpl implements ITbClassService {

    private final TbClassMapper baseMapper;
    private final SysDictDataMapper sysDictDataMapper;

    /**
     * 查询班级管理
     */
    @Override
    public TbClassVo queryById(Long classId) {
        return baseMapper.selectVoById(classId);
    }

    /**
     * 查询班级管理列表
     */
    @Override
    public TableDataInfo<TbClassVo> queryPageList(TbClassBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TbClass> lqw = buildQueryWrapper(bo);
        Page<TbClassVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询班级管理列表
     */
    @Override
    public List<TbClassVo> queryList(TbClassBo bo) {
        LambdaQueryWrapper<TbClass> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TbClass> buildQueryWrapper(TbClassBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TbClass> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCollegeId() != null, TbClass::getCollegeId, bo.getCollegeId());
        lqw.like(StringUtils.isNotBlank(bo.getName()), TbClass::getName, bo.getName());
        return lqw;
    }

    /**
     * 新增班级管理
     */
    @Override
    @CacheEvict(cacheNames = CacheNames.SYS_DICT, key = "'class_name'")
    public Boolean insertByBo(TbClassBo bo) {
        TbClass add = BeanUtil.toBean(bo, TbClass.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setClassId(add.getClassId());
            //添加到学院信息到字典
            SysDictData sysDictData = new SysDictData();
            sysDictData.setDictLabel(bo.getName());
            sysDictData.setDictValue(String.valueOf(bo.getClassId()));
            sysDictData.setDictType("class_name");
            sysDictDataMapper.insert(sysDictData);
        }
        return flag;
    }

    /**
     * 修改班级管理
     */
    @Override
    @CacheEvict(cacheNames = CacheNames.SYS_DICT, key = "'class_name'")
    public Boolean updateByBo(TbClassBo bo) {
        TbClass update = BeanUtil.toBean(bo, TbClass.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TbClass entity) {
        //TODO 做一些数据校验,如唯一约束
        LambdaQueryWrapper<TbClass> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TbClass::getName, entity.getName());
        TbClass tbClass = baseMapper.selectOne(queryWrapper);
        if (tbClass!=null){
            throw new BaseException("班级已存在");
        }
    }

    /**
     * 批量删除班级管理
     */
    @Override
    @CacheEvict(cacheNames = CacheNames.SYS_DICT, key = "'class_name'")
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        List<TbClass> tbClasses = baseMapper.selectBatchIds(ids);
        List<String> collect = tbClasses.stream().map(TbClass::getName).collect(Collectors.toList());
        LambdaQueryWrapper<SysDictData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysDictData::getDictLabel, collect);
        sysDictDataMapper.delete(queryWrapper);
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
