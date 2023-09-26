package com.ruoyi.client.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学生表
 * </p>
 *
 * @author 16956
 * @since 2023-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id 学生表的主键
     */
    @TableId(value = "student_id", type = IdType.AUTO)
    private Long studentId;

    /**
     * 班级id
     */
    private Integer classId;

    /**
     * 学院id
     */
    private Integer collegeId;

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
    private LocalDate birthday;

    /**
     * 运动会信用 默认100，若出现多次退选，舞弊等行为，扣除一定分数
     */
    private Integer creditScore;

    /**
     * 录入时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 其他 备用字段
     */
    private String other;


}
