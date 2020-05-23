<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="box-card">
      <el-row :gutter="20">
        <el-col :span="9">
          <el-input placeholder="请输入内容" v-model="query" clearable>
            <el-button @click="getUserList(query)" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="6">
          <el-button type="primary" @click="dialogVisible = true">添加用户</el-button>
        </el-col>
      </el-row>
      <el-table :data="userList" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column prop="username" label="姓名"></el-table-column>
        <el-table-column prop="email" label="邮箱"></el-table-column>
        <el-table-column prop="mobile" label="电话"></el-table-column>
        <el-table-column prop="role_name" label="角色"></el-table-column>
        <el-table-column label="状态">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.mg_state" @change="userStateChanged(scope.row)"></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180px">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="showEditDialog(scope.row.id)"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="removeUserById(scope.row.id)"
            ></el-button>
            

            <el-tooltip
              class="item"
              effect="dark"
              content="分配角色"
              placement="top"
              :enterable="false"
            >
              <el-button type="warning" icon="el-icon-setting" size="mini" @click="setRole(scope.row)"></el-button>
            </el-tooltip>



          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pagenum"
        :page-sizes="[2, 4, 6, 8]"
        :page-size="pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
      <el-dialog title="添加用户" :visible.sync="dialogVisible" width="30%" @close="addDialogClosed">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="70px">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="ruleForm.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="ruleForm.password"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="ruleForm.email"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="mobile">
            <el-input v-model="ruleForm.mobile"></el-input>
          </el-form-item>
        </el-form>

        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addUser">确 定</el-button>
        </span>
      </el-dialog>

      <el-dialog
        title="修改用户"
        :visible.sync="editDialogVisible"
        width="30%"
        @close="editDialogClosed"
      >
        <el-form :model="editForm" ref="editFormRef" label-width="70px" :rules="editFormRules">
          <el-form-item label="用户名">
            <el-input v-model="editForm.username" disabled></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="editForm.email"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="mobile">
            <el-input v-model="editForm.mobile"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="editDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="printsuccess">确 定</el-button>
        </span>
      </el-dialog>
    </el-card>
    
    <!-- 分配角色的对话框 -->
    <el-dialog title="分配角色" :visible.sync="setRoleDialogVisible" width="50%" @close="setRoleDialogClosed">
      <div>
        <p>当前的用户：{{userInfo.username}}</p>
        <p>当前的角色：{{userInfo.role_name}}</p>
        <p>分配新角色：
          <el-select v-model="selectedRoleId" placeholder="请选择">
            <el-option v-for="item in rolesList" :key="item.id" :label="item.roleName" :value="item.id">
            </el-option>
          </el-select>
        </p>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="setRoleDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveRoleInfo">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    var checkMobile = (rule, value, cb) => {
      const regMobile = /^1[34578]\d{9}$/
      if (regMobile.test(value)) {
        return cb()
      }
      //返回一个错误提示
      cb(new Error('请输入合法的手机号码'))
    }
    //验证邮箱的规则
    var checkEmail = (rule, value, cb) => {
      const regEmail = /^\w+@\w+(\.\w+)+$/
      if (regEmail.test(value)) {
        return cb()
      }
      //返回一个错误提示
      cb(new Error('请输入合法的邮箱'))
    }
    return {
      userInfo:{},
      rolesList:[
        {"id":1,"roleName":"主管"},
        {"id":2,"roleName":"经理"},
        {"id":3,"roleName":"董事长"},
        {"id":4,"roleName":"普通职工"},
        {"id":5,"roleName":"超级管理员"},
        {"id":6,"roleName":"VIP消费者"},
        ],
      setRoleDialogVisible:false,
      editDialogVisible: false,
      i: 7,
      dialogVisible: false,
      query: '',
      pagesize: 1,
      pagenum: 3,
      selectedRoleId:'',
      userList: [
        {
          id: 1,
          username: 'user1',
          mobile: '18616358651',
          type: 1,
          email: 'tige112@163.com',
          create_time: '2017-11-09T20:36:26.000Z',
          mg_state: true, // 当前用户的状态
          role_name: '超级管理员'
        },
        {
          id: 2,
          username: 'user2',
          mobile: '18616358651',
          type: 1,
          email: 'tige112@163.com',
          create_time: '2017-11-09T20:36:26.000Z',
          mg_state: true, // 当前用户的状态
          role_name: '超级管理员'
        },
        {
          id: 3,
          username: 'user3',
          mobile: '18616358651',
          type: 1,
          email: 'tige112@163.com',
          create_time: '2017-11-09T20:36:26.000Z',
          mg_state: true, // 当前用户的状态
          role_name: '超级管理员'
        },
        {
          id: 4,
          username: 'user4',
          mobile: '18616358651',
          type: 1,
          email: 'tige112@163.com',
          create_time: '2017-11-09T20:36:26.000Z',
          mg_state: true, // 当前用户的状态
          role_name: '超级管理员'
        },
        {
          id: 5,
          username: 'user5',
          mobile: '18616358651',
          type: 1,
          email: 'tige112@163.com',
          create_time: '2017-11-09T20:36:26.000Z',
          mg_state: true, // 当前用户的状态
          role_name: '超级管理员'
        },
        {
          id: 6,
          username: 'user6',
          mobile: '18616358651',
          type: 1,
          email: 'tige112@163.com',
          create_time: '2017-11-09T20:36:26.000Z',
          mg_state: true, // 当前用户的状态
          role_name: '超级管理员'
        }
      ],
      userlist1: [
        {
          id: 1,
          username: 'user1',
          mobile: '18616358651',
          type: 1,
          email: 'tige112@163.com',
          create_time: '2017-11-09T20:36:26.000Z',
          mg_state: true, // 当前用户的状态
          role_name: '超级管理员'
        }
      ],
      total: 6,
      ruleForm: {
        username: '',
        password: '',
        email: '',
        mobile: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 0, max: 6, message: '长度在 0 到 6 个字符', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { validator: checkEmail, trigger: 'blur' }
        ],
        mobile: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { validator: checkMobile, trigger: 'blur' }
        ]
      },
      editForm: {
        username: '',
        email: '',
        mobile: ''
      },
      editFormRules: {
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          {
            validator: checkEmail,
            message: '邮箱格式不正确，请重新输入',
            trigger: 'blur'
          }
        ],
        mobile: [
          { required: true, message: '请输入手机号码', trigger: 'blur' },
          {
            validator: checkMobile,
            message: '手机号码不正确，请重新输入',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  methods: {
    handleSizeChange(newSize) {
      this.queryInfo.pagesize = newSize
    },
    handleCurrentChange(current) {
      this.queryInfo.pagenum = current
    },
    userStateChanged(row) {
      this.$message.success('更新状态成功')
    },
    getUserList(id) {
      if (id === 'user1') {
        this.userList = this.userlist1
      }
    },
    addDialogClosed() {
      this.$refs.ruleForm.resetFields()
    },
    addUser() {
      this.$refs.ruleForm.validate(valid => {
        if (!valid) return this.$message.error('请填写完整用户信息')
        this.userList[this.i] = this.ruleForm
        this.i = this.i + 1
        this.$message.success('添加用户成功')
        this.total = this.total + 1
        this.dialogVisible = false
      })
    },

    showEditDialog(id) {
      this.editDialogVisible = true
      for (var j = 0; j < this.userList.length; j++) {
        if (id == this.userList[j].id) {
          this.editForm = this.userList[j]
        }
      }
    },
    printsuccess() {
      this.$refs.editFormRef.validate(valid => {
        if (!valid) return this.$message.error('请填写完整用户信息')
        this.editDialogVisible = false
        this.$message.success('修改成功')
      })
    },
    editDialogClosed() {
      this.$refs.editFormRef.resetFields()
    },
     async removeUserById(id) {
      //弹出确定取消框，是否删除用户
    const confirmResult = await this.$confirm('请问是否要永久删除该用户','删除提示',{
    confirmButtonText:'确认删除',
    cancelButtonText:'取消',
    type:'warning'
    }).catch(err=>err)
    //如果用户点击确认，则confirmResult 为'confirm'
    //如果用户点击取消, 则confirmResult获取的就是catch的错误消息'cancel'
    if(confirmResult != "confirm"){
        return this.$message.info("已经取消删除")
    }
      this.$message.success('删除成功')
      
    },
    setRole(userInfo){
         this.userInfo= userInfo
         
         this.setRoleDialogVisible=true
    },
    saveRoleInfo(){
      if(!this.selectedRoleId){
        return this.$message.error('请选择分配的角色')
      }
      this.$message.success('修改角色成功')
      this.selectedRoleId=''
      this.userInfo={}
      this.setRoleDialogVisible=false
    }
  }

  
}
</script>
<style lang="less" scoped>
</style>