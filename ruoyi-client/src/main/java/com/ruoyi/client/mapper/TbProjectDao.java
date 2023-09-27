package com.ruoyi.client.mapper;

import com.ruoyi.client.domain.entity.TbProject;

import java.util.List;

public interface TbProjectDao {
    int deleteByPrimaryKey(Integer projectId);

    int insert(TbProject record);

    int insertSelective(TbProject record);

    TbProject selectByPrimaryKey(Integer projectId);

    int updateByPrimaryKeySelective(TbProject record);

    int updateByPrimaryKey(TbProject record);

    List<TbProject> find();
}
