package com.ruoyi.client.controller;

import com.ruoyi.client.domain.dto.LogoutDTO;
import com.ruoyi.client.domain.dto.UserLoginDTO;
import com.ruoyi.client.service.UserService;
import com.ruoyi.client.service.impl.UserServiceImpl;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户端：用户模块
 * @author 16956
 */
@Controller
@RequestMapping("/client/user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    /**
     * 跳转到登录
     *
     * @return 跳转到登录页面
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "user/login";
    }
    /**
     * 跳转到注册
     *
     * @return 注册界面
     */
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "user/register";
    }

    /**
     * 跳转到主页
     *
     * @param reason 判断是否登录
     * @return 主页面
     */
    @RequestMapping("/toIndex")
    public ModelAndView toIndex(String reason){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("reason",reason);
        modelAndView.setViewName("user/index");
        return  modelAndView;
    }

    /**
     * 用户登录
     *
     * @param userLoginDTO 邮箱及密码
     * @return 是否登录成功
     */
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

    /**
     * 用户注册
     *
     * @param info 注册信息
     * @return 是否注册成功
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @Log(title = "用户端注册账号 ", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @ResponseBody
    public R register(@RequestBody Map<String, String> info) {
        if (info == null){
            return R.fail("请输入邮箱和密码");
        }
        return userService.addUserToRegister(info);
    }


    /**
     * 用户端退出登录
     *
     * @return 是否退出成功
     */
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ResponseBody
    public R logout(@RequestBody LogoutDTO logoutDTO) {
        if (logoutDTO.getToken() == null){
            return R.fail("请先登录账号");
        }
        return userService.logout(logoutDTO.getToken());
    }
}
