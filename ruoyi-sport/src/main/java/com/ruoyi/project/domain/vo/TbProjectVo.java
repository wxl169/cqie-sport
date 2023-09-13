package com.ruoyi.project.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 项目管理 视图对象 tb_project
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Data
@ExcelIgnoreUnannotated
public class TbProjectVo {

    private static final long serialVersionUID = 1L;

    /**
     * 项目编号
     */
    @ExcelProperty(value = "项目编号")
    private String number;

    /**
     * 项目名称
     */
    @ExcelProperty(value = "项目名称")
    private String name;

    /**
     * 比赛类型 0：个人，1：团体
     */
    @ExcelProperty(value = "比赛类型 0：个人，1：团体")
    private String type;

    /**
     * 男子/女子赛 0：女子，1：男子
     */
    @ExcelProperty(value = "男子/女子赛 0：女子，1：男子")
    private String gtype;

    /**
     * 招收人数 / 队数 根据比赛类型字段判断人数 / 队数
     */
    @ExcelProperty(value = "招收人数 / 队数 根据比赛类型字段判断人数 / 队数")
    private Long upnum;

    /**
     * 裁判员数量
     */
    @ExcelProperty(value = "裁判员数量")
    private Long renum;

    /**
     * 是否删除
     */
    @ExcelProperty(value = "是否删除")
    private String isCancel;


}
