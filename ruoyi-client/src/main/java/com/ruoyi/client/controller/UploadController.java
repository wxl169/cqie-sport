package com.ruoyi.client.controller;

import com.ruoyi.client.service.UploadService;
import com.ruoyi.common.core.domain.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author 16956
 */
@Controller
@RequestMapping("/client/file")
public class UploadController {
    @Resource
    private UploadService uploadService;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public R uploadImg(@RequestParam(value = "img",required = false) MultipartFile file){
        if (file.isEmpty()){
            return R.fail("请选择图片");
        }
        return  uploadService.uploadImg(file);
    }

}
