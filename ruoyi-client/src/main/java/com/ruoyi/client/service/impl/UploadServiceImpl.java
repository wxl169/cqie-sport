package com.ruoyi.client.service.impl;

import com.ruoyi.client.service.UploadService;
import com.ruoyi.common.constant.UploadConstants;
import com.ruoyi.common.core.domain.R;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @author 16956
 */

@Service
public class UploadServiceImpl implements UploadService {

    /**
     * 上传头像
     *
     * @param fileUpload 图片地址
     * @return 图片保存路径
     */
    @Override
    public R uploadImg(MultipartFile fileUpload) {
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        if (fileName == null){
            return R.fail("图片为空");
        }
        //获取文件后缀名。也可以在这里添加判断语句，规定特定格式的图片才能上传，否则拒绝保存。
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //判断图片格式
        if (!suffixName.equalsIgnoreCase(UploadConstants.UPLOAD_FORMAT)) {
            return R.fail("上传头像图片只能是 JPG 格式!");
        }

            //判断图片大小
        if (fileUpload.getSize() > UploadConstants.IMG_SIZE) {
            return R.fail("上传头像图片大小不能超过 2MB!");
        }
        //为了避免发生图片替换，这里使用了文件名重新生成
        fileName = UUID.randomUUID() + suffixName;
        try {
            //文件路径
            String uploadPath = ResourceUtils.getFile(UploadConstants.IMG_HEAD).getAbsolutePath();
            String filePath = uploadPath + File.separator + fileName;
            File dest = new File(filePath);
            //检查是否有该目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            //将图片保存到文件夹里
            fileUpload.transferTo(dest);
            return R.ok("上传成功",fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("上传失败");
        }
    }
}
