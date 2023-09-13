package com.ruoyi.match.service;

import com.ruoyi.match.domain.TbCompetition;
import com.ruoyi.match.domain.vo.TbCompetitionVo;
import com.ruoyi.match.domain.bo.TbCompetitionBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 比赛记录 Service接口
 *
 * @author ruoyi
 * @date 2023-09-13
 */
public interface ITbCompetitionService {

    /**
     * 查询比赛记录 
     */
    TbCompetitionVo queryById(Long competitionId);

    /**
     * 查询比赛记录 列表
     */
    TableDataInfo<TbCompetitionVo> queryPageList(TbCompetitionBo bo, PageQuery pageQuery);

    /**
     * 查询比赛记录 列表
     */
    List<TbCompetitionVo> queryList(TbCompetitionBo bo);

    /**
     * 新增比赛记录 
     */
    Boolean insertByBo(TbCompetitionBo bo);

    /**
     * 修改比赛记录 
     */
    Boolean updateByBo(TbCompetitionBo bo);

    /**
     * 校验并批量删除比赛记录 信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
