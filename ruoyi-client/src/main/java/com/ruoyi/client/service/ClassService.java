package com.ruoyi.client.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.client.domain.dto.ClassDTO;
import com.ruoyi.client.domain.entity.Class;

import java.util.List;

public interface ClassService extends IService<Class> {

    //按班级总分展示
    public IPage<ClassDTO> classScores(Integer page, Integer pageSize);

    //根据班级名称查询成绩
    public IPage<Class> classSelect(Integer page, Integer pageSize, String name);
}
