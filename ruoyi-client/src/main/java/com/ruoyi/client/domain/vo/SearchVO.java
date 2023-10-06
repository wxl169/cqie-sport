package com.ruoyi.client.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 查询页-运动员成绩查询，团体成绩查询-视图实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchVO {

    private String athleteNumber; //运动员编号
    private String studentIdNumber; //学生身份证号
    private String studentName; //学生姓名
    private String competitionResult; //比赛结果
    private String competitionScore; //比赛分数
    private Date arrangementInfoTime; //比赛时间
    private String arrangementInfoPlace;
    private String projectNumber;
    private String projectName;

    private String teamNumber; //团体编号

}
