package com.ruoyi.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.client.domain.entity.Arrangement;

import com.ruoyi.client.domain.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrangementMapper extends  BaseMapper<Arrangement> {

    /**
     * 裁判报名后，根据：project_id、info_id更新arrangement表中的referee字段
     * @param projectId
     * @param arrangementInfoId
     * @param refereeId
     * @return
     */
    public int updateRefereeInArrangement(@Param("projectId") Long projectId,
                                          @Param("arrangementInfoId") Long arrangementInfoId,
                                          @Param("refereeId") String refereeId);

    /**
     * 获取参数裁判数
     * @param projectId 项目id
     * @return 裁判数
     */
    String getRefereeNum(Long projectId);

    /**
     * 获取参数运动员数量
     * @param projectId 项目id
     * @return 运动员数量
     */
    Long getAthleteNum(Long projectId);
}
