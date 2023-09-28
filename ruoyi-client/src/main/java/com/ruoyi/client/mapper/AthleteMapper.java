package com.ruoyi.client.mapper;

import com.ruoyi.client.domain.entity.Athlete;
import com.ruoyi.client.domain.general.GeneralDAO;

import com.ruoyi.client.domain.vo.SearchVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

@Mapper
public interface AthleteMapper extends GeneralDAO<Athlete>, BaseMapper<Athlete> {

    /**
     * 运动员成绩查询：根据运动员编号查询
     * @param athleteNumber
     * @return
     */
    public List<SearchVO> selectCompetitionsByNumber(String athleteNumber);

    /**
     * 团队成绩查询：根据团队编号查询
     * @param teamNumber
     * @return
     */
    public List<SearchVO> selectCompetitionsByTeamNumber(String teamNumber);


    /**
     * 运动员报名查询：根据身份证号查询
     * @param studentIdNumber
     * @return
     */
    public List<SearchVO> selectArrangementsByIdNumber(String studentIdNumber);
}
