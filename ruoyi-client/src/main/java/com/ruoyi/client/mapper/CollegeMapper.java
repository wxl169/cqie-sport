package com.ruoyi.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.client.domain.entity.Class;
import com.ruoyi.client.domain.entity.College;

import java.util.List;

public interface CollegeMapper extends BaseMapper<College> {
    int deleteByPrimaryKey(Long collegeId);

    int insert(College record);

    int insertSelective(College record);

    College selectByPrimaryKey(Long collegeId);

    int updateByPrimaryKeySelective(College record);

    int updateByPrimaryKey(College record);

    //按学院总分_降序展示
    List<College> selectAll();

    //根据学院id查询学院名
    String selectByCollegeId(Long collegeId);

    //根据学院名称查询成绩
    List<College> selectByName(String name);
}
