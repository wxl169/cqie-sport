package com.ruoyi.client.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.client.domain.dto.TeamDto;
import com.ruoyi.client.domain.entity.*;
import com.ruoyi.client.domain.vo.ResStatus;
import com.ruoyi.client.domain.vo.ResultVO;
import com.ruoyi.client.mapper.*;
import com.ruoyi.client.service.TeamService;
import com.ruoyi.client.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private AthleteMapper athleteMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private TeamInfoMapper teamInfoMapper;
    @Autowired
    private ArrangementMapper arrangementMapper;



    @Override
    @Transactional
    public ResultVO createTeam(TeamDto teamDto) {
        List<Athlete> ids = new ArrayList<>();
        List<TeamInfo> teamInfos = new ArrayList<>();
        String sex = "-1";
        for (Integer o : teamDto.getStudents()) {
//            Example example = new Example(Athlete.class);
//            example.and().andEqualTo("studentId",o);
            LambdaQueryWrapper<Athlete> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Athlete::getStudentId,o);
             List<Athlete> athletes1 = athleteMapper.selectList(lambdaQueryWrapper);
//            List<Athlete> athletes1 = athleteMapper.selectByExample(example);


            if (athletes1.size()==0){
//                Student student = studentMapper.selectByPrimaryKey(o);
                Student student = studentMapper.selectById(o);
                student.setIsAthlete("1");
//                int i1 = studentMapper.updateByPrimaryKey(student);
                 int i1 = studentMapper.updateById(student);
                if(sex.equals("-1")){
                    sex = student.getGender();
                }else{
                    if (!sex.equals(student.getGender()))
                        return new ResultVO(ResStatus.NO,"队伍性别不一致",null);
                }
                //更新运动员表字段-------------------------
                Athlete athlete = new Athlete();
                athlete.setStudentId(Long.valueOf(o));


                //判断此学生之前是否为运动员，是的话则不再分配编号，也不能再插入运动员表

                    //若此学生之前不是运动员----为运动员安排编号---根据学生表主键id来生成
                    String athleteNumber = MyUtil.generateAthleteNumber(Long.valueOf(o));
                    athlete.setNumber(athleteNumber);

                    athlete.setCreateTime(new Date());
                    athlete.setUpdateTime(new Date());
                    athlete.setOther(student.getGender());

                    athleteMapper.save(athlete);
                    ids.add(athlete);
                    //----------------------------
            }else{
                ids.add(athletes1.get(0));
                if(sex.equals("-1")){
                    sex = athletes1.get(0).getOther();
                }else{
                    if (!sex.equals(athletes1.get(0).getOther()))
                        return new ResultVO(ResStatus.NO,"队伍性别不一致",null);
                }
            }
        }
        Team team = new Team();
        team.setAthleteId(ids.get(0).getAthleteId());
        team.setNumber(teamDto.getTeamName());
        team.setCreateTime(new Date());
        team.setUpdateTime(new Date());
        int time = teamMapper.insert(team);

        System.out.println(team);

        for (Athlete id : ids) {
            TeamInfo teamInfo = new TeamInfo();
            teamInfo.setTeamId(team.getTeamId());
            teamInfo.setAthleteId(id.getAthleteId());
            teamInfos.add(teamInfo);
        }
        int teamInfo = teamInfoMapper.createTeamInfo(teamInfos);
        if(teamInfo>0 && time>0 )
            return new ResultVO(ResStatus.OK,"添加成功",null);
        else
            return new ResultVO(ResStatus.NO,"添加失败",null);
    }

    @Override
    public ResultVO selectTeamByStudentId(Integer typeId) {
        List<TeamInfo> teamInfos = teamInfoMapper.selectTeamByStudentId(typeId);
        if (teamInfos.size()>0) return new  ResultVO(ResStatus.OK,"查询团队成功",teamInfos);
        return new ResultVO(ResStatus.NO,"查询团队失败",null);
    }

    @Transactional
    public ResultVO deleteTeam(TeamDto teamDto){
//        LambdaQueryWrapper<Arrangement> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(Arrangement::getTypeId,teamDto.getTeamId())
//            .eq(Arrangement::getType,"1");
//        List<Arrangement> arrangements = arrangementMapper.selectList(queryWrapper);
//
//        for (Arrangement arrangement : arrangements) {
//
//        }


        LambdaQueryWrapper<TeamInfo> lqw = new LambdaQueryWrapper<>();
        Team team = teamMapper.selectById(teamDto.getTeamId());
        LambdaQueryWrapper<Athlete> lqw1 = new LambdaQueryWrapper<>();
        lqw1.eq(Athlete::getStudentId,teamDto.getTypeId());
        Athlete athlete = athleteMapper.selectOne(lqw1);
        if(!team.getAthleteId().equals(athlete.getAthleteId())){
            return new ResultVO(ResStatus.NO,"你不是组长不能删除",null);
        }
        int i = teamMapper.deleteById(teamDto.getTeamId());
        lqw.eq(TeamInfo::getTeamId,teamDto.getTeamId());
        int delete = teamInfoMapper.delete(lqw);
        if(i>0&&delete>0)
            return new ResultVO(ResStatus.OK,"删除成功",null);
        return new ResultVO(ResStatus.NO,"删除失败",null);
    }
}
