package com.ruoyi.match.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 比赛记录 视图对象 tb_competition
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Data
@ExcelIgnoreUnannotated
public class TbCompetitionVo {

    private static final long serialVersionUID = 1L;

    /**
     * 比赛id 比赛表的主键
     */
    @ExcelProperty(value = "比赛id 比赛表的主键")
    private Long competitionId;

    /**
     * 安排id
     */
    @ExcelProperty(value = "安排id")
    private Long arrangementId;

    /**
     * 运动员id / 团体id
     */
    @ExcelProperty(value = "运动员id / 团体id")
    private String typeId;

    /**
     * 类别 0：个人赛，1：团体赛
     */
    @ExcelProperty(value = "类别 0：个人赛，1：团体赛")
    private String type;

    /**
     * 比赛结果
     */
    @ExcelProperty(value = "比赛结果")
    private String result;

    /**
     * 成绩
     */
    @ExcelProperty(value = "成绩")
    private Long score;

    /**
     * 成绩是否有效 0：无效，1：有效
     */
    @ExcelProperty(value = "成绩是否有效 0：无效，1：有效")
    private String isEffective;

    /**
     * 成绩无效原因 若有效则为NULL
     */
    @ExcelProperty(value = "成绩无效原因 若有效则为NULL")
    private String reason;

    /**
     * 其他 备用字段
     */
    @ExcelProperty(value = "其他 备用字段")
    private String other;


}
