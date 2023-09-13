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
import com.ruoyi.match.domain.bo.TbArrangementBo;
import com.ruoyi.match.domain.vo.TbArrangementVo;
import com.ruoyi.match.domain.TbArrangement;
import com.ruoyi.match.mapper.TbArrangementMapper;
import com.ruoyi.match.service.ITbArrangementService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 安排 Service业务层处理
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@RequiredArgsConstructor
@Service
public class TbArrangementServiceImpl implements ITbArrangementService {

    private final TbArrangementMapper baseMapper;

    /**
     * 查询安排 
     */
    @Override
    public TbArrangementVo queryById(Long arrangementId){
        return baseMapper.selectVoById(arrangementId);
    }

    /**
     * 查询安排 列表
     */
    @Override
    public TableDataInfo<TbArrangementVo> queryPageList(TbArrangementBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TbArrangement> lqw = buildQueryWrapper(bo);
        Page<TbArrangementVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询安排 列表
     */
    @Override
    public List<TbArrangementVo> queryList(TbArrangementBo bo) {
        LambdaQueryWrapper<TbArrangement> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TbArrangement> buildQueryWrapper(TbArrangementBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TbArrangement> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getProjectId() != null, TbArrangement::getProjectId, bo.getProjectId());
        lqw.eq(bo.getRefereeId() != null, TbArrangement::getRefereeId, bo.getRefereeId());
        lqw.eq(bo.getTypeId() != null, TbArrangement::getTypeId, bo.getTypeId());
        lqw.eq(bo.getInfoId() != null, TbArrangement::getInfoId, bo.getInfoId());
        lqw.eq(StringUtils.isNotBlank(bo.getType()), TbArrangement::getType, bo.getType());
        lqw.eq(StringUtils.isNotBlank(bo.getIsCancel()), TbArrangement::getIsCancel, bo.getIsCancel());
        lqw.eq(StringUtils.isNotBlank(bo.getReason()), TbArrangement::getReason, bo.getReason());
        lqw.eq(StringUtils.isNotBlank(bo.getOther()), TbArrangement::getOther, bo.getOther());
        return lqw;
    }

    /**
     * 新增安排 
     */
    @Override
    public Boolean insertByBo(TbArrangementBo bo) {
        TbArrangement add = BeanUtil.toBean(bo, TbArrangement.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setArrangementId(add.getArrangementId());
        }
        return flag;
    }

    /**
     * 修改安排 
     */
    @Override
    public Boolean updateByBo(TbArrangementBo bo) {
        TbArrangement update = BeanUtil.toBean(bo, TbArrangement.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TbArrangement entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除安排 
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
