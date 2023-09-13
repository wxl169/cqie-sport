package com.ruoyi.people.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 团体管理视图对象 tb_team
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Data
@ExcelIgnoreUnannotated
public class TbTeamVo {

    private static final long serialVersionUID = 1L;

    /**
     * 团体id 团体表的主键
     */
    @ExcelProperty(value = "团体id 团体表的主键")
    private Long teamId;

    /**
     * 运动员id
     */
    @ExcelProperty(value = "运动员id")
    private Long athleteId;

    /**
     * 团体编号
     */
    @ExcelProperty(value = "团体编号")
    private String number;

    /**
     * 其他 备用字段
     */
    @ExcelProperty(value = "其他 备用字段")
    private String other;


}
