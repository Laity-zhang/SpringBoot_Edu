<template>
    <div class="app-container">
        <el-form label-width="120px">

            <el-form-item label="讲师名称">
                <el-input v-model="teacher.name"/>
            </el-form-item>

            <el-form-item label="讲师排序">
                <el-input-number v-model="teacher.sort" controls-position="right" :min="0"/>
            </el-form-item>
      
            <el-form-item label="讲师头衔">
                <el-select v-model="teacher.level" clearable placeholder="请选择">
                    <!--
                        数据类型一定要和取出的json中的一致，否则没法回填
                        因此，这里value使用动态绑定的值，保证其数据类型是number
                    -->
                    <el-option :value="1" label="高级讲师"/>
                    <el-option :value="2" label="首席讲师"/>
                </el-select>
            </el-form-item>

            <el-form-item label="讲师资历">
                <el-input v-model="teacher.career"/>
            </el-form-item>

            <el-form-item label="讲师简介">
                <el-input v-model="teacher.intro" :rows="10" type="textarea"/>
            </el-form-item>

            <!-- 讲师头像 -->
            <el-form-item label="讲师头像">

                <!-- 头衔缩略图 -->
                <pan-thumb :image="teacher.avatar"/>
                <!-- 文件上传按钮 -->
                <el-button type="primary" icon="el-icon-upload" @click="imagecropperShow=true">更换头像
                </el-button>

                <!--
                    v-show：是否显示上传组件
                    :key：类似于id，如果一个页面多个图片上传控件，可以做区分
                    :url：后台上传的url地址
                    @close：关闭上传组件
                    @crop-upload-success：上传成功后的回调 -->
                    <image-cropper
                        v-show="imagecropperShow"
                        :width="300"
                        :height="300"
                        :key="imagecropperKey"
                        :url="BASE_API+'/eduoss/fileoss'"
                        field="file"
                        @close="close"
                        @crop-upload-success="cropSuccess"/>

            </el-form-item>


            <el-form-item>
                <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
            </el-form-item>
        </el-form>

    </div>
</template>

<script>
    //引入调用teacher.js文件
    import teacherApi from '@/api/edu/teacher'
    //引入组件
    import ImageCropper from '@/components/ImageCropper'
    import PanThumb from '@/components/PanThumb'
    export default {
        components: { ImageCropper, PanThumb },//声明上传头像的组件
        data() {
            return {
                teacher: {
                    name: '',
                    sort: 0,
                    level: 1,
                    career: '',
                    intro: '',
                    avatar: ''
                },
                //上传弹框组件是否显示
                imagecropperShow:false,//上传组件id
                imagecropperKey:0,//上传组件key值
                BASE_API:process.env.BASE_API,//获取dev.env.js里面的地址
                saveBtnDisabled: false // 保存按钮是否禁用,
            }
        },

        created(){//页面渲染之前执行
            this.init()
        },

        watch:{//监听
            $route(to,from){//路由变化方式，当路由发生变化时，执行此方法   
                this.init()
            }
        },

        methods: {
            //关闭上传弹框的方法
            close(){
                this.imagecropperShow = false;//关闭弹窗（判断imagecropperShow的值）
                this.imagecropperKey = this.imagecropperKey + 1;//做上传弹框初始化
            },
            //上传成功的方法
            cropSuccess(data){
                this.imagecropperShow = false;
                //上传之后接口返回图片地址
                this.teacher.avatar = data.url
                this.imagecropperKey = this.imagecropperKey + 1;//做上传弹框初始化
            },
            //初始化
            init(){
                //判断路径中是否有id，跳转到修改页面修改数据（此时数据回显）
                if(this.$route.params && this.$route.params.id){
                    //从路径中获取id
                    const id = this.$route.params.id
                    //调用根据id查询的方法
                    this.getInfo(id)
                }
                else{//当路径中无id是，跳转到添加页面添加数据（此时页面无数据）
                    //清空表单
                    this.teacher = {}
                }
            },
            //根据讲师id查询的方法
            getInfo(id){
                teacherApi.getTeacherInfo(id)
                .then(res =>{
                    console.log(res.data)
                    this.teacher = res.data.items
                })
            },

            //判断保存按钮执行的是添加操作还是修改操作
            saveOrUpdate() {
               if(!this.teacher.id){
                   //执行添加操作
                   this.saveTeaecher()
               }
               else{
                   //执行修改操作
                    this.updateTeacher()
               }
            },

            //修改讲师信息
            updateTeacher(){
                teacherApi.updateTeacherInfo(this.teacher)
                    .then(res =>{
                        this.$message({
                        type: 'success',
                        message: '修改成功!'
                    })
                    //跳转到列表页面
                    this.$router.push({path:'/teacher/table'})
                    })
            },

            // 添加讲师的方法
            saveTeaecher() {
                teacherApi.addTeacher(this.teacher)
                .then(res =>{
                    //提示信息
                    this.$message({
                        type: 'success',
                        message: '添加成功!'
                    })
                    //跳转到列表页面
                    this.$router.push({path:'/teacher/table'})
                })
                .catch()
            }

        }
    }
</script>