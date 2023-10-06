package com.ruoyi.client.domain.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Competition {
    /**
     * 比赛id 比赛表的主键
     */
    @Id
    @Column(name = "比赛_id")
    private Long 比赛Id;

    /**
     * 安排id
     */
    @Column(name = "arrangement_id")
    private Long arrangementId;

    /**
     * 运动员id / 团体id
     */
    @Column(name = "type_id")
    private String typeId; //团队表的主键id，无法和比赛表中的type_id字段关联
                            //因此需要用团队表中的编号字段来关联，而编号字段是varchar类型
                            //因此，此处也应改为String类型

    /**
     * 类别 0：个人赛，1：团体赛
     */
    private String type;

    /**
     * 成绩
     */
    private Integer score;

    /**
     * 成绩是否有效 0：无效，1：有效
     */
    @Column(name = "is_effective")
    private String isEffective;

    /**
     * 成绩无效原因 若有效则为NULL
     */
    private String reason;

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
     * 获取比赛id 比赛表的主键
     *
     * @return 比赛_id - 比赛id 比赛表的主键
     */
    public Long get比赛Id() {
        return 比赛Id;
    }

    /**
     * 设置比赛id 比赛表的主键
     *
     * @param 比赛Id 比赛id 比赛表的主键
     */
    public void set比赛Id(Long 比赛Id) {
        this.比赛Id = 比赛Id;
    }

    /**
     * 获取安排id
     *
     * @return arrangement_id - 安排id
     */
    public Long getArrangementId() {
        return arrangementId;
    }

    /**
     * 设置安排id
     *
     * @param arrangementId 安排id
     */
    public void setArrangementId(Long arrangementId) {
        this.arrangementId = arrangementId;
    }

    /**
     * 获取运动员id / 团体id
     *
     * @return type_id - 运动员id / 团体id
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * 设置运动员id / 团体id
     *
     * @param typeId 运动员id / 团体id
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取类别 0：个人赛，1：团体赛
     *
     * @return type - 类别 0：个人赛，1：团体赛
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类别 0：个人赛，1：团体赛
     *
     * @param type 类别 0：个人赛，1：团体赛
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取成绩
     *
     * @return score - 成绩
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置成绩
     *
     * @param score 成绩
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取成绩是否有效 0：无效，1：有效
     *
     * @return is_effective - 成绩是否有效 0：无效，1：有效
     */
    public String getIsEffective() {
        return isEffective;
    }

    /**
     * 设置成绩是否有效 0：无效，1：有效
     *
     * @param isEffective 成绩是否有效 0：无效，1：有效
     */
    public void setIsEffective(String isEffective) {
        this.isEffective = isEffective;
    }

    /**
     * 获取成绩无效原因 若有效则为NULL
     *
     * @return reason - 成绩无效原因 若有效则为NULL
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置成绩无效原因 若有效则为NULL
     *
     * @param reason 成绩无效原因 若有效则为NULL
     */
    public void setReason(String reason) {
        this.reason = reason;
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
