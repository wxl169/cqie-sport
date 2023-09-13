import request from '@/utils/request'

// 查询班级管理列表
export function listClass(query) {
  return request({
    url: '/project/class/list',
    method: 'get',
    params: query
  })
}

// 查询班级管理详细
export function getClass(classId) {
  return request({
    url: '/project/class/' + classId,
    method: 'get'
  })
}

// 新增班级管理
export function addClass(data) {
  return request({
    url: '/project/class',
    method: 'post',
    data: data
  })
}

// 修改班级管理
export function updateClass(data) {
  return request({
    url: '/project/class',
    method: 'put',
    data: data
  })
}

// 删除班级管理
export function delClass(classId) {
  return request({
    url: '/project/class/' + classId,
    method: 'delete'
  })
}
