package com.ruoyi.project.service;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.domain.bo.TbCollegeBo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.project.domain.vo.TbCollegeVo;

import java.util.Collection;
import java.util.List;

/**
 * 学院管理Service接口
 *
 * @author ruoyi
 * @date 2023-09-14
 */
public interface ITbCollegeService {

    /**
     * 查询学院管理
     */
    TbCollegeVo queryById(Long collegeId);

    /**
     * 查询学院管理列表
     */
    TableDataInfo<TbCollegeVo> queryPageList(TbCollegeBo bo, PageQuery pageQuery);

    /**
     * 查询学院管理列表
     */
    List<TbCollegeVo> queryList(TbCollegeBo bo);

    /**
     * 新增学院管理
     */
    Boolean insertByBo(TbCollegeBo bo);

    /**
     * 修改学院管理
     */
    Boolean updateByBo(TbCollegeBo bo);

    /**
     * 校验并批量删除学院管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
