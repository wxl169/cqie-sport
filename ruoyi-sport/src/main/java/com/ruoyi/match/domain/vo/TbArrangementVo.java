package com.ruoyi.match.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 安排 视图对象 tb_arrangement
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Data
@ExcelIgnoreUnannotated
public class TbArrangementVo {

    private static final long serialVersionUID = 1L;

    /**
     * 安排id 安排表的主键
     */
    @ExcelProperty(value = "安排id 安排表的主键")
    private Long arrangementId;

    /**
     * 项目id
     */
    @ExcelProperty(value = "项目id")
    private Long projectId;

    /**
     * 裁判员id
     */
    @ExcelProperty(value = "裁判员id")
    private Long refereeId;

    /**
     * 运动员id / 团体id
     */
    @ExcelProperty(value = "运动员id / 团体id")
    private Long typeId;

    /**
     * 安排信息单元id
     */
    @ExcelProperty(value = "安排信息单元id")
    private Long infoId;

    /**
     * 类别 0：个人赛，1：团体赛
     */
    @ExcelProperty(value = "类别 0：个人赛，1：团体赛")
    private String type;

    /**
     * 是否取消 0：取消，1：未取消
     */
    @ExcelProperty(value = "是否取消 0：取消，1：未取消")
    private String isCancel;

    /**
     * 取消原因 若未取消则为NULL
     */
    @ExcelProperty(value = "取消原因 若未取消则为NULL")
    private String reason;

    /**
     * 其他 备用字段
     */
    @ExcelProperty(value = "其他 备用字段")
    private String other;


}
