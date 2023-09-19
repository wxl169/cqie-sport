package com.ruoyi.people.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * 运动员管理 视图对象 tb_athlete
 *
 * @author ruoyi
 * @date 2023-09-19
 */
@Data
@ExcelIgnoreUnannotated
public class TbAthleteVo {

    private static final long serialVersionUID = 1L;

    /**
     * 运动员id 运动员表的主键
     */
    @ExcelProperty(value = "运动员id 运动员表的主键")
    private Long athleteId;

    /**
     * 学生id
     */
    @ExcelProperty(value = "学生id")
    private Long studentId;

    /**
     * 学生姓名
     */
    @ExcelProperty(value = "学生姓名")
    private String studentName;

    /**
     * 运动员编号
     */
    @ExcelProperty(value = "运动员编号")
    private String number;


}
