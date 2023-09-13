package com.ruoyi.people.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 学生管理视图对象 tb_student
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Data
@ExcelIgnoreUnannotated
public class TbStudentVo {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id 学生表的主键
     */
    @ExcelProperty(value = "学生id 学生表的主键")
    private Long studentId;

    /**
     * 班级id
     */
    @ExcelProperty(value = "班级id")
    private Long classId;

    /**
     * 学院id
     */
    @ExcelProperty(value = "学院id")
    private Long collegeId;

    /**
     * 是否为运动员 0：不是，1：是
     */
    @ExcelProperty(value = "是否为运动员 0：不是，1：是")
    private String isAthlete;

    /**
     * 学号
     */
    @ExcelProperty(value = "学号")
    private String studentNumber;

    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名")
    private String name;

    /**
     * 性别 1：男，0：女
     */
    @ExcelProperty(value = "性别 1：男，0：女")
    private String gender;

    /**
     * 身份证号 18位号码
     */
    @ExcelProperty(value = "身份证号 18位号码")
    private String idnumber;

    /**
     * 联系电话
     */
    @ExcelProperty(value = "联系电话")
    private String phoneNumber;

    /**
     * 出生日期
     */
    @ExcelProperty(value = "出生日期")
    private Date birthday;

    /**
     * 运动会信用 默认100，若出现多次退选，舞弊等行为，扣除一定分数
     */
    @ExcelProperty(value = "运动会信用 默认100，若出现多次退选，舞弊等行为，扣除一定分数")
    private Long creditScore;

    /**
     * 其他 备用字段
     */
    @ExcelProperty(value = "其他 备用字段")
    private String other;


}
