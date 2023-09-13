package com.ruoyi.match.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 安排信息单元 视图对象 tb_arrange_info
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Data
@ExcelIgnoreUnannotated
public class TbArrangeInfoVo {

    private static final long serialVersionUID = 1L;

    /**
     * 安排信息单元id 安排信息单元表的主键
     */
    @ExcelProperty(value = "安排信息单元id 安排信息单元表的主键")
    private Long arrangeInfoId;

    /**
     * 项目id
     */
    @ExcelProperty(value = "项目id")
    private Long projectId;

    /**
     * 比赛时间
     */
    @ExcelProperty(value = "比赛时间")
    private Date time;

    /**
     * 比赛地点
     */
    @ExcelProperty(value = "比赛地点")
    private String place;

    /**
     * 其他安排信息
     */
    @ExcelProperty(value = "其他安排信息")
    private String content;

    /**
     * 其他 备用字段
     */
    @ExcelProperty(value = "其他 备用字段")
    private String other;


}
