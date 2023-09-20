package com.ruoyi.project.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学院管理对象 tb_college
 *
 * @author ruoyi
 * @date 2023-09-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_college")
public class TbCollege extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 学院id 学院表的主键
     */
    @TableId(value = "college_id")
    private Long collegeId;
    /**
     * 学院名
     */
    private String name;
    /**
     * 学院人数
     */
    private Long snum;
    /**
     * 学院总分 初始为0
     */
    private Long score;
    /**
     * 其他 备用字段
     */
    private String other;

}
