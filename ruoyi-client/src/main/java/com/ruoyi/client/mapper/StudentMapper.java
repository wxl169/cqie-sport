package com.ruoyi.client.mapper;

import com.ruoyi.client.domain.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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



    List<Student> selectStudent(Integer id);
    /**
     * 修改学生信息
     *
     * @param studentId 学生id
     * @param phoneNumber 手机号
     * @return 是否修改成功
     */
    boolean updateSutdentPhone(@Param("studentId") Long studentId, @Param("phoneNumber") String phoneNumber);
}
