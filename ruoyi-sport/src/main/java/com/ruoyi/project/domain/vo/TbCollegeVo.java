package com.ruoyi.project.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 学院管理视图对象 tb_college
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Data
@ExcelIgnoreUnannotated
public class TbCollegeVo {

    private static final long serialVersionUID = 1L;

    /**
     * 学院id 学院表的主键
     */
    @ExcelProperty(value = "学院id 学院表的主键")
    private Long collegeId;

    /**
     * 学院名
     */
    @ExcelProperty(value = "学院名")
    private String name;

    /**
     * 学院人数
     */
    @ExcelProperty(value = "学院人数")
    private Long snum;

    /**
     * 学院总分 初始为0
     */
    @ExcelProperty(value = "学院总分 初始为0")
    private Long score;

    /**
     * 其他 备用字段
     */
    @ExcelProperty(value = "其他 备用字段")
    private String other;


}
