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
 * 用户表
 * </p>
 *
 * @author 16956
 * @since 2023-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id 用户表的主键
     */
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private Long userId;

    /**
     * 对应身份id 若不是本校学生，则为NULL；若是本校学生，则学生表中已有的数据此表中为NULL即可
     */
    private Long typeId;

    /**
     * 身份类型 0：学生，1：班级老师，2：裁判，3：校内其他工作人员，4：非本校人员
     */
    private String type;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realname;

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
     * 头像 存储图片相对地址，初始应指定默认头像
     */
    private String img;

    /**
     * 邮箱地址
     */
    private String email;

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
