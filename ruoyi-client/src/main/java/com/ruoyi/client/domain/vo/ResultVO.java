package com.ruoyi.client.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResultVO {
    //传递给前端的状态码

    private int code;

    //响应给前端的信息

    private String msg;

    //响应给前端的数据

    private Object data;
}
