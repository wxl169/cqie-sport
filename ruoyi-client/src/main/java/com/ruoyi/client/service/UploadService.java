package com.ruoyi.client.service;


import com.ruoyi.common.core.domain.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 16956
 */
public interface UploadService {
    /**
     * 上传图片
     *
     * @param fileUpload 图片地址
     * @return 图片路径
     */
    R uploadImg(MultipartFile fileUpload);
}
