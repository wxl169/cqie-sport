package com.ruoyi.client.mapper;

import com.ruoyi.client.domain.vo.MatchPankingVO;

import java.util.List;

/**
 * @Auther Mr lIU
 * @explanation
 * @date 2023/9/18 10:39
 */
public interface MatchPankingMapper {
    List<MatchPankingVO> find(String arrangement);
}
