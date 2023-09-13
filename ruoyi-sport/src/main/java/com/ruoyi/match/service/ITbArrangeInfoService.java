package com.ruoyi.match.service;

import com.ruoyi.match.domain.TbArrangeInfo;
import com.ruoyi.match.domain.vo.TbArrangeInfoVo;
import com.ruoyi.match.domain.bo.TbArrangeInfoBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 安排信息单元 Service接口
 *
 * @author ruoyi
 * @date 2023-09-13
 */
public interface ITbArrangeInfoService {

    /**
     * 查询安排信息单元 
     */
    TbArrangeInfoVo queryById(Long arrangeInfoId);

    /**
     * 查询安排信息单元 列表
     */
    TableDataInfo<TbArrangeInfoVo> queryPageList(TbArrangeInfoBo bo, PageQuery pageQuery);

    /**
     * 查询安排信息单元 列表
     */
    List<TbArrangeInfoVo> queryList(TbArrangeInfoBo bo);

    /**
     * 新增安排信息单元 
     */
    Boolean insertByBo(TbArrangeInfoBo bo);

    /**
     * 修改安排信息单元 
     */
    Boolean updateByBo(TbArrangeInfoBo bo);

    /**
     * 校验并批量删除安排信息单元 信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
