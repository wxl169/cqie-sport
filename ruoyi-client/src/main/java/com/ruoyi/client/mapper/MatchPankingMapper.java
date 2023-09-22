package com.ruoyi.client.mapper;

import com.ruoyi.client.domain.vo.MatchPankingVO;
import com.ruoyi.client.domain.vo.MatchTypeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther 邬文莱
 * @explanation
 * @date 2023/9/18 10:39
 */
@Mapper
public interface MatchPankingMapper {
    List<MatchPankingVO> find(@Param("type") String type,@Param("projectName") String projectName);

    List<MatchTypeVO> findTerms();
}
