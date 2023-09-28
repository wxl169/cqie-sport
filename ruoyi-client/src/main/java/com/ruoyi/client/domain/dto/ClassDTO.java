package com.ruoyi.client.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.client.domain.entity.Class;
import lombok.Data;

import java.util.Date;

@Data
public class ClassDTO {
    /**
     * 班级id 班级表的主键
     */
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
    private Integer snum;

    /**
     * 班级总分 初始为0
     */
    private Integer score;

    /**
     * 录入时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 其他 备用字段
     */
    private String other;

    /**
     * 添加 学院名
     */
    private String collegeName;
}
