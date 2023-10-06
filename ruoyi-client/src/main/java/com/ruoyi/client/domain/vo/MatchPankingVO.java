package com.ruoyi.client.domain.vo;

import lombok.Data;

/**
 * @author 16956
 * @Auther 邬文莱
 * @explanation
 * @date 2023/9/15 12:05
 */
@Data
public class MatchPankingVO {
    //学生姓名
    private String studentName;
    //学生成绩
    private String score;
    //学生班级
    private String studentClass;
    //所在学院
    private String college;
    //项目名称
    private String projectName;
}
