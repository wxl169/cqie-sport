package com.ruoyi.people.service;

import com.ruoyi.people.domain.TbTeam;
import com.ruoyi.people.domain.vo.TbTeamVo;
import com.ruoyi.people.domain.bo.TbTeamBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 团体管理Service接口
 *
 * @author ruoyi
 * @date 2023-09-13
 */
public interface ITbTeamService {

    /**
     * 查询团体管理
     */
    TbTeamVo queryById(Long teamId);

    /**
     * 查询团体管理列表
     */
    TableDataInfo<TbTeamVo> queryPageList(TbTeamBo bo, PageQuery pageQuery);

    /**
     * 查询团体管理列表
     */
    List<TbTeamVo> queryList(TbTeamBo bo);

    /**
     * 新增团体管理
     */
    Boolean insertByBo(TbTeamBo bo);

    /**
     * 修改团体管理
     */
    Boolean updateByBo(TbTeamBo bo);

    /**
     * 校验并批量删除团体管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
