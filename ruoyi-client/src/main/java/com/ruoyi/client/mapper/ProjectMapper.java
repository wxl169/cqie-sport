package com.ruoyi.client.mapper;

import com.ruoyi.client.domain.entity.Project;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 项目表  Mapper 接口
 * </p>
 *
 * @author 16956
 * @since 2023-09-25
 */
@Mapper
public interface ProjectMapper extends BaseMapper<Project> {

    List<Project> find();

    boolean verify(String projectId);
}
