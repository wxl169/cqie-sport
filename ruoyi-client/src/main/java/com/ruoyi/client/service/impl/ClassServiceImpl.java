package com.ruoyi.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.client.domain.dto.ClassDTO;
import com.ruoyi.client.domain.entity.Class;
import com.ruoyi.client.mapper.ClassMapper;
import com.ruoyi.client.mapper.CollegeMapper;
import com.ruoyi.client.service.ClassService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {
    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    /**
     * 按班级总分展示
     * @return
     */
    @Override
    public IPage<ClassDTO> classScores(Integer page, Integer pageSize) {
        Page<Class> classPage = new Page<>(page, pageSize);
        Page<Class> classPage1 = classMapper.selectPage(classPage, null);
        System.out.println("classPage1 " + classPage1.getCurrent());
        List<Class> records = classPage1.getRecords();

        List<ClassDTO> list = records.stream().map((item) ->{
            ClassDTO classDTO = new ClassDTO();

            BeanUtils.copyProperties(item,classDTO);
            Long collegeId = item.getCollegeId();//学院id
            //根据id查询学院名
            String name = collegeMapper.selectByCollegeId(collegeId);
            if(name != null) {
                classDTO.setCollegeName(name);
            }
            return classDTO;
        }).collect(Collectors.toList());

        Page<ClassDTO> classPage2 = new Page<>();
        classPage2.setRecords(list);
        classPage2.setTotal(classPage1.getTotal());
        classPage2.setSize(classPage1.getSize());
        classPage2.setCurrent(classPage1.getCurrent());

        classPage2.setOrders(classPage1.getOrders());
        classPage2.setOptimizeCountSql(classPage1.optimizeCountSql());
        classPage2.setSearchCount(classPage1.searchCount());
        classPage2.setMaxLimit(classPage1.maxLimit());
        classPage2.setCountId(classPage1.getCountId());

        classPage2.setPages(classPage1.getPages());

        return classPage2;
    }

    /**
     * 根据班级名称查询成绩
     * @param name
     * @return
     */
    @Override
    public IPage<Class> classSelect(Integer page, Integer pageSize, String name) {
        Page<Class> classPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Class> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Class::getName,name);
        Page<Class> classPage1 = classMapper.selectPage(classPage, queryWrapper);

        return classPage1;
    }
}
