package com.ruoyi.client.service;

import com.ruoyi.client.domain.dto.LostPropertyAddDTO;
import com.ruoyi.client.domain.entity.LostProperty;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.domain.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 16956
 * @since 2023-10-05
 */
public interface ILostPropertyService extends IService<LostProperty> {

    /**
     * 添加丢失物品信息
     *
     * @param lostPropertyAddDTO 丢失物品信息
     * @return 是否添加成功
     */
    R addLostProperty(LostPropertyAddDTO lostPropertyAddDTO);

    /**
     * 获取丢失物品信息
     *
     * @param pageNum 页码
     * @param pageSize 每页数据量
     * @return 数据
     */
    R getLostProperty(Integer pageNum, Integer pageSize);
}
