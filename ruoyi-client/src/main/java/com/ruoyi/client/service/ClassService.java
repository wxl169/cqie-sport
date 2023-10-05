package com.ruoyi.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.client.domain.dto.ClassDTO;
import com.ruoyi.client.domain.entity.Class;

import java.util.List;

public interface ClassService extends IService<Class> {

    //按班级总分展示
    public List<ClassDTO> classScores();

    //根据班级名称查询成绩
    public List<Class> classSelect(String name);
}
