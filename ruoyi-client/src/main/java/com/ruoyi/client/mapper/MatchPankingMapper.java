package com.ruoyi.client.mapper;

import com.ruoyi.client.domain.dto.MatchTypeDTO;
import com.ruoyi.client.domain.vo.MatchPankingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther 邬文莱
 * @explanation
 * @date 2023/9/18 10:39
 */
@Mapper
public interface MatchPankingMapper {
    List<MatchPankingVO> find(int type,String projectName);

    List<MatchTypeDTO> findTerms();
}
