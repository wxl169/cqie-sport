package com.ruoyi.client.mapper;

import com.ruoyi.client.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表  Mapper 接口
 * </p>
 *
 * @author 16956
 * @since 2023-09-15
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据邮箱搜索用户
     *
     * @param email 邮箱
     */
    User selectUserByEmail(String email);

    /**
     * 根据学生id判断该学生是否创建账号
     * @param studentId 学生id
     * @param userTypeStudent 用户类型
     * @return 用户数据
     */
    User selectUserByType(Integer studentId, String userTypeStudent);
}
