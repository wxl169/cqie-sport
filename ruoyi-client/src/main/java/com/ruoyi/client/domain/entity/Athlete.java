package com.ruoyi.client.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@TableName("tb_athlete")
@Table(name = "tb_athlete")
public class Athlete {
    /**
     * 运动员id 运动员表的主键
     */

    @TableId(value = "athlete_id",type = IdType.ASSIGN_ID)
    private Long athleteId;

    /**
     * 学生id
     */
    @Column(name = "student_id")
    private Long studentId;

    /**
     * 运动员编号
     */
    private String number;

    /**
     * 录入时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 其他 备用字段
     */
    private String other;

    /**
     * 获取运动员id 运动员表的主键
     *
     * @return athlete_id - 运动员id 运动员表的主键
     */
    public Long getAthleteId() {
        return athleteId;
    }

    /**
     * 设置运动员id 运动员表的主键
     *
     * @param athleteId 运动员id 运动员表的主键
     */
    public void setAthleteId(Long athleteId) {
        this.athleteId = athleteId;
    }

    /**
     * 获取学生id
     *
     * @return student_id - 学生id
     */
    public Long getStudentId() {
        return studentId;
    }

    /**
     * 设置学生id
     *
     * @param studentId 学生id
     */
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    /**
     * 获取运动员编号
     *
     * @return number - 运动员编号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置运动员编号
     *
     * @param number 运动员编号
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 获取录入时间
     *
     * @return create_time - 录入时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置录入时间
     *
     * @param createTime 录入时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取其他 备用字段
     *
     * @return other - 其他 备用字段
     */
    public String getOther() {
        return other;
    }

    /**
     * 设置其他 备用字段
     *
     * @param other 其他 备用字段
     */
    public void setOther(String other) {
        this.other = other;
    }
}
