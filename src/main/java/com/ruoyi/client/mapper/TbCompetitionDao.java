package com.ruoyi.client.mapper;

import com.ruoyi.client.domain.entity.TbCompetition;

public interface TbCompetitionDao {
    int deleteByPrimaryKey(Integer competitionId);

    int insert(TbCompetition record);

    int insertSelective(TbCompetition record);

    TbCompetition selectByPrimaryKey(Integer competitionId);

    int updateByPrimaryKeySelective(TbCompetition record);

    int updateByPrimaryKey(TbCompetition record);
}