package com.ruoyi.match.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 安排信息单元 业务对象 tb_arrange_info
 *
 * @author ruoyi
 * @date 2023-09-13
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TbArrangeInfoBo extends BaseEntity {

    /**
     * 安排信息单元id 安排信息单元表的主键
     */
    @NotNull(message = "安排信息单元id 安排信息单元表的主键不能为空", groups = { EditGroup.class })
    private Long arrangeInfoId;

    /**
     * 项目id
     */
    @NotNull(message = "项目id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long projectId;

    /**
     * 比赛时间
     */
    @NotNull(message = "比赛时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date time;

    /**
     * 比赛地点
     */
    @NotBlank(message = "比赛地点不能为空", groups = { AddGroup.class, EditGroup.class })
    private String place;

    /**
     * 其他安排信息
     */
    @NotBlank(message = "其他安排信息不能为空", groups = { AddGroup.class, EditGroup.class })
    private String content;

    /**
     * 其他 备用字段
     */
    @NotBlank(message = "其他 备用字段不能为空", groups = { AddGroup.class, EditGroup.class })
    private String other;


}
