package com.ruoyi.client.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.client.domain.entity.*;
import com.ruoyi.client.domain.vo.ResStatus;
import com.ruoyi.client.domain.vo.ResultVO;
import com.ruoyi.client.mapper.*;
import com.ruoyi.client.service.ArrangementService;
import com.ruoyi.client.util.MyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Slf4j
@Service
public class ArrangementServiceImpl implements ArrangementService {

    @Autowired
    private ArrangementMapper arrangementMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private AthleteMapper athleteMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private TeamMapper teamMapper;

    /**
     * 将一条报名信息插入报名表
     * @param info
     * @return
     */
    @Override
    @Transactional
    public ResultVO addSignUp(Map<String, String> info) {
        log.info("info,{}",info);
        String type = info.get("type");//身份
        Long studentId = Long.valueOf(info.get("typeId2"));
        if (!type.equals("0") && !type.equals("2")){ //既不是学生也不是裁判员
            return new ResultVO(ResStatus.NO, "fail", null);
        }
        Long typeId = Long.valueOf(info.get("typeId"));//学生id
        Long projectId = Long.valueOf(info.get("projectId"));
        String projectType = info.get("projectType");
        Long arrangeInfoId = Long.valueOf(info.get("arrangeInfoId"));

        Arrangement arrangement = new Arrangement();
        arrangement.setProjectId(projectId);
        arrangement.setType(type);
        arrangement.setInfoId(arrangeInfoId);
        arrangement.setIsCancel("0");
        arrangement.setCreateTime(new Date());
        arrangement.setUpdateTime(new Date());

        //若是学生，需要更新，学生表，运动员表，以及安排表
        if (type.equals("0")) {
            List<Athlete> athletes = new ArrayList<>();
            //typeId为学生id，学转换为运动员id
            Student student = null;
            if(projectType .equals("0")){
//                Student student = studentMapper.selectByPrimaryKey(typeId);
                student = studentMapper.selectById(typeId);
                student.setIsAthlete("1");
                int i1 = studentMapper.updateById(student);
            }

            //-------------
            boolean apply = true;
            if(projectType.equals("0")){
                //判断此学生之前是否为运动员，是的话则不再分配编号，也不能再插入运动员表
//                Example example1 = new Example(Athlete.class);
//                Example.Criteria criteria1 = example1.createCriteria();
//                criteria1.andEqualTo("studentId", typeId);
//                athletes = athleteMapper.selectByExample(example1);

                LambdaQueryWrapper<Athlete> lqw = new LambdaQueryWrapper<>();
                lqw.eq(Athlete::getStudentId,typeId);
                 athletes = athleteMapper.selectList(lqw);

                if (athletes.size()>0){
                    apply = isApply(athletes.get(0).getAthleteId(), projectId, projectType, arrangeInfoId);
                }
            }else{
                    apply = isApply(typeId,projectId,projectType,arrangeInfoId);
            }

            if (!apply) {
                return new ResultVO(ResStatus.NO, "yibaoming", null);
            }
            //若之前没报过此比赛---------------------------------
            //判断报名数是否已经满了
            boolean applyFull = isApplyFull(projectId, projectType, arrangeInfoId);
            if(!applyFull){
                return new ResultVO(ResStatus.NO, "yibaomingmanle", null);
            }
            //更新运动员表并添加报名信息---------------------
            int i = athleteApply(student,typeId, projectType, arrangement,studentId);
            if (i ==1) {
                return new ResultVO(ResStatus.OK, "success", null);
            }else if (i == 2) {
                return new ResultVO(ResStatus.NO, "你不是这队组长没权限", null);
            }else{
                return new ResultVO(ResStatus.NO, "fail", null);
            }
        }

        //若是裁判员，也需要先查找安排表中，此裁判员是否已经报名 referee_id
        if (type.equals("2")) {
          /*  Example example = new Example(Arrangement.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("projectId", projectId)
                    .andEqualTo("infoId", arrangeInfoId);
//                    .andEqualTo("refereeId", typeId);
            List<Arrangement> arrangements = arrangementMapper.selectByExample(example);*/

            LambdaQueryWrapper<Arrangement> lqw = new LambdaQueryWrapper<>();
            lqw.eq(Arrangement::getProjectId,projectId).eq(Arrangement::getInfoId,arrangeInfoId);
             List<Arrangement> arrangements = arrangementMapper.selectList(lqw);
            String RefereeId = "";
            if(arrangements.size() !=0){
                Arrangement arrangement1 = arrangements.get(0);
                String[] split = arrangement1.getRefereeId().split(",");
                if (Arrays.asList(split).contains(typeId.toString())) {
                    return new ResultVO(ResStatus.NO, "yibaoming", null);
                }
                //判断裁判报名人数是否已满
                Project project = projectMapper.selectById(projectId);
                if (split.length>=project.getRenum()){
                    return new ResultVO(ResStatus.NO, "yibaomingmanle", null);
                }
                if(!ObjectUtils.isEmpty(arrangement1.getRefereeId()))
                RefereeId = arrangement1.getRefereeId() + ",";
            }


            //若未报名，则可进行报名操作
            //根据：project_id、info_id更新arrangement表中的referee字段
            RefereeId = RefereeId + typeId;
            int i = arrangementMapper.updateRefereeInArrangement(projectId, arrangeInfoId, RefereeId);
            if (i > 0) {
                return new ResultVO(ResStatus.OK, "success", null);
            } else{
                return new ResultVO(ResStatus.NO, "fail", null);
            }
        }
        return new ResultVO(ResStatus.NO, "fail", null);
    }

    /**
     * 判断比赛是否自己已经报名
     * @param typeId
     * @param projectId
     * @param projectType
     * @param arrangeInfoId
     * @return
     */
    private boolean isApply(Long typeId ,Long projectId,String projectType,Long arrangeInfoId){
        //判断之前是否报过此比赛------是：project_id, info_id, type_id, type, is_cancel = 0
 /*       Example example = new Example(Arrangement.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", projectId)
                .andEqualTo("infoId", arrangeInfoId)
                .andEqualTo("typeId", typeId)
                .andEqualTo("type", projectType)
                .andEqualTo("isCancel", 0);*/
        LambdaQueryWrapper<Arrangement> lqw =new LambdaQueryWrapper<>();
        lqw.eq(Arrangement::getProjectId,projectId).eq(Arrangement::getInfoId,arrangeInfoId)
            .eq(Arrangement::getTypeId,typeId).eq(Arrangement::getType,projectType)
            .eq(Arrangement::getIsCancel,0);
         List<Arrangement> arrangements = arrangementMapper.selectList(lqw);
//        List<Arrangement> arrangements = arrangementMapper.selectByExample(example);
        if (arrangements.size()>0) return false;
        else return true;
    }

    /**
     * 判断报名是否已经到达上限
     * @return
     */
    private boolean  isApplyFull(Long projectId,String projectType,Long arrangeInfoId){
        //判断人数是否已经到达上限运动员
//            Example exampleArrangement = new Example(Arrangement.class);
//            exampleArrangement.and().andEqualTo("projectId",projectId)
//                    .andEqualTo("infoId", arrangeInfoId)
//                    .andEqualTo("type", projectType)
//                    .andEqualTo("isCancel", 0);
            LambdaQueryWrapper<Arrangement> lqw = new LambdaQueryWrapper<>();
            lqw.eq(Arrangement::getProjectId,projectId).eq(Arrangement::getInfoId,arrangeInfoId)
                .eq(Arrangement::getType,projectType).eq(Arrangement::getIsCancel,0);
         List<Arrangement> arrangements = arrangementMapper.selectList(lqw);
//        List<Arrangement> arrangements = arrangementMapper.selectByExample(exampleArrangement);
            Project project = projectMapper.selectById(projectId);
            if (arrangements.size()>=project.getUpnum()){
                return false;
            }
            return true;
    }

    private int athleteApply(Student student,Long typeId,String projectType,Arrangement arrangement,Long studentId){
        int insert = 0;
   /*     Example exampleArrangement = new Example(Arrangement.class);
        exampleArrangement.and().andEqualTo("projectId",arrangement.getProjectId())
                .andEqualTo("infoId", arrangement.getInfoId())
                .andEqualTo("type", projectType)
                .andEqualTo("isCancel", 0);*/
        LambdaQueryWrapper<Arrangement> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Arrangement::getProjectId,arrangement.getProjectId())
            .eq(Arrangement::getInfoId,arrangement.getInfoId())
            .eq(Arrangement::getType,projectType).eq(Arrangement::getIsCancel,0);
        List<Arrangement> arrangements = arrangementMapper.selectList(lqw);
        String refereeId = "";
//        List<Arrangement> arrangements = arrangementMapper.selectByExample(exampleArrangement);
        if (arrangements.size() != 0){
            refereeId = arrangements.get(0).getRefereeId();
        }
        arrangement.setRefereeId(refereeId);

        if (projectType.equals("0")){
            /*Example example1 = new Example(Athlete.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("studentId", typeId);*/
//            List<Athlete> athletes = athleteMapper.selectByExample(example1);

            LambdaQueryWrapper<Athlete> lqw1 = new LambdaQueryWrapper<>();
            lqw1.eq(Athlete::getStudentId,typeId);
            List<Athlete> athletes = athleteMapper.selectList(lqw1);
            if(athletes.size() == 0){
                Athlete athlete = new Athlete();
                athlete.setStudentId(typeId);
                String athleteNumber = MyUtil.generateAthleteNumber(typeId);
                athlete.setNumber(athleteNumber);
                athlete.setCreateTime(new Date());
                athlete.setUpdateTime(new Date());
                athlete.setOther(student.getGender());
                athleteMapper.insert(athlete);
                arrangement.setTypeId(athlete.getAthleteId());
            }
            arrangement.setTypeId(athletes.get(0).getAthleteId());//设置运动员个人id
            System.out.println("打印赛事安排");
            System.out.println(arrangement);
            insert = arrangementMapper.insert(arrangement);
            System.out.println("用户报名");
            System.out.println(insert);
        }else{
            /*Example example1 = new Example(Athlete.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("studentId", studentId);*/

            LambdaQueryWrapper<Athlete> lqw2 = new LambdaQueryWrapper<>();
            lqw2.eq(Athlete::getStudentId,studentId);
            List<Athlete> athletes = athleteMapper.selectList(lqw2);
//            List<Athlete> athletes = athleteMapper.selectByExample(example1);
            Athlete athlete = athletes.get(0);

            Team team = teamMapper.selectById(typeId);
            if(!Objects.equals(athlete.getAthleteId(), team.getAthleteId())) {
                insert = 2;
            }else{
                arrangement.setTypeId(typeId);//设置团队id
                arrangement.setType("1");
                insert=arrangementMapper.insert(arrangement);
            }

        }
        return insert;
    }

//    private boolean isRefereeApply(Integer projectId,Integer typeId,Integer arrangeInfoId){
//        Example example = new Example(Arrangement.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("projectId", projectId)
//                .andEqualTo("infoId", arrangeInfoId);
////                .andEqualTo("refereeId", typeId);
//        Arrangement arrangement1 = arrangementMapper.selectByExample(example).get(0);
//        String[] split = arrangement1.getRefereeId().split(",");
//        return Arrays.asList(split).contains(typeId); //包含裁判id就表明已经报名返回true
//    }
}
