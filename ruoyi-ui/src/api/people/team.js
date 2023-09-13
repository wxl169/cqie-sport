import request from '@/utils/request'

// 查询团体管理列表
export function listTeam(query) {
  return request({
    url: '/people/team/list',
    method: 'get',
    params: query
  })
}

// 查询团体管理详细
export function getTeam(teamId) {
  return request({
    url: '/people/team/' + teamId,
    method: 'get'
  })
}

// 新增团体管理
export function addTeam(data) {
  return request({
    url: '/people/team',
    method: 'post',
    data: data
  })
}

// 修改团体管理
export function updateTeam(data) {
  return request({
    url: '/people/team',
    method: 'put',
    data: data
  })
}

// 删除团体管理
export function delTeam(teamId) {
  return request({
    url: '/people/team/' + teamId,
    method: 'delete'
  })
}
