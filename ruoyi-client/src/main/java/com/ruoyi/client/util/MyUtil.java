package com.ruoyi.client.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtil {

    /**
     * 为运动员生成学号--根据年份和学生id
     * 一次运动会，运动员只持有一个编号
     * @param sid
     * @return
     */
    public static String generateAthleteNumber(Long sid) {

        String studentId = sid + "";

        //例 A20210001--学号1
        //A 年 4位（学生id）
        String result = "A";

        Date date = new Date();
        String dateString = (date + "");
        String year = dateString.substring(dateString.length() - 4, dateString.length());
        result += year;

        int len = studentId.length();
        int zeroNum = 4 - len;
        String zeroString = "";
        for (int i = 0; i < zeroNum; i++) {
            zeroString += "0";
        }

        result = result + zeroString + studentId;
        return result;
    }

    public static String generateProjectNumber(Integer pid) {

        String studentId = pid + "";

        String result = "P";

        Date date = new Date();
        String dateString = (date + "");
        String year = dateString.substring(dateString.length() - 4, dateString.length());
        result += year;

        int len = studentId.length();
        int zeroNum = 4 - len;
        String zeroString = "";
        for (int i = 0; i < zeroNum; i++) {
            zeroString += "0";
        }

        result = result + zeroString + studentId;
        return result;
    }


    public static Date timeChange(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date timeDate = sdf.parse(time);
        return timeDate;
    }


    public static void main(String[] args) {
        System.out.println("generateAthleteNumber(\"1\") = " + generateAthleteNumber(1L));
        System.out.println("generateAthleteNumber(\"12\") = " + generateAthleteNumber(12L));
        System.out.println("generateAthleteNumber(\"122\") = " + generateAthleteNumber(122L));
        System.out.println("generateAthleteNumber(\"1222\") = " + generateAthleteNumber(1222L));

    }
}

