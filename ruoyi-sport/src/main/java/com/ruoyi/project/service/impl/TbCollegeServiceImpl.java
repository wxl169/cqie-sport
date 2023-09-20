package com.ruoyi.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.domain.TbCollege;
import com.ruoyi.project.domain.bo.TbCollegeBo;
import com.ruoyi.project.domain.vo.TbCollegeVo;
import com.ruoyi.project.mapper.TbCollegeMapper;
import com.ruoyi.project.service.ITbCollegeService;
import com.ruoyi.system.mapper.SysDictDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 学院管理Service业务层处理
 *
 * @author ruoyi
 * @date 2023-09-14
 */
@RequiredArgsConstructor
@Service
public class TbCollegeServiceImpl implements ITbCollegeService {

    private final TbCollegeMapper baseMapper;
    private final SysDictDataMapper sysDictDataMapper;

    /**
     * 查询学院管理
     */
    @Override
    public TbCollegeVo queryById(Long collegeId) {
        return baseMapper.selectVoById(collegeId);
    }

    /**
     * 查询学院管理列表
     */
    @Override
    public TableDataInfo<TbCollegeVo> queryPageList(TbCollegeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TbCollege> lqw = buildQueryWrapper(bo);
        Page<TbCollegeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询学院管理列表
     */
    @Override
    public List<TbCollegeVo> queryList(TbCollegeBo bo) {
        LambdaQueryWrapper<TbCollege> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TbCollege> buildQueryWrapper(TbCollegeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TbCollege> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), TbCollege::getName, bo.getName());
        return lqw;
    }

    /**
     * 新增学院管理
     */
    @Override
    public Boolean insertByBo(TbCollegeBo bo) {
        TbCollege add = BeanUtil.toBean(bo, TbCollege.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setCollegeId(add.getCollegeId());

            //添加到学院信息到字典
            SysDictData sysDictData = new SysDictData();
            sysDictData.setDictLabel(bo.getName());
            sysDictData.setDictValue(String.valueOf(bo.getCollegeId()));
            sysDictData.setDictType("college_name");
            sysDictDataMapper.insert(sysDictData);
        }
        return flag;
    }

    /**
     * 修改学院管理
     */
    @Override
    public Boolean updateByBo(TbCollegeBo bo) {
        TbCollege update = BeanUtil.toBean(bo, TbCollege.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TbCollege entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除学院管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        List<TbCollege> tbColleges = baseMapper.selectBatchIds(ids);
        List<String> collegeNames = tbColleges.stream().map(TbCollege::getName).collect(Collectors.toList());
        LambdaQueryWrapper<SysDictData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysDictData::getDictLabel, collegeNames);
        sysDictDataMapper.delete(queryWrapper);
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
