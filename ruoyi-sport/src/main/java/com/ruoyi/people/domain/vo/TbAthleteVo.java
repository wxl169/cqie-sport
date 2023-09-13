package com.ruoyi.people.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 运动员管理 视图对象 tb_athlete
 *
 * @author ruoyi
 * @date 2023-09-13
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
     * 运动员编号
     */
    @ExcelProperty(value = "运动员编号")
    private String number;

    /**
     * 其他 备用字段
     */
    @ExcelProperty(value = "其他 备用字段")
    private String other;

    private Long teamId;


}
