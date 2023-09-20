package com.ruoyi.people.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 用户管理视图对象 tb_user
 *
 * @author ruoyi
 * @date 2023-09-15
 */
@Data
@ExcelIgnoreUnannotated
public class TbUserVo {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id 用户表的主键
     */
    @ExcelProperty(value = "用户id 用户表的主键")
    private Long userId;

    /**
     * 身份id
     */
    @ExcelProperty(value = "身份id")
    private Long typeId;

    /**
     * 身份类型
     */
    @ExcelProperty(value = "身份类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "identity_type")
    private String type;

    /**
     * 用户名
     */
    @ExcelProperty(value = "用户名")
    private String username;

    /**
     * 真实姓名
     */
    @ExcelProperty(value = "真实姓名")
    private String realname;

    /**
     * 性别
     */
    @ExcelProperty(value = "性别", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_user_sex")
    private String gender;

    /**
     * 联系电话
     */
    @ExcelProperty(value = "联系电话")
    private String phoneNumber;

    /**
     * 出生日期
     */
    @ExcelProperty(value = "出生日期")
    private Date birthday;

    /**
     * 邮箱地址
     */
    @ExcelProperty(value = "邮箱地址")
    private String email;


}
