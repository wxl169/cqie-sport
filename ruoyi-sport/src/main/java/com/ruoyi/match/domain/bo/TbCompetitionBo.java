package com.ruoyi.match.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 比赛记录 业务对象 tb_competition
 *
 * @author ruoyi
 * @date 2023-09-13
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TbCompetitionBo extends BaseEntity {

    /**
     * 比赛id 比赛表的主键
     */
    @NotNull(message = "比赛id 比赛表的主键不能为空", groups = { EditGroup.class })
    private Long competitionId;

    /**
     * 安排id
     */
    @NotNull(message = "安排id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long arrangementId;

    /**
     * 运动员id / 团体id
     */
    @NotBlank(message = "运动员id / 团体id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String typeId;

    /**
     * 类别 0：个人赛，1：团体赛
     */
    @NotBlank(message = "类别 0：个人赛，1：团体赛不能为空", groups = { AddGroup.class, EditGroup.class })
    private String type;

    /**
     * 比赛结果
     */
    @NotBlank(message = "比赛结果不能为空", groups = { AddGroup.class, EditGroup.class })
    private String result;

    /**
     * 成绩
     */
    @NotNull(message = "成绩不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long score;

    /**
     * 成绩是否有效 0：无效，1：有效
     */
    @NotBlank(message = "成绩是否有效 0：无效，1：有效不能为空", groups = { AddGroup.class, EditGroup.class })
    private String isEffective;

    /**
     * 成绩无效原因 若有效则为NULL
     */
    @NotBlank(message = "成绩无效原因 若有效则为NULL不能为空", groups = { AddGroup.class, EditGroup.class })
    private String reason;

    /**
     * 其他 备用字段
     */
    @NotBlank(message = "其他 备用字段不能为空", groups = { AddGroup.class, EditGroup.class })
    private String other;


}
