package com.ruoyi.people.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生管理对象 tb_student
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_student")
public class TbStudent extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 学生id 学生表的主键
     */
    @TableId(value = "student_id")
    private Long studentId;
    /**
     * 班级id
     */
    private Long classId;
    /**
     * 学院id
     */
    private Long collegeId;
    /**
     * 是否为运动员 0：不是，1：是
     */
    private String isAthlete;
    /**
     * 学号
     */
    private String studentNumber;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别 1：男，0：女
     */
    private String gender;
    /**
     * 身份证号 18位号码
     */
    private String idnumber;
    /**
     * 联系电话
     */
    private String phoneNumber;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 运动会信用 默认100，若出现多次退选，舞弊等行为，扣除一定分数
     */
    private Long creditScore;
    /**
     * 其他 备用字段
     */
    private String other;

}
