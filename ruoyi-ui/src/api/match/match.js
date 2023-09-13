import request from '@/utils/request'

// 查询比赛记录 列表
export function listMatch(query) {
  return request({
    url: '/match/match/list',
    method: 'get',
    params: query
  })
}

// 查询比赛记录 详细
export function getMatch(competitionId) {
  return request({
    url: '/match/match/' + competitionId,
    method: 'get'
  })
}

// 新增比赛记录 
export function addMatch(data) {
  return request({
    url: '/match/match',
    method: 'post',
    data: data
  })
}

// 修改比赛记录 
export function updateMatch(data) {
  return request({
    url: '/match/match',
    method: 'put',
    data: data
  })
}

// 删除比赛记录 
export function delMatch(competitionId) {
  return request({
    url: '/match/match/' + competitionId,
    method: 'delete'
  })
}
