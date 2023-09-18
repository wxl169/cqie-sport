package com.ruoyi.client.controller;

import com.ruoyi.client.domain.dto.UserLoginDTO;
import com.ruoyi.client.service.impl.UserServiceImpl;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 16956
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserServiceImpl userService;

    @PostMapping("/login")
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

    @PostMapping("/register")
    public R register(@RequestBody Map<String, String> info) {
        return userService.addUserToRegister(info);
    }

}