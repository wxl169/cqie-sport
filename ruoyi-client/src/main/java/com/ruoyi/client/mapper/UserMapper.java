package com.ruoyi.client.mapper;

import com.ruoyi.client.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.client.domain.vo.UserInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    User selectUserByType(@Param("studentId") Long studentId, @Param("userTypeStudent") String userTypeStudent);

    /**
     * 查找学生信息
     * @param userId 学生id
     * @return 学生信息
     */
    UserInfoVO selectStudentInfo(Long userId);
}
