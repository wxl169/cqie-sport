package com.ruoyi.client.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 成绩公布页的VO实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScoreVO {
    private String projectNumber;
    private String projectName;
    private Date arrangementInfoTime;
    private String arrangementInfoPlace;
    private String arrangementInfoContent;
    private String athleteNumber;
    private String collegeName;
    private String className;
    private String studentName;
    private String competitionResult;
    private String competitionScore;
    private String refereeNumber;
}
