package com.ruoyi.project.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 班级管理对象 tb_class
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_class")
public class TbClass extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 班级id 班级表的主键
     */
    @TableId(value = "class_id")
    private Long classId;
    /**
     * 学院id
     */
    private Long collegeId;
    /**
     * 班级名称
     */
    private String name;
    /**
     * 班级人数
     */
    private Long snum;
    /**
     * 班级总分 初始为0
     */
    private Long score;
    /**
     * 其他 备用字段
     */
    private String other;

}
