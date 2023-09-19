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
import com.ruoyi.people.domain.bo.TbRefereeBo;
import com.ruoyi.people.domain.vo.TbRefereeVo;
import com.ruoyi.people.domain.TbReferee;
import com.ruoyi.people.mapper.TbRefereeMapper;
import com.ruoyi.people.service.ITbRefereeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 裁判员管理Service业务层处理
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@RequiredArgsConstructor
@Service
public class TbRefereeServiceImpl implements ITbRefereeService {

    private final TbRefereeMapper baseMapper;

    /**
     * 查询裁判员管理
     */
    @Override
    public TbRefereeVo queryById(Long refereeId){
        return baseMapper.selectVoById(refereeId);
    }

    /**
     * 查询裁判员管理列表
     */
    @Override
    public TableDataInfo<TbRefereeVo> queryPageList(TbRefereeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TbReferee> lqw = buildQueryWrapper(bo);
        Page<TbRefereeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询裁判员管理列表
     */
    @Override
    public List<TbRefereeVo> queryList(TbRefereeBo bo) {
        LambdaQueryWrapper<TbReferee> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TbReferee> buildQueryWrapper(TbRefereeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TbReferee> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getNumber()), TbReferee::getNumber, bo.getNumber());
        lqw.like(StringUtils.isNotBlank(bo.getName()), TbReferee::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getGender()), TbReferee::getGender, bo.getGender());
        lqw.eq(StringUtils.isNotBlank(bo.getIdnumber()), TbReferee::getIdnumber, bo.getIdnumber());
        lqw.eq(StringUtils.isNotBlank(bo.getPhoneNumber()), TbReferee::getPhoneNumber, bo.getPhoneNumber());
        lqw.eq(bo.getBirthday() != null, TbReferee::getBirthday, bo.getBirthday());
        return lqw;
    }

    /**
     * 新增裁判员管理
     */
    @Override
    public Boolean insertByBo(TbRefereeBo bo) {
        TbReferee add = BeanUtil.toBean(bo, TbReferee.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setRefereeId(add.getRefereeId());
        }
        return flag;
    }

    /**
     * 修改裁判员管理
     */
    @Override
    public Boolean updateByBo(TbRefereeBo bo) {
        TbReferee update = BeanUtil.toBean(bo, TbReferee.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TbReferee entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除裁判员管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
