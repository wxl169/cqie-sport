package com.ruoyi.project.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 班级管理视图对象 tb_class
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Data
@ExcelIgnoreUnannotated
public class TbClassVo {

    private static final long serialVersionUID = 1L;

    /**
     * 班级id 班级表的主键
     */
    @ExcelProperty(value = "班级id 班级表的主键")
    private Long classId;

    /**
     * 学院id
     */
    @ExcelProperty(value = "学院id")
    private Long collegeId;

    /**
     * 班级名称
     */
    @ExcelProperty(value = "班级名称")
    private String name;

    /**
     * 班级人数
     */
    @ExcelProperty(value = "班级人数")
    private Long snum;

    /**
     * 班级总分 初始为0
     */
    @ExcelProperty(value = "班级总分 初始为0")
    private Long score;

    /**
     * 其他 备用字段
     */
    @ExcelProperty(value = "其他 备用字段")
    private String other;


}
