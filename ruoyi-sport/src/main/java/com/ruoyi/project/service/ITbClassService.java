package com.ruoyi.project.service;

import com.ruoyi.project.domain.TbClass;
import com.ruoyi.project.domain.vo.TbClassVo;
import com.ruoyi.project.domain.bo.TbClassBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 班级管理Service接口
 *
 * @author ruoyi
 * @date 2023-09-14
 */
public interface ITbClassService {

    /**
     * 查询班级管理
     */
    TbClassVo queryById(Long classId);

    /**
     * 查询班级管理列表
     */
    TableDataInfo<TbClassVo> queryPageList(TbClassBo bo, PageQuery pageQuery);

    /**
     * 查询班级管理列表
     */
    List<TbClassVo> queryList(TbClassBo bo);

    /**
     * 新增班级管理
     */
    Boolean insertByBo(TbClassBo bo);

    /**
     * 修改班级管理
     */
    Boolean updateByBo(TbClassBo bo);

    /**
     * 校验并批量删除班级管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
