package com.ruoyi.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther Mr lIU
 * @explanation
 * @date 2023/9/26 16:47
 */
@Controller
@RequestMapping("/client/system")
public class RefereeController {
    @RequestMapping("/toReferee")
    public String toReferee(){
        return "/referee/referee";
    }
}
