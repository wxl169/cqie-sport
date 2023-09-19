package com.ruoyi.client.mapper;

import com.ruoyi.client.domain.vo.MatchPankingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Auther 邬文莱
 * @explanation
 * @date 2023/9/18 10:39
 */
@Mapper
public interface MatchPankingMapper {
    List<MatchPankingVO> find(String arrangement,int type,String projectName);

    Map<String,Object> findTerms();
}
