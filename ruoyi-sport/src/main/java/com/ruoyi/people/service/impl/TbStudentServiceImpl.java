package com.ruoyi.people.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.people.domain.bo.TbStudentBo;
import com.ruoyi.people.domain.vo.TbStudentVo;
import com.ruoyi.people.domain.TbStudent;
import com.ruoyi.people.mapper.TbStudentMapper;
import com.ruoyi.people.service.ITbStudentService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 学生管理Service业务层处理
 *
 * @author ruoyi
 * @date 2023-09-14
 */
@RequiredArgsConstructor
@Service
public class TbStudentServiceImpl implements ITbStudentService {

    private final TbStudentMapper baseMapper;

    /**
     * 查询学生管理
     */
    @Override
    public TbStudentVo queryById(Long studentId){
        return baseMapper.selectVoById(studentId);
    }

    /**
     * 查询学生管理列表
     */
    @Override
    public TableDataInfo<TbStudentVo> queryPageList(TbStudentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TbStudent> lqw = buildQueryWrapper(bo);
        Page<TbStudentVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询学生管理列表
     */
    @Override
    public List<TbStudentVo> queryList(TbStudentBo bo) {
        LambdaQueryWrapper<TbStudent> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TbStudent> buildQueryWrapper(TbStudentBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TbStudent> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getClassId() != null, TbStudent::getClassId, bo.getClassId());
        lqw.eq(bo.getCollegeId() != null, TbStudent::getCollegeId, bo.getCollegeId());
        lqw.eq(StringUtils.isNotBlank(bo.getIsAthlete()), TbStudent::getIsAthlete, bo.getIsAthlete());
        lqw.eq(StringUtils.isNotBlank(bo.getStudentNumber()), TbStudent::getStudentNumber, bo.getStudentNumber());
        lqw.like(StringUtils.isNotBlank(bo.getName()), TbStudent::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getGender()), TbStudent::getGender, bo.getGender());
        lqw.between(params.get("beginBirthday") != null && params.get("endBirthday") != null,
            TbStudent::getBirthday ,params.get("beginBirthday"), params.get("endBirthday"));
        lqw.between(params.get("beginCreditScore") != null && params.get("endCreditScore") != null,
            TbStudent::getCreditScore ,params.get("beginCreditScore"), params.get("endCreditScore"));
        return lqw;
    }

    /**
     * 新增学生管理
     */
    @Override
    public Boolean insertByBo(TbStudentBo bo) {
        TbStudent add = BeanUtil.toBean(bo, TbStudent.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setStudentId(add.getStudentId());
        }
        return flag;
    }

    /**
     * 修改学生管理
     */
    @Override
    public Boolean updateByBo(TbStudentBo bo) {
        TbStudent update = BeanUtil.toBean(bo, TbStudent.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TbStudent entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除学生管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
