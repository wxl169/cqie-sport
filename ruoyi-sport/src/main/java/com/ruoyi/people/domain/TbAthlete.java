package com.ruoyi.people.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 运动员管理 对象 tb_athlete
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_athlete")
public class TbAthlete extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 运动员id 运动员表的主键
     */
    @TableId(value = "athlete_id")
    private Long athleteId;
    /**
     * 学生id
     */
    private Long studentId;
    /**
     * 运动员编号
     */
    private String number;
    /**
     * 其他 备用字段
     */
    private String other;


    private Long teamId;

}
