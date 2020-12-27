import request from '@/utils/request'

export default {
    //讲师列表  按条件分页查询讲师信息
    //参数介绍：current：当前页，limit；每页记录数，teacherQuery：查询条件对象
    getTeacherListPage(current, limit, teacherQuery) {
        return request({
            url: `/eduservice/teacher/pageQuery/${current}/${limit}`,
            method: 'post',
            //teacherQuery条件对象，后端使用RequestBody获取数据
            //data表示把对象转换为json进行传递到接口里面
            data: teacherQuery
        })
    },
    //删除讲师信息
    deleteTeacherId(id) {
        return request({
            url: `/eduservice/teacher/${id}`,
            method: 'delete',
        })
    },
    //添加讲师信息
    addTeacher(teacher) {
        return request({
            url: `/eduservice/teacher/addTeacher`,
            method: 'post',
            data: teacher
        })
    },
    //根据id查询讲师
    getTeacherInfo(id) {
        return request({
            url: `/eduservice/teacher/getTeacher/${id}`,
            method: 'get',
        })
    },
    //修改讲师信息
    updateTeacherInfo(teacher) {
        return request({
            url: `/eduservice/teacher/editTeacher`,
            method: 'post',
            data: teacher
        })
    }
}