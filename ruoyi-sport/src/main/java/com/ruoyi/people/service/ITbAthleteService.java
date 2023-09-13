package com.ruoyi.people.service;

import com.ruoyi.people.domain.TbAthlete;
import com.ruoyi.people.domain.vo.TbAthleteVo;
import com.ruoyi.people.domain.bo.TbAthleteBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 运动员管理 Service接口
 *
 * @author ruoyi
 * @date 2023-09-13
 */
public interface ITbAthleteService {

    /**
     * 查询运动员管理 
     */
    TbAthleteVo queryById(Long athleteId);

    /**
     * 查询运动员管理 列表
     */
    TableDataInfo<TbAthleteVo> queryPageList(TbAthleteBo bo, PageQuery pageQuery);

    /**
     * 查询运动员管理 列表
     */
    List<TbAthleteVo> queryList(TbAthleteBo bo);

    /**
     * 新增运动员管理 
     */
    Boolean insertByBo(TbAthleteBo bo);

    /**
     * 修改运动员管理 
     */
    Boolean updateByBo(TbAthleteBo bo);

    /**
     * 校验并批量删除运动员管理 信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
