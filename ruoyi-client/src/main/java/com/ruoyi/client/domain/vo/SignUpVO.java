package com.ruoyi.client.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 关联到 ProjectMapper.xml 中
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignUpVO {
    //Project
    private Long projectId;
    private String projectNumber;
    private String projectName;
    private String projectIntroduction;
    private String projectType; //项目类型 个人/团体
    private String projectGType; //男子/女子赛 0：女子，1：男子
    private Integer projectNeedNum; //招收人数/队数
    private Integer projectNeedReNum; //招收裁判员数量

    //ArrangeInfo
    private Long arrangeInfoId;
    private Date arrangeInfoTime;
    private String arrangeInfoPlace;
    private String arrangeInfoContent;
}
