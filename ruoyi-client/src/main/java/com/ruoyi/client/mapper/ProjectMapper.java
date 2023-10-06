package com.ruoyi.client.mapper;

import com.ruoyi.client.domain.entity.Project;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.client.domain.general.GeneralDAO;
import com.ruoyi.client.domain.vo.ProjectPageVO;
import com.ruoyi.client.domain.vo.SignUpVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 项目表  Mapper 接口
 * </p>
 *
 * @author 16956
 * @since 2023-09-25
 */
@Repository
public interface ProjectMapper extends BaseMapper<Project>, GeneralDAO<Project> {
    /**
     * 报名页中，返回下拉框中需要显示的赛事信息
     * @return
     */
    public List<SignUpVO> selectSignUpNeedInfo();


    /**
     * 赛事公布页中，根据赛事编号来查询
     * @param projectNumber
     * @return
     */
    public SignUpVO selectProjectByNumber(String projectNumber);

    /**
     * 赛事公布页中，根据赛事名来查询---模糊查询
     * @param projectName
     * @return
     */
    public List<SignUpVO> selectProjectsByName(String projectName);

    /**
     * 赛事公布页中，根据赛事类别来查询
     * @param projectType
     * @return
     */
    public List<SignUpVO> selectProjectsByType(String projectType);


    /**
     * 查询被取消的比赛
     * @return
     */
    public List<SignUpVO> selectSignUpNeedInfoCancel();


    /**
     * 查询比赛列表
     * @return
     */
    public List<SignUpVO>selectSignUpNeedInfoAdmin();

    /**
     * 获取预报名的比赛名
     *
     * @param pageNum 页码
     * @param pageSize 每页数据量
     * @return 分页数据
     */
    List<ProjectPageVO> getProjectPage(@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize);

    /**
     * 一共有多少数据
     * @return
     */
    Long getProjectPageTotal();
}
