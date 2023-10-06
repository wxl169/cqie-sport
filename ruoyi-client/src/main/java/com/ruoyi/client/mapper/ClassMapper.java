package com.ruoyi.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.client.domain.entity.Class;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper extends BaseMapper<Class> {
    int deleteByPrimaryKey(Long classId);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(Long classId);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);

    //按班级总分_降序展示
    List<Class> selectAll();

    //根据班级名称查询成绩
    List<Class> selectByNameClasses(String name);
}
