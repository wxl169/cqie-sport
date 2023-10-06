package com.ruoyi.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.client.domain.entity.Competition;
import com.ruoyi.client.domain.entity.Project;
import com.ruoyi.client.domain.general.GeneralDAO;
import com.ruoyi.client.domain.vo.ScoreVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionMapper extends BaseMapper<Competition>,GeneralDAO<Competition> {

    /**
     * 查找比赛结果列表
     * @return
     */
    public List<ScoreVO> selectCompetitions();

}
