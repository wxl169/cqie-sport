import request from '@/utils/request'

// 查询安排 列表
export function listArrangement(query) {
  return request({
    url: '/match/arrangement/list',
    method: 'get',
    params: query
  })
}

// 查询安排 详细
export function getArrangement(arrangementId) {
  return request({
    url: '/match/arrangement/' + arrangementId,
    method: 'get'
  })
}

// 新增安排
export function addArrangement(data) {
  return request({
    url: '/match/arrangement',
    method: 'post',
    data: data
  })
}

// 修改安排
export function updateArrangement(data) {
  return request({
    url: '/match/arrangement',
    method: 'put',
    data: data
  })
}

// 删除安排
export function delArrangement(arrangementId) {
  return request({
    url: '/match/arrangement/' + arrangementId,
    method: 'delete'
  })
}
