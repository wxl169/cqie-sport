package com.ruoyi.client.mapper;

import com.ruoyi.client.domain.entity.TCompetition;

import java.util.List;

public interface TbCompetitionDao {
    int deleteByPrimaryKey(Integer competitionId);

    int insert(TCompetition record);

    int insertSelective(TCompetition record);

    TCompetition selectByPrimaryKey(Integer competitionId);

    int updateByPrimaryKeySelective(TCompetition record);

    int updateByPrimaryKey(TCompetition record);

}
