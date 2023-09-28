package com.ruoyi.client.service;

import com.ruoyi.client.domain.vo.ResultVO;


import java.util.Map;

public interface ArrangementService {

    /**
     * 将一条报名信息插入报名表
     * @param info
     * @return
     */
    public ResultVO addSignUp(Map<String, String> info);
}
