package com.ruoyi.client.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.client.domain.dto.ClassDTO;
import com.ruoyi.client.domain.entity.Class;
import com.ruoyi.client.domain.entity.College;
import com.ruoyi.client.mapper.CollegeMapper;
import com.ruoyi.client.service.ClassService;
import com.ruoyi.client.service.CollegeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 按班级、学院总分展示
 * 班级、学院总分查询
 */
@Controller
@RequestMapping("/client/user")
public class ClassController extends BaseController {
    @Autowired
    private ClassService classService;

    @Autowired
    private CollegeService collegeService;

    /**
     * 按班级总分展示
     * @return
     */
    @GetMapping("/classScores")
    @ResponseBody
    public R classScores() {
        List<ClassDTO> scoresList = classService.classScores();
        return R.ok(scoresList);
    }

    /**
     * 根据班级名称查询成绩
     * @param name
     * @return
     */
    @PostMapping("/classSelect")
    @ResponseBody
    public R classSelect(String name) {
        List<Class> classes = classService.classSelect(name);
        return R.ok(classes);
    }

    /**
     * 按学院总分展示
     * @return
     */
    @GetMapping("/collegScores")
    @ResponseBody
    public R collegScores() {
        List<College> collegeList = collegeService.collegScores();
        return R.ok(collegeList);
    }

    /**
     * 根据学院名称查询成绩
     * @param name
     * @return
     */
    @PostMapping("/collegSelect")
    @ResponseBody
    public R collegSelect(String name) {
        List<College> colleges = collegeService.collegSelect(name);
        return R.ok(colleges);
    }
}
