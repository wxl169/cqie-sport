package com.ruoyi.project.service;

import com.ruoyi.project.domain.TbProject;
import com.ruoyi.project.domain.vo.TbProjectVo;
import com.ruoyi.project.domain.bo.TbProjectBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 项目管理 Service接口
 *
 * @author ruoyi
 * @date 2023-09-13
 */
public interface ITbProjectService {

    /**
     * 查询项目管理 
     */
    TbProjectVo queryById(Long projectId);

    /**
     * 查询项目管理 列表
     */
    TableDataInfo<TbProjectVo> queryPageList(TbProjectBo bo, PageQuery pageQuery);

    /**
     * 查询项目管理 列表
     */
    List<TbProjectVo> queryList(TbProjectBo bo);

    /**
     * 新增项目管理 
     */
    Boolean insertByBo(TbProjectBo bo);

    /**
     * 修改项目管理 
     */
    Boolean updateByBo(TbProjectBo bo);

    /**
     * 校验并批量删除项目管理 信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
