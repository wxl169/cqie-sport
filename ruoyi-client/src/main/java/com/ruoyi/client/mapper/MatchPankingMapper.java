package com.ruoyi.client.mapper;

import com.ruoyi.client.domain.vo.MatchPankingVO;
import com.ruoyi.client.domain.vo.MatchTypeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 16956
 * @Auther 邬文莱
 * @explanation
 * @date 2023/9/18 10:39
 */
@Mapper
public interface MatchPankingMapper {
    /**
     * 根据项目名查询比赛排行榜信息
     *
     * @param projectName 项目名
     * @return 比赛排行信息
     */
    List<MatchPankingVO> find(@Param("projectName") String projectName);

}
