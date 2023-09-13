package com.ruoyi.match.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.match.domain.bo.TbCompetitionBo;
import com.ruoyi.match.domain.vo.TbCompetitionVo;
import com.ruoyi.match.domain.TbCompetition;
import com.ruoyi.match.mapper.TbCompetitionMapper;
import com.ruoyi.match.service.ITbCompetitionService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 比赛记录 Service业务层处理
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@RequiredArgsConstructor
@Service
public class TbCompetitionServiceImpl implements ITbCompetitionService {

    private final TbCompetitionMapper baseMapper;

    /**
     * 查询比赛记录 
     */
    @Override
    public TbCompetitionVo queryById(Long competitionId){
        return baseMapper.selectVoById(competitionId);
    }

    /**
     * 查询比赛记录 列表
     */
    @Override
    public TableDataInfo<TbCompetitionVo> queryPageList(TbCompetitionBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TbCompetition> lqw = buildQueryWrapper(bo);
        Page<TbCompetitionVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询比赛记录 列表
     */
    @Override
    public List<TbCompetitionVo> queryList(TbCompetitionBo bo) {
        LambdaQueryWrapper<TbCompetition> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TbCompetition> buildQueryWrapper(TbCompetitionBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TbCompetition> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getArrangementId() != null, TbCompetition::getArrangementId, bo.getArrangementId());
        lqw.eq(StringUtils.isNotBlank(bo.getTypeId()), TbCompetition::getTypeId, bo.getTypeId());
        lqw.eq(StringUtils.isNotBlank(bo.getType()), TbCompetition::getType, bo.getType());
        lqw.eq(StringUtils.isNotBlank(bo.getResult()), TbCompetition::getResult, bo.getResult());
        lqw.eq(bo.getScore() != null, TbCompetition::getScore, bo.getScore());
        lqw.eq(StringUtils.isNotBlank(bo.getIsEffective()), TbCompetition::getIsEffective, bo.getIsEffective());
        lqw.eq(StringUtils.isNotBlank(bo.getReason()), TbCompetition::getReason, bo.getReason());
        lqw.eq(StringUtils.isNotBlank(bo.getOther()), TbCompetition::getOther, bo.getOther());
        return lqw;
    }

    /**
     * 新增比赛记录 
     */
    @Override
    public Boolean insertByBo(TbCompetitionBo bo) {
        TbCompetition add = BeanUtil.toBean(bo, TbCompetition.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setCompetitionId(add.getCompetitionId());
        }
        return flag;
    }

    /**
     * 修改比赛记录 
     */
    @Override
    public Boolean updateByBo(TbCompetitionBo bo) {
        TbCompetition update = BeanUtil.toBean(bo, TbCompetition.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TbCompetition entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除比赛记录 
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
