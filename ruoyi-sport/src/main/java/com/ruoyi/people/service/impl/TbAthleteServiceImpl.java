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
import com.ruoyi.people.domain.bo.TbAthleteBo;
import com.ruoyi.people.domain.vo.TbAthleteVo;
import com.ruoyi.people.domain.TbAthlete;
import com.ruoyi.people.mapper.TbAthleteMapper;
import com.ruoyi.people.service.ITbAthleteService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 运动员管理 Service业务层处理
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@RequiredArgsConstructor
@Service
public class TbAthleteServiceImpl implements ITbAthleteService {

    private final TbAthleteMapper baseMapper;

    /**
     * 查询运动员管理 
     */
    @Override
    public TbAthleteVo queryById(Long athleteId){
        return baseMapper.selectVoById(athleteId);
    }

    /**
     * 查询运动员管理 列表
     */
    @Override
    public TableDataInfo<TbAthleteVo> queryPageList(TbAthleteBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TbAthlete> lqw = buildQueryWrapper(bo);
        Page<TbAthleteVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询运动员管理 列表
     */
    @Override
    public List<TbAthleteVo> queryList(TbAthleteBo bo) {
        LambdaQueryWrapper<TbAthlete> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TbAthlete> buildQueryWrapper(TbAthleteBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TbAthlete> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getStudentId() != null, TbAthlete::getStudentId, bo.getStudentId());
        lqw.eq(StringUtils.isNotBlank(bo.getNumber()), TbAthlete::getNumber, bo.getNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getOther()), TbAthlete::getOther, bo.getOther());
        return lqw;
    }

    /**
     * 新增运动员管理 
     */
    @Override
    public Boolean insertByBo(TbAthleteBo bo) {
        TbAthlete add = BeanUtil.toBean(bo, TbAthlete.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAthleteId(add.getAthleteId());
        }
        return flag;
    }

    /**
     * 修改运动员管理 
     */
    @Override
    public Boolean updateByBo(TbAthleteBo bo) {
        TbAthlete update = BeanUtil.toBean(bo, TbAthlete.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TbAthlete entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除运动员管理 
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
