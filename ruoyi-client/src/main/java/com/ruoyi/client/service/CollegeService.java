package com.ruoyi.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.client.domain.entity.College;

import java.util.List;

public interface CollegeService extends IService<College> {

    //按学院总分展示
    List<College> collegScores();

    //根据学院名称查询成绩
    List<College> collegSelect(String name);
}
