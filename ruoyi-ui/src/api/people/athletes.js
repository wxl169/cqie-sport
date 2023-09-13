import request from '@/utils/request'

// 查询运动员管理 列表
export function listAthletes(query) {
  return request({
    url: '/people/athletes/list',
    method: 'get',
    params: query
  })
}

// 查询运动员管理 详细
export function getAthletes(athleteId) {
  return request({
    url: '/people/athletes/' + athleteId,
    method: 'get'
  })
}

// 新增运动员管理 
export function addAthletes(data) {
  return request({
    url: '/people/athletes',
    method: 'post',
    data: data
  })
}

// 修改运动员管理 
export function updateAthletes(data) {
  return request({
    url: '/people/athletes',
    method: 'put',
    data: data
  })
}

// 删除运动员管理 
export function delAthletes(athleteId) {
  return request({
    url: '/people/athletes/' + athleteId,
    method: 'delete'
  })
}
