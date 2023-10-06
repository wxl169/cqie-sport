package com.ruoyi.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.client.domain.entity.Competition;
import com.ruoyi.client.domain.vo.ResStatus;
import com.ruoyi.client.domain.vo.ResultVO;
import com.ruoyi.client.domain.vo.ScoreVO;
import com.ruoyi.client.mapper.CompetitionMapper;
import com.ruoyi.client.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompetitionServiceImpl extends ServiceImpl<CompetitionMapper, Competition> implements CompetitionService {

    @Autowired
    private CompetitionMapper competitionMapper;

    /**
     * 查找比赛结果列表
     * @return
     */
    @Override
    public ResultVO listCompetitions() {
        List<ScoreVO> list = competitionMapper.selectCompetitions();
        //list中是每条比赛记录的VO ScoreVO

        //按赛事编号进行分类List
        List<String> projectNumberList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String projectNumber = list.get(i).getProjectNumber();
            //如果list中不存在这个值，则加入
            if (!projectNumberList.contains(projectNumber)) {
                projectNumberList.add(projectNumber);
            }
        }
        //先定义一个总的List
        List<List<ScoreVO>> deepList = new ArrayList<>();
        ArrayList<ScoreVO>[] listArray = new ArrayList[projectNumberList.size()];
        for (int i = 0; i < listArray.length; i++) {
            listArray[i] = new ArrayList<ScoreVO>();
        }

        //首先得到这一类的列表
        ArrayList<ScoreVO> list2 = new ArrayList<>();
        for (int i = 0; i < projectNumberList.size(); i++) {
            //先遍历每一种项目编号
            for (int j = 0; j < list.size(); j++) {//再遍历查到的整个赛事列表
                if (projectNumberList.get(i).equals(list.get(j).getProjectNumber())) {
                    //若此项目属于此编号，则加入此编号对应的列表
                    listArray[i].add(list.get(j));
                }
            }
        }
        //System.out.println(listArray);
        return new ResultVO(ResStatus.OK, "success", listArray);

    }

    /**
     * 比赛结果列表CompetitionMapper
     */
    @Override
    public ResultVO listCompetitionResults() {
        List<ScoreVO> list = competitionMapper.selectCompetitions();
        return new ResultVO(ResStatus.OK, "success", list);
    }
}
