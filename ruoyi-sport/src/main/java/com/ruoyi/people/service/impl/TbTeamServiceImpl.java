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
import com.ruoyi.people.domain.bo.TbTeamBo;
import com.ruoyi.people.domain.vo.TbTeamVo;
import com.ruoyi.people.domain.TbTeam;
import com.ruoyi.people.mapper.TbTeamMapper;
import com.ruoyi.people.service.ITbTeamService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 团体管理Service业务层处理
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@RequiredArgsConstructor
@Service
public class TbTeamServiceImpl implements ITbTeamService {

    private final TbTeamMapper baseMapper;

    /**
     * 查询团体管理
     */
    @Override
    public TbTeamVo queryById(Long teamId){
        return baseMapper.selectVoById(teamId);
    }

    /**
     * 查询团体管理列表
     */
    @Override
    public TableDataInfo<TbTeamVo> queryPageList(TbTeamBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TbTeam> lqw = buildQueryWrapper(bo);
        Page<TbTeamVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询团体管理列表
     */
    @Override
    public List<TbTeamVo> queryList(TbTeamBo bo) {
        LambdaQueryWrapper<TbTeam> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TbTeam> buildQueryWrapper(TbTeamBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TbTeam> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAthleteId() != null, TbTeam::getAthleteId, bo.getAthleteId());
        lqw.eq(StringUtils.isNotBlank(bo.getNumber()), TbTeam::getNumber, bo.getNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getOther()), TbTeam::getOther, bo.getOther());
        return lqw;
    }

    /**
     * 新增团体管理
     */
    @Override
    public Boolean insertByBo(TbTeamBo bo) {
        TbTeam add = BeanUtil.toBean(bo, TbTeam.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setTeamId(add.getTeamId());
        }
        return flag;
    }

    /**
     * 修改团体管理
     */
    @Override
    public Boolean updateByBo(TbTeamBo bo) {
        TbTeam update = BeanUtil.toBean(bo, TbTeam.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TbTeam entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除团体管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
