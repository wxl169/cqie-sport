package com.ruoyi.client.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 裁判员表
 * </p>
 *
 * @author 16956
 * @since 2023-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_referee")
public class Referee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 裁判员id 裁判员表的主键
     */
    @TableId(value = "referee_id", type = IdType.AUTO)
    private Integer refereeId;

    /**
     * 裁判员编号
     */
    private String number;

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
     * 录入时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 其他 备用字段
     */
    private String other;


}
