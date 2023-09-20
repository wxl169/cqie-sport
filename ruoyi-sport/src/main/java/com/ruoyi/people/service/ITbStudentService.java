package com.ruoyi.people.service;

import com.ruoyi.people.domain.TbStudent;
import com.ruoyi.people.domain.vo.TbStudentVo;
import com.ruoyi.people.domain.bo.TbStudentBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 学生管理Service接口
 *
 * @author ruoyi
 * @date 2023-09-14
 */
public interface ITbStudentService {

    /**
     * 查询学生管理
     */
    TbStudentVo queryById(Long studentId);

    /**
     * 查询学生管理列表
     */
    TableDataInfo<TbStudentVo> queryPageList(TbStudentBo bo, PageQuery pageQuery);

    /**
     * 查询学生管理列表
     */
    List<TbStudentVo> queryList(TbStudentBo bo);

    /**
     * 新增学生管理
     */
    Boolean insertByBo(TbStudentBo bo);

    /**
     * 修改学生管理
     */
    Boolean updateByBo(TbStudentBo bo);

    /**
     * 校验并批量删除学生管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
