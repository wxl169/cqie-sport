package com.ruoyi.match.service;

import com.ruoyi.match.domain.TbArrangement;
import com.ruoyi.match.domain.vo.TbArrangementVo;
import com.ruoyi.match.domain.bo.TbArrangementBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 安排 Service接口
 *
 * @author ruoyi
 * @date 2023-09-13
 */
public interface ITbArrangementService {

    /**
     * 查询安排 
     */
    TbArrangementVo queryById(Long arrangementId);

    /**
     * 查询安排 列表
     */
    TableDataInfo<TbArrangementVo> queryPageList(TbArrangementBo bo, PageQuery pageQuery);

    /**
     * 查询安排 列表
     */
    List<TbArrangementVo> queryList(TbArrangementBo bo);

    /**
     * 新增安排 
     */
    Boolean insertByBo(TbArrangementBo bo);

    /**
     * 修改安排 
     */
    Boolean updateByBo(TbArrangementBo bo);

    /**
     * 校验并批量删除安排 信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
