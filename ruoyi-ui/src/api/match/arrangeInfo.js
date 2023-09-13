import request from '@/utils/request'

// 查询安排信息单元 列表
export function listArrangeInfo(query) {
  return request({
    url: '/match/arrangeInfo/list',
    method: 'get',
    params: query
  })
}

// 查询安排信息单元 详细
export function getArrangeInfo(arrangeInfoId) {
  return request({
    url: '/match/arrangeInfo/' + arrangeInfoId,
    method: 'get'
  })
}

// 新增安排信息单元 
export function addArrangeInfo(data) {
  return request({
    url: '/match/arrangeInfo',
    method: 'post',
    data: data
  })
}

// 修改安排信息单元 
export function updateArrangeInfo(data) {
  return request({
    url: '/match/arrangeInfo',
    method: 'put',
    data: data
  })
}

// 删除安排信息单元 
export function delArrangeInfo(arrangeInfoId) {
  return request({
    url: '/match/arrangeInfo/' + arrangeInfoId,
    method: 'delete'
  })
}
