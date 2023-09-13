import request from '@/utils/request'

// 查询学院管理列表
export function listCollege(query) {
  return request({
    url: '/project/college/list',
    method: 'get',
    params: query
  })
}

// 查询学院管理详细
export function getCollege(collegeId) {
  return request({
    url: '/project/college/' + collegeId,
    method: 'get'
  })
}

// 新增学院管理
export function addCollege(data) {
  return request({
    url: '/project/college',
    method: 'post',
    data: data
  })
}

// 修改学院管理
export function updateCollege(data) {
  return request({
    url: '/project/college',
    method: 'put',
    data: data
  })
}

// 删除学院管理
export function delCollege(collegeId) {
  return request({
    url: '/project/college/' + collegeId,
    method: 'delete'
  })
}
