package com.ruoyi.client.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author 16956
 */
@Data
public class UserInfoVO {
    /**
     * 用户id 用户表的主键
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 对应身份id 若不是本校学生，则为NULL；若是本校学生，则学生表中已有的数据此表中为NULL即可
     */
    private Integer typeId;

    /**
     * 身份类型 0：学生，1：班级老师，2：裁判，3：校内其他工作人员，4：非本校人员
     */
    private String type;

    /**
     * 用户名
     */
    private String username;



    /**
     * 性别 1：男，0：女
     */
    private String gender;


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
     * 其他 备用字段
     */
    private String other;
}
