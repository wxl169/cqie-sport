package com.ruoyi.client.controller;

import com.ruoyi.client.domain.dto.UserLoginDTO;
import com.ruoyi.client.service.impl.UserServiceImpl;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 16956
 */
@Controller
@RequestMapping("/client/user")
public class UserController extends BaseController {

    @Resource
    private UserServiceImpl userService;
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "user/login";
    }

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "user/register";
    }
    @RequestMapping("/toIndex")
    public ModelAndView toIndex(String reason){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("reason",reason);
        modelAndView.setViewName("user/index");
        return  modelAndView;
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public R checkLogin(@RequestBody UserLoginDTO userLoginDTO) {
      if (userLoginDTO == null){
          return R.fail("请输入邮箱和密码");
      }
        if (StringUtils.isBlank(userLoginDTO.getEmail())){
            return R.fail("请输入邮箱");
        }
        if (StringUtils.isBlank(userLoginDTO.getPassword())){
            return R.fail("请输入密码");
        }
        return userService.getUserByEmailAndPassword(userLoginDTO.getEmail(), userLoginDTO.getPassword());
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public R register(@RequestBody Map<String, String> info) {
        if (info == null){
            return R.fail("请输入邮箱和密码");
        }
        return userService.addUserToRegister(info);
    }

}
