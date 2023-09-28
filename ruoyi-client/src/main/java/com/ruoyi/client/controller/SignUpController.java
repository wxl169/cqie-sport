package com.ruoyi.client.controller;


import com.ruoyi.client.domain.dto.TeamDto;
import com.ruoyi.client.domain.vo.ResultVO;
import com.ruoyi.client.service.ArrangementService;
import com.ruoyi.client.service.ProjectService;
import com.ruoyi.client.service.TeamService;
import com.ruoyi.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/client/system")
public class SignUpController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ArrangementService arrangementService;
    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;




    @GetMapping("/signup")
    public ResultVO listSignUpNeedInfo() {
        ResultVO resultVO = projectService.listSignUpNeedInfo();
        return resultVO;
    }

    @PostMapping("/sign")

    public ResultVO signUp(@RequestBody Map<String, String> info) {
        ResultVO resultVO = arrangementService.addSignUp(info);
        return resultVO;
    }


    @GetMapping("/selectStudent/{typeId}")

    public ResultVO selectStudent(@PathVariable Integer typeId){
        ResultVO resultVO = userService.selectStudent(typeId);
        return resultVO;
    }

    @PostMapping("/team")

    public ResultVO createTeam(@RequestBody TeamDto teamDto){
        System.out.println(teamDto);
        ResultVO resultVO = teamService.createTeam(teamDto);
        return resultVO;
    }

    @GetMapping("/selectTeamByStudentId/{typeId}")
    public ResultVO selectTeamByStudentId(@PathVariable Integer typeId){

        return teamService.selectTeamByStudentId(typeId);
    }

    @DeleteMapping("/deleteTeam")
    public ResultVO deleteTeam(@RequestBody TeamDto teamDto){
        ResultVO resultVO = teamService.deleteTeam(teamDto);
        return resultVO;
    }

}
