package com.ruoyi.people.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.people.domain.bo.TbUserBo;
import com.ruoyi.people.domain.vo.TbUserVo;
import com.ruoyi.people.domain.TbUser;
import com.ruoyi.people.mapper.TbUserMapper;
import com.ruoyi.people.service.ITbUserService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 用户管理Service业务层处理
 *
 * @author ruoyi
 * @date 2023-09-15
 */
@RequiredArgsConstructor
@Service
public class TbUserServiceImpl implements ITbUserService {

    private final TbUserMapper baseMapper;

    /**
     * 查询用户管理
     */
    @Override
    public TbUserVo queryById(Long userId) {
        return baseMapper.selectVoById(userId);
    }

    /**
     * 查询用户管理列表
     */
    @Override
    public TableDataInfo<TbUserVo> queryPageList(TbUserBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TbUser> lqw = buildQueryWrapper(bo);
        Page<TbUserVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询用户管理列表
     */
    @Override
    public List<TbUserVo> queryList(TbUserBo bo) {
        LambdaQueryWrapper<TbUser> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TbUser> buildQueryWrapper(TbUserBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TbUser> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getTypeId() != null, TbUser::getTypeId, bo.getTypeId());
        lqw.eq(StringUtils.isNotBlank(bo.getType()), TbUser::getType, bo.getType());
        lqw.like(StringUtils.isNotBlank(bo.getRealname()), TbUser::getRealname, bo.getRealname());
        lqw.eq(StringUtils.isNotBlank(bo.getGender()), TbUser::getGender, bo.getGender());
        lqw.eq(StringUtils.isNotBlank(bo.getPhoneNumber()), TbUser::getPhoneNumber, bo.getPhoneNumber());
        lqw.eq(bo.getBirthday() != null, TbUser::getBirthday, bo.getBirthday());
        return lqw;
    }

    /**
     * 新增用户管理
     */
    @Override
    public Boolean insertByBo(TbUserBo bo) {
        TbUser add = BeanUtil.toBean(bo, TbUser.class);
        add.setPassword(add.getPassword() == null ?
            BCrypt.hashpw("123456") : BCrypt.hashpw(add.getPassword()));
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setUserId(add.getUserId());
        }
        return flag;
    }

    /**
     * 修改用户管理
     */
    @Override
    public Boolean updateByBo(TbUserBo bo) {
        TbUser update = BeanUtil.toBean(bo, TbUser.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TbUser entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除用户管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
