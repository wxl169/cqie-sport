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
 * 裁判员管理视图对象 tb_referee
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Data
@ExcelIgnoreUnannotated
public class TbRefereeVo {

    private static final long serialVersionUID = 1L;

    /**
     * 裁判员id 裁判员表的主键
     */
    @ExcelProperty(value = "裁判员id 裁判员表的主键")
    private Long refereeId;

    /**
     * 裁判员编号
     */
    @ExcelProperty(value = "裁判员编号")
    private String number;

    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名")
    private String name;

    /**
     * 性别 1：男，0：女
     */
    @ExcelProperty(value = "性别 1：男，0：女")
    private String gender;

    /**
     * 身份证号 18位号码
     */
    @ExcelProperty(value = "身份证号 18位号码")
    private String idnumber;

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
     * 其他 备用字段
     */
    @ExcelProperty(value = "其他 备用字段")
    private String other;


}
