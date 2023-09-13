package com.ruoyi.people.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 团体管理对象 tb_team
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_team")
public class TbTeam extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 团体id 团体表的主键
     */
    @TableId(value = "team_id")
    private Long teamId;
    /**
     * 运动员id
     */
    private Long athleteId;
    /**
     * 团体编号
     */
    private String number;
    /**
     * 其他 备用字段
     */
    private String other;

}
