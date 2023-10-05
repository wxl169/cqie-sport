package com.ruoyi.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.client.domain.dto.ClassDTO;
import com.ruoyi.client.domain.entity.Class;
import com.ruoyi.client.domain.entity.College;
import com.ruoyi.client.mapper.ClassMapper;
import com.ruoyi.client.mapper.CollegeMapper;
import com.ruoyi.client.service.ClassService;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.BeanCopyUtils;
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
    public List<ClassDTO> classScores() {

        List<Class> classes = classMapper.selectAll();

        List<ClassDTO> list = classes.stream().map((item) ->{
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

        return list;
    }

    /**
     * 根据班级名称查询成绩
     * @param name
     * @return
     */
    @Override
    public List<Class> classSelect(String name) {
//        //名称为空或空字符时，抛出异常
//        if (name == null || name.isEmpty()) {
//            throw new ServiceException("请输入正确班级名称");
//        }

        List<Class> classes = classMapper.selectByNameClasses(name);
//        for (Class aClass : classes) {
//            System.out.println(aClass);
//        }
        return classes;
    }
}
