import request from '@/utils/request'

// 查询学生管理列表
export function listStudent(query) {
  return request({
    url: '/people/student/list',
    method: 'get',
    params: query
  })
}

// 查询学生管理详细
export function getStudent(studentId) {
  return request({
    url: '/people/student/' + studentId,
    method: 'get'
  })
}

// 新增学生管理
export function addStudent(data) {
  return request({
    url: '/people/student',
    method: 'post',
    data: data
  })
}

// 修改学生管理
export function updateStudent(data) {
  return request({
    url: '/people/student',
    method: 'put',
    data: data
  })
}

// 删除学生管理
export function delStudent(studentId) {
  return request({
    url: '/people/student/' + studentId,
    method: 'delete'
  })
}
