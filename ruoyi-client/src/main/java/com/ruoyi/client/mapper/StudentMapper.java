package com.ruoyi.client.mapper;

import com.ruoyi.client.domain.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 学生表  Mapper 接口
 * </p>
 *
 * @author 16956
 * @since 2023-09-15
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    /**
     * 修改学生信息
     *
     * @param studentId 学生id
     * @param phoneNumber 手机号
     * @return 是否修改成功
     */
    boolean updateSutdentPhone(@Param("studentId") Integer studentId, @Param("phoneNumber") String phoneNumber);
}
