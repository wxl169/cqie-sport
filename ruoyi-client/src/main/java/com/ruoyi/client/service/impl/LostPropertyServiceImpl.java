package com.ruoyi.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.client.domain.dto.LostPropertyAddDTO;
import com.ruoyi.client.domain.entity.LostProperty;
import com.ruoyi.client.domain.vo.LostPropertyVO;
import com.ruoyi.client.domain.vo.PageVO;
import com.ruoyi.client.mapper.LostPropertyMapper;
import com.ruoyi.client.service.ILostPropertyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.constant.LostPropertyConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 16956
 * @since 2023-10-05
 */
@Service
public class LostPropertyServiceImpl extends ServiceImpl<LostPropertyMapper, LostProperty> implements ILostPropertyService {

    @Override
    public R addLostProperty(LostPropertyAddDTO lostPropertyAddDTO) {
        if (StringUtils.isBlank(lostPropertyAddDTO.getLostName())){
            return R.fail("请输入丢失物品名！");
        }
        if (StringUtils.isBlank(lostPropertyAddDTO.getLostPlace())){
            return R.fail("请输入丢失地点名");
        }
        if (lostPropertyAddDTO.getLostTime() == null){
            return  R.fail("请输入拾取时间！");
        }
        LostProperty copy = BeanCopyUtils.copy(lostPropertyAddDTO, LostProperty.class);
        copy.setStatus(0);
        boolean save = this.save(copy);
        if (!save){
            return R.fail("新增失败!");
        }
        return R.ok();
    }


    @Override
    public R getLostProperty(Integer pageNum, Integer pageSize) {
        if(pageNum == null){
            pageNum = 1;
        }
        if (pageSize  == null){
            pageSize = 8;
        }
        Page<LostProperty> page = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<LostProperty> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LostProperty::getStatus, LostPropertyConstants.RECEIVE_NOT_STATUS);
        this.page(page,queryWrapper);
        List<LostPropertyVO> lostPropertyVOS = BeanCopyUtils.copyList(page.getRecords(), LostPropertyVO.class);
        return R.ok(new PageVO(lostPropertyVOS,page.getTotal()));
    }


}
