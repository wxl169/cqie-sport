package com.ruoyi.client.mapper;

import com.ruoyi.client.domain.vo.MatchPankingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther Mr lIU
 * @explanation
 * @date 2023/9/18 10:39
 */
@Mapper
public interface MatchPankingMapper {
    List<MatchPankingVO> find(String arrangement,int type,String projectName);
}
