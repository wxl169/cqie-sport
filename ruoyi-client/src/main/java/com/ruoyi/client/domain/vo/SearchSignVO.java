package com.ruoyi.client.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchSignVO {
    private String collegeName; //学院名
    private String className;
    private String studentName;
    private String gender;
    private String studentIdNumber;
    private String phoneNumber;
    private String athleteNumber;
    private String projectNumber;
    private String projectName;
    private String arrangementInfoTime;
    private String arrangementInfoPlace;
}
