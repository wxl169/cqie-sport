package com.ruoyi.client.mapper;

import com.ruoyi.client.domain.entity.TArrangement;
import com.ruoyi.client.domain.entity.TCompetition;

import java.util.List;

public interface TbArrangementDao {
    int deleteByPrimaryKey(Integer arrangementId);

    int insert(TArrangement record);

    int insertSelective(TArrangement record);

    TArrangement selectByPrimaryKey(Integer arrangementId);

    int updateByPrimaryKeySelective(TArrangement record);

    int updateByPrimaryKey(TArrangement record);

    List<TCompetition> selectByProjectId(String projectId);
}
