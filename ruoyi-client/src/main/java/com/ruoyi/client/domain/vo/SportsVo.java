package com.ruoyi.client.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class SportsVo {
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
    private Integer arrangeInfoId;
    private Date arrangeInfoTime; //比赛时间
    private String arrangeInfoPlace;//比赛地点
    private String arrangeInfoContent;//比赛说明
}
