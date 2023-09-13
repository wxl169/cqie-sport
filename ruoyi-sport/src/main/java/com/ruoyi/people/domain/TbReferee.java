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
 * 裁判员管理对象 tb_referee
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_referee")
public class TbReferee extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 裁判员id 裁判员表的主键
     */
    @TableId(value = "referee_id")
    private Long refereeId;
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
    private Date birthday;
    /**
     * 其他 备用字段
     */
    private String other;

}
