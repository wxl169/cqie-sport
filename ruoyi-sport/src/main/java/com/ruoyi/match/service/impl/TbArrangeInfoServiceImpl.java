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
import com.ruoyi.match.domain.bo.TbArrangeInfoBo;
import com.ruoyi.match.domain.vo.TbArrangeInfoVo;
import com.ruoyi.match.domain.TbArrangeInfo;
import com.ruoyi.match.mapper.TbArrangeInfoMapper;
import com.ruoyi.match.service.ITbArrangeInfoService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 安排信息单元 Service业务层处理
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@RequiredArgsConstructor
@Service
public class TbArrangeInfoServiceImpl implements ITbArrangeInfoService {

    private final TbArrangeInfoMapper baseMapper;

    /**
     * 查询安排信息单元 
     */
    @Override
    public TbArrangeInfoVo queryById(Long arrangeInfoId){
        return baseMapper.selectVoById(arrangeInfoId);
    }

    /**
     * 查询安排信息单元 列表
     */
    @Override
    public TableDataInfo<TbArrangeInfoVo> queryPageList(TbArrangeInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TbArrangeInfo> lqw = buildQueryWrapper(bo);
        Page<TbArrangeInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询安排信息单元 列表
     */
    @Override
    public List<TbArrangeInfoVo> queryList(TbArrangeInfoBo bo) {
        LambdaQueryWrapper<TbArrangeInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TbArrangeInfo> buildQueryWrapper(TbArrangeInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TbArrangeInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getProjectId() != null, TbArrangeInfo::getProjectId, bo.getProjectId());
        lqw.eq(bo.getTime() != null, TbArrangeInfo::getTime, bo.getTime());
        lqw.eq(StringUtils.isNotBlank(bo.getPlace()), TbArrangeInfo::getPlace, bo.getPlace());
        lqw.eq(StringUtils.isNotBlank(bo.getContent()), TbArrangeInfo::getContent, bo.getContent());
        lqw.eq(StringUtils.isNotBlank(bo.getOther()), TbArrangeInfo::getOther, bo.getOther());
        return lqw;
    }

    /**
     * 新增安排信息单元 
     */
    @Override
    public Boolean insertByBo(TbArrangeInfoBo bo) {
        TbArrangeInfo add = BeanUtil.toBean(bo, TbArrangeInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setArrangeInfoId(add.getArrangeInfoId());
        }
        return flag;
    }

    /**
     * 修改安排信息单元 
     */
    @Override
    public Boolean updateByBo(TbArrangeInfoBo bo) {
        TbArrangeInfo update = BeanUtil.toBean(bo, TbArrangeInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TbArrangeInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除安排信息单元 
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
