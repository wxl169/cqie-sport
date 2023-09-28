package com.ruoyi.client.domain.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.Column;
import java.util.Date;
@TableName("tb_arrangement")
public class Arrangement {
    /**
     * 安排id 安排表的主键
     */
    @TableId(value = "arrangement_id",type = IdType.ASSIGN_ID)
    private Long arrangementId;

    /**
     * 项目id
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 裁判员id
     */
    @Column(name = "referee_id")
    private String refereeId;

    /**
     * 运动员id / 团体id
     */
    @Column(name = "type_id")
    private Long typeId;

    /**
     * 安排信息单元id
     */
    @Column(name = "info_id")
    private Long infoId;

    /**
     * 类别 0：个人赛，1：团体赛
     */
    private String type;

    /**
     * 是否取消 0：取消，1：未取消
     */
    @Column(name = "is_cancel")
    private String isCancel;

    /**
     * 取消原因 若未取消则为NULL
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
     * 获取安排id 安排表的主键
     *
     * @return arrangement_id - 安排id 安排表的主键
     */
    public Long getArrangementId() {
        return arrangementId;
    }

    /**
     * 设置安排id 安排表的主键
     *
     * @param arrangementId 安排id 安排表的主键
     */
    public void setArrangementId(Long arrangementId) {
        this.arrangementId = arrangementId;
    }

    /**
     * 获取项目id
     *
     * @return project_id - 项目id
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * 设置项目id
     *
     * @param projectId 项目id
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取裁判员id
     *
     * @return referee_id - 裁判员id
     */
    public String getRefereeId() {
        return refereeId;
    }

    /**
     * 设置裁判员id
     *
     * @param refereeId 裁判员id
     */
    public void setRefereeId(String refereeId) {
        this.refereeId = refereeId;
    }

    /**
     * 获取运动员id / 团体id
     *
     * @return type_id - 运动员id / 团体id
     */
    public Long getTypeId() {
        return typeId;
    }

    /**
     * 设置运动员id / 团体id
     *
     * @param typeId 运动员id / 团体id
     */
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取安排信息单元id
     *
     * @return info_id - 安排信息单元id
     */
    public Long getInfoId() {
        return infoId;
    }

    /**
     * 设置安排信息单元id
     *
     * @param infoId 安排信息单元id
     */
    public void setInfoId(Long infoId) {
        this.infoId = infoId;
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
     * 获取是否取消 0：取消，1：未取消
     *
     * @return is_cancel - 是否取消 0：取消，1：未取消
     */
    public String getIsCancel() {
        return isCancel;
    }

    /**
     * 设置是否取消 0：取消，1：未取消
     *
     * @param isCancel 是否取消 0：取消，1：未取消
     */
    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel;
    }

    /**
     * 获取取消原因 若未取消则为NULL
     *
     * @return reason - 取消原因 若未取消则为NULL
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置取消原因 若未取消则为NULL
     *
     * @param reason 取消原因 若未取消则为NULL
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
