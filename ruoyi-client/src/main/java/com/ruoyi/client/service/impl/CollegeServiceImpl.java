package com.ruoyi.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public IPage<College> collegScores(Integer page, Integer pageSize) {
        Page<College> collegePage = new Page<>(page,pageSize);
        LambdaQueryWrapper<College> collegeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        IPage<College> collegeIPage = collegeMapper.selectPage(collegePage,collegeLambdaQueryWrapper);
        return collegeIPage;
    }

    /**
     * 根据学院名称查询成绩
     * @param name
     * @return
     */
    @Override
    public IPage<College> collegSelect(Integer page, Integer pageSize, String name) {
        Page<College> collegePage = new Page<>(page,pageSize);
        LambdaQueryWrapper<College> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(College::getName,name);
        Page<College> collegePage1 = collegeMapper.selectPage(collegePage, queryWrapper);

//        //名称为空或空字符时，抛出异常
//        if (name == null || name.isEmpty()) {
//            throw new ServiceException("请输入正确学院名称");
//        }

//        List<College> colleges = collegeMapper.selectByName(name);

//        for (College college : colleges) {
//            System.out.println(college);
//        }
        return collegePage1;
    }
}
