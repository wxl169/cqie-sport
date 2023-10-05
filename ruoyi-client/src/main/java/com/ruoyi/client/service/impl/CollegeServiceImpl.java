package com.ruoyi.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.client.domain.entity.College;
import com.ruoyi.client.mapper.CollegeMapper;
import com.ruoyi.client.service.CollegeService;
import com.ruoyi.common.exception.ServiceException;
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

    /**
     * 根据学院名称查询成绩
     * @param name
     * @return
     */
    @Override
    public List<College> collegSelect(String name) {

//        //名称为空或空字符时，抛出异常
//        if (name == null || name.isEmpty()) {
//            throw new ServiceException("请输入正确学院名称");
//        }

        List<College> colleges = collegeMapper.selectByName(name);
//        for (College college : colleges) {
//            System.out.println(college);
//        }
        return colleges;
    }
}
