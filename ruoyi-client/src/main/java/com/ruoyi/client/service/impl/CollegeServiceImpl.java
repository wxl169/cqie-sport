package com.ruoyi.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.client.domain.entity.College;
import com.ruoyi.client.mapper.CollegeMapper;
import com.ruoyi.client.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College> implements CollegeService{
    @Autowired
    private CollegeMapper collegeMapper;


    @Override
    public List<College> collegScores() {
        List<College> colleges = collegeMapper.selectAll();
        return colleges;
    }
}
