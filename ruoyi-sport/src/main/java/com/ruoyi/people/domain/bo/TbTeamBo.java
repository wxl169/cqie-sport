package com.ruoyi.people.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 团体管理业务对象 tb_team
 *
 * @author ruoyi
 * @date 2023-09-13
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TbTeamBo extends BaseEntity {

    /**
     * 团体id 团体表的主键
     */
    @NotNull(message = "团体id 团体表的主键不能为空", groups = { EditGroup.class })
    private Long teamId;

    /**
     * 运动员id
     */
    @NotNull(message = "运动员id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long athleteId;

    /**
     * 团体编号
     */
    @NotBlank(message = "团体编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String number;

    /**
     * 其他 备用字段
     */
    @NotBlank(message = "其他 备用字段不能为空", groups = { AddGroup.class, EditGroup.class })
    private String other;


}
