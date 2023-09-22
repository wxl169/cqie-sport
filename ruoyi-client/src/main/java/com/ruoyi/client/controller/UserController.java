package com.ruoyi.client.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.ruoyi.client.domain.dto.LogoutDTO;
import com.ruoyi.client.domain.dto.UserInfoDTO;
import com.ruoyi.client.domain.dto.UserLoginDTO;
import com.ruoyi.client.domain.dto.UserUpdateDTO;
import com.ruoyi.client.service.UserService;
import com.ruoyi.client.service.impl.UserServiceImpl;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.UserConstants;
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
import java.util.Objects;

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
     * 前往个人详情页
     *
     * @return 个人详情页
     */
    @RequestMapping("/toMyInfo")
    public String toMyInfo(){
        return "user/my";
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

    /**
     * 获取当前登陆用户信息
     *
     * @param userId 用户的id和类型
     * @param type 用戶類型
     * @param typeId 是否為本校學生
     * @param token token
     * @return 用户信息
     */
    @RequestMapping(value = "/myInfo")
    @ResponseBody
    public R myInfo(@RequestParam("userId") String userId,@RequestParam("type") String type,
                    @RequestParam(value = "typeId",required = false) String typeId,@RequestParam("token")String token) {
        //判断当前用户是否登录
        if (!userService.judgeLogin(token)){
            return R.fail("请登录账号");
        }
        UserInfoDTO userInfoDTO  = new UserInfoDTO();
        userInfoDTO.setUserId(Long.valueOf(userId));
        userInfoDTO.setType(type);
        if (!UserConstants.USER_NULL.equals(typeId) && typeId != null){
            userInfoDTO.setTypeId(Integer.valueOf(typeId));
        }
        return userService.getUserInfo(userInfoDTO);
    }

    /**
     * 通过token获取登录用户信息
     *
     * @param token 密匙
     * @return 用户信息
     */
    @RequestMapping(value = "/getLoginUserInfo/{token}")
    @ResponseBody
    public R getLoginUserInfo(@PathVariable("token") String token) {
        //判断当前用户是否登录
        if (!userService.judgeLogin(token)){
            return R.fail("请登录账号");
        }
        return userService.getLoginUserInfo(token);
    }

    /**
     * 修改用户信息
     *
     * @param userUpdateDTO 传入的信息参数
     * @return 修改是否成功
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public R updateUserInfo(@RequestBody UserUpdateDTO userUpdateDTO){
        if (userUpdateDTO == null){
            return R.fail("请求参数错误");
        }
        return userService.updateUserInfo(userUpdateDTO);
    }

}
