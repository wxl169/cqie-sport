package com.ruoyi.people.service;

import com.ruoyi.people.domain.TbUser;
import com.ruoyi.people.domain.vo.TbUserVo;
import com.ruoyi.people.domain.bo.TbUserBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 用户管理Service接口
 *
 * @author ruoyi
 * @date 2023-09-15
 */
public interface ITbUserService {

    /**
     * 查询用户管理
     */
    TbUserVo queryById(Long userId);

    /**
     * 查询用户管理列表
     */
    TableDataInfo<TbUserVo> queryPageList(TbUserBo bo, PageQuery pageQuery);

    /**
     * 查询用户管理列表
     */
    List<TbUserVo> queryList(TbUserBo bo);

    /**
     * 新增用户管理
     */
    Boolean insertByBo(TbUserBo bo);

    /**
     * 修改用户管理
     */
    Boolean updateByBo(TbUserBo bo);

    /**
     * 校验并批量删除用户管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
