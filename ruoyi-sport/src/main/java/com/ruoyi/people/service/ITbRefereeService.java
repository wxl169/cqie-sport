package com.ruoyi.people.service;

import com.ruoyi.people.domain.TbReferee;
import com.ruoyi.people.domain.vo.TbRefereeVo;
import com.ruoyi.people.domain.bo.TbRefereeBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 裁判员管理Service接口
 *
 * @author ruoyi
 * @date 2023-09-13
 */
public interface ITbRefereeService {

    /**
     * 查询裁判员管理
     */
    TbRefereeVo queryById(Long refereeId);

    /**
     * 查询裁判员管理列表
     */
    TableDataInfo<TbRefereeVo> queryPageList(TbRefereeBo bo, PageQuery pageQuery);

    /**
     * 查询裁判员管理列表
     */
    List<TbRefereeVo> queryList(TbRefereeBo bo);

    /**
     * 新增裁判员管理
     */
    Boolean insertByBo(TbRefereeBo bo);

    /**
     * 修改裁判员管理
     */
    Boolean updateByBo(TbRefereeBo bo);

    /**
     * 校验并批量删除裁判员管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
