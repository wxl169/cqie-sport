package com.ruoyi.client.controller;

import com.ruoyi.client.domain.vo.ResultVO;
import com.ruoyi.client.service.CompetitionService;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/client/sports")
public class ScoreController {
    @Autowired
    private CompetitionService competitionService;

    @GetMapping("/listcompetitions")
    public ResultVO listCompetitions() {
        ResultVO resultVO = competitionService.listCompetitions();
        return resultVO;
    }
}
