package com.ruoyi.client.controller;


import com.ruoyi.client.domain.dto.LostPropertyAddDTO;
import com.ruoyi.client.service.ILostPropertyService;
import com.ruoyi.common.core.domain.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/client/lostProperty")
public class LostPropertyController {
    @Resource
    private ILostPropertyService lostPropertyService;


    /**
     * 添加丢失物品信息
     *
     * @param lostPropertyAddDTO 丢失物品信息
     * @return 是否添加成功
     */
    @PostMapping("/add")
    public R addLostProperty(@RequestBody LostPropertyAddDTO lostPropertyAddDTO){
        if (lostPropertyAddDTO == null){
            return R.fail("请输入丢失物品信息");
        }
        return lostPropertyService.addLostProperty(lostPropertyAddDTO);
    }


    /**
     * 获取丢失物品信息
     *
     * @param pageNum 页码
     * @param pageSize 每页数据量
     * @return 数据
     */
    @GetMapping("/get")
    public R getLostProperty(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize){
        if(pageNum == null){
            pageNum = 1;
        }
        if (pageSize  == null){
            pageSize = 8;
        }
        return lostPropertyService.getLostProperty(pageNum,pageSize);
    }



}
