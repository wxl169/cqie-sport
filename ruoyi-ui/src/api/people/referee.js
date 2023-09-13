import request from '@/utils/request'

// 查询裁判员管理列表
export function listReferee(query) {
  return request({
    url: '/people/referee/list',
    method: 'get',
    params: query
  })
}

// 查询裁判员管理详细
export function getReferee(refereeId) {
  return request({
    url: '/people/referee/' + refereeId,
    method: 'get'
  })
}

// 新增裁判员管理
export function addReferee(data) {
  return request({
    url: '/people/referee',
    method: 'post',
    data: data
  })
}

// 修改裁判员管理
export function updateReferee(data) {
  return request({
    url: '/people/referee',
    method: 'put',
    data: data
  })
}

// 删除裁判员管理
export function delReferee(refereeId) {
  return request({
    url: '/people/referee/' + refereeId,
    method: 'delete'
  })
}
