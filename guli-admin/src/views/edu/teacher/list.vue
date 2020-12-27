<template>
    <div class="app-container">

        <!--查询表单-->
        <el-form :inline="true" class="demo-form-inline">
            <el-form-item style="width:140px">
                <el-input v-model="teacherQuery.name" placeholder="讲师名"/>
            </el-form-item>

            <el-form-item style="width:180px">
                <el-select v-model="teacherQuery.level" clearable placeholder="讲师头衔">
                    <el-option :value="1" label="高级讲师"/>
                    <el-option :value="2" label="首席讲师"/>
                </el-select>
            </el-form-item>

            <el-form-item label="添加时间">
                <el-date-picker
                    v-model="teacherQuery.begin"
                    type="datetime"
                    placeholder="选择开始时间"
                    value-format="yyyy-MM-dd HH:mm:ss"
                    default-time="00:00:00"
                />
            </el-form-item>

            <el-form-item>
                <el-date-picker
                    v-model="teacherQuery.end"
                    type="datetime"
                    placeholder="选择截止时间"
                    value-format="yyyy-MM-dd HH:mm:ss"
                    default-time="00:00:00"
                />
            </el-form-item>

            <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
            <el-button type="default" @click="resetData()">清空</el-button>
        </el-form>

        <!-- 表格 -->
        <el-table
            v-loading="listLoading"
            :data="list"
            element-loading-text="数据加载中"
            border
            fit
            highlight-current-row>

            <el-table-column
                label="序号"
                width="70"
                align="center">
                <template slot-scope="scope">
                    {{ (page - 1) * limit + scope.$index + 1 }}
                </template>
            </el-table-column>

            <el-table-column prop="id" label="ID" />

            <el-table-column prop="name" label="名称" width="80" />

            <el-table-column label="头衔" width="80">
                <template slot-scope="scope">
                    {{ scope.row.level===1?'高级讲师':'首席讲师' }}
                </template>
            </el-table-column>

            <el-table-column prop="intro" label="资历" width="160" />

            <el-table-column prop="gmtCreate" label="添加时间" width="160"/>

            <el-table-column prop="sort" label="排序" width="60" />


            <el-table-column label="操作" width="200" align="center">
                <template slot-scope="scope">
                    <router-link :to="'/teacher/edit/'+scope.row.id"><!-- 实现路由跳转 -->
                        <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
                    </router-link>
                    <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 分页 -->
        <el-pagination
            :current-page="page"
            :page-size="limit"
            :total="total"
            style="padding: 30px 0; text-align: center;"
            layout="total, prev, pager, next, jumper"
            @current-change="getList"
        />

    </div>
</template>
<script>
    //引入调用teacher.js文件
    import teacher from '@/api/edu/teacher'
    export default {
        //核心代码
        data(){//定义变量和初始值
            return{
                list:null,//查询接口之后返回的数据集合
                page:1,//当前页
                limit:5,//每页记录数
                total:0,
                teacherQuery:{},//条件封装对象
            }
        },
        created(){//页面渲染之前执行，一般调用methods定义的方法
            this.getList()
        },
        methods:{//创建具体的方法，调用teacher.js中的方法
            //分页按条件查询讲师信息
            getList(page = 1){
                this.page = page
                teacher.getTeacherListPage(this.page,this.limit,this.teacherQuery)
                .then(res =>{
                    //请求成功
                    //res表示接口返回的数据
                    //console.log(res)
                    this.list = res.data.rows
                    this.total = res.data.total
                    console.log(this.list)
                    console.log(this.total)
                })
                .catch(error =>{
                    //请求失败
                    console.log(error)
                })
            },

            //清空按钮的方法
            resetData() {
                //表单输入项数据清空
                this.teacherQuery = {}
                //查询所有讲师数据
                this.getList()
            },

            //逻辑删除讲师信息
            removeDataById(id){
                this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                })
                .then(res =>{
                    //调用删除方法
                    teacher.deleteTeacherId(id)
                    .then(() =>{//删除成功后
                        //提示信息
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        })
                        //刷新页面信息
                        this.getList()
                    })
                })
            }
        }
    }
</script>