package com.ruoyi.client.service.impl;

import com.ruoyi.client.domain.entity.TbProject;
import com.ruoyi.client.mapper.TbProjectDao;
import com.ruoyi.client.service.RefereeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther Mr lIU
 * @explanation
 * @date 2023/9/27 11:01
 */
@Service
public class RefereeServiceImpl implements RefereeService {
    private TbProjectDao tbProjectDao;
    @Override
    public List<TbProject> find() {
        return tbProjectDao.find();
    }
}
