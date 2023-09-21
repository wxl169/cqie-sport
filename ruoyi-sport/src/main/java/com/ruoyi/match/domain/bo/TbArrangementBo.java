package com.ruoyi.match.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 安排 业务对象 tb_arrangement
 *
 * @author ruoyi
 * @date 2023-09-13
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TbArrangementBo extends BaseEntity {

    /**
     * 安排id 安排表的主键
     */
    @NotNull(message = "安排id 安排表的主键不能为空", groups = { EditGroup.class })
    private Long arrangementId;

    /**
     * 项目id
     */
    @NotNull(message = "项目id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long projectId;

    /**
     * 裁判员id
     */
    @NotNull(message = "裁判员id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String refereeId;

    /**
     * 运动员id / 团体id
     */
    @NotNull(message = "运动员id / 团体id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long typeId;

    /**
     * 安排信息单元id
     */
    @NotNull(message = "安排信息单元id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long infoId;

    /**
     * 类别 0：个人赛，1：团体赛
     */
    @NotBlank(message = "类别 0：个人赛，1：团体赛不能为空", groups = { AddGroup.class, EditGroup.class })
    private String type;

    /**
     * 是否取消 0：取消，1：未取消
     */
    @NotBlank(message = "是否取消 0：取消，1：未取消不能为空", groups = { AddGroup.class, EditGroup.class })
    private String isCancel;

    /**
     * 取消原因 若未取消则为NULL
     */
    private String reason;

    /**
     * 其他 备用字段
     */
    private String other;


}
