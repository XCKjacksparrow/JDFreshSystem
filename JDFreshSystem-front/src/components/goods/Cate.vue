<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>商品分类</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图区域 -->
    <el-card>
      <!-- 添加分类按钮区域 -->
      <el-row>
        <el-col>
          <el-button type="primary" @click="showAddCateDialog">添加分类</el-button>
        </el-col>
      </el-row>
      <!-- 分类表格  -->
      <el-table :data="cateList" border stripe>
        <el-table-column type="expand">
          <template slot-scope="scope">
            <el-row
              v-for="(item,li) in scope.row.children"
              :key="item.cat-id"
              :class="['bdbottom',li===0 ? 'dbtop':'','vcenter']"
            >
              <el-col :span="5">
                <el-tag>{{item.cat-name}}</el-tag>
                <i class="el-icon-caret-right"></i>
              </el-col>
              <el-col :span="19">
                <el-row
                  v-for="(item1,li1) in item.children"
                  :key="item1.cat-id"
                  :class="[li1 === 0 ? '' : 'bdtop','vcenter']"
                >
                  <el-col :span="6">
                    <el-tag type="success">{{item1.cat-name}}</el-tag>
                    <i class="el-icon-caret-right"></i>
                  </el-col>
                  <el-col :span="18">
                    <el-tag
                      v-for="item2 in item1.children"
                      :key="item2.cat-id"
                      type="warning"
                      closable
                    >{{item2.cat-name}}</el-tag>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
          </template>
        </el-table-column>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="角色名称" prop="cat-name"></el-table-column>
        <el-table-column label="角色描述" prop="cat-pid"></el-table-column>
        <el-table-column label="操作" width="300px">
          <template>
            <el-button size="mini" type="primary" icon="el-icon-edit">编辑</el-button>
            <el-button size="mini" type="danger" icon="el-icon-delete">删除</el-button>
            <el-button size="mini" type="warning" icon="el-icon-setting">分配权限</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
    </el-card>

    <!-- 添加分类的对话框 -->
    <el-dialog
      title="添加分类"
      :visible.sync="addCateDialogVisible"
      width="50%"
      @close="addCateDialogClosed"
    >
      <!-- 添加分类的表单 -->
      <el-form
        :model="addCateForm"
        :rules="addCateFormRules"
        ref="addCateFormRef"
        label-width="100px"
      >
        <el-form-item label="分类名称：" prop="cat_name">
          <el-input v-model="addCateForm.cat_name"></el-input>
        </el-form-item>
        <el-form-item label="父级分类：">
             <el-cascader expand-trigger="hover" :options="parentCateList" :props="cascaderProps" v-model="selectedKeys" @change="parentCateChanged" clearable change-on-select>
          </el-cascader>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addCateDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addCate">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 商品分类数据列表
      cateList: [
        {
          'cat-id': 1,
          'cat-name': '大家电',
          'cat-pid': '电视',
          'cat-level': 0,
          'cat-deleted': false,
          children: [
            {
              'cat-id': 1,
              'cat-name': '电视',
              'cat-pid': '电视',
              'cat-level': 0,
              'cat-deleted': false,
              children: [
                {
                  'cat-id': 1,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 0,
                  'cat-deleted': false
                }
              ]
            }
          ]
        },
        {
          'cat-id': 1,
          'cat-name': '大家电',
          'cat-pid': '电视',
          'cat-level': 0,
          'cat-deleted': false,
          children: [
            {
              'cat-id': 1,
              'cat-name': '大家电',
              'cat-pid': '电视',
              'cat-level': 0,
              'cat-deleted': false,
              children: [
                {
                  'cat-id': 1,
                  'cat-name': '大家电',
                  'cat-pid': '电视',
                  'cat-level': 0,
                  'cat-deleted': false
                }
              ]
            }
          ]
        },
        {
          'cat-id': 1,
          'cat-name': '大家电',
          'cat-pid': '电视',
          'cat-level': 0,
          'cat-deleted': false,
          children: [
            {
              'cat-id': 1,
              'cat-name': '大家电',
              'cat-pid': '电视',
              'cat-level': 0,
              'cat-deleted': false,
              children: [
                {
                  'cat-id': 1,
                  'cat-name': '大家电',
                  'cat-pid': '电视',
                  'cat-level': 0,
                  'cat-deleted': false
                }
              ]
            }
          ]
        },
        {
          'cat-id': 1,
          'cat-name': '大家电',
          'cat-pid': '电视',
          'cat-level': 0,
          'cat-deleted': false,
          children: [
            {
              'cat-id': 1,
              'cat-name': '大家电',
              'cat-pid': '电视',
              'cat-level': 0,
              'cat-deleted': false,
              children: [
                {
                  'cat-id': 1,
                  'cat-name': '大家电',
                  'cat-pid': '电视',
                  'cat-level': 0,
                  'cat-deleted': false
                }
              ]
            }
          ]
        },
        {
          'cat-id': 1,
          'cat-name': '大家电',
          'cat-pid': '电视',
          'cat-level': 0,
          'cat-deleted': false,
          children: [
            {
              'cat-id': 1,
              'cat-name': '大家电',
              'cat-pid': '电视',
              'cat-level': 0,
              'cat-deleted': false,
              children: [
                {
                  'cat-id': 1,
                  'cat-name': '大家电',
                  'cat-pid': '电视',
                  'cat-level': 0,
                  'cat-deleted': false
                }
              ]
            }
          ]
        }
      ],
      addCateDialogVisible: false,
      //查询分类数据的条件
      queryInfo: {
        type: 3,
        pagenum: 1,
        pagesize: 5
      },
      //保存总数据条数
      total: 3,
      addCateForm: {
        // 将要添加的分类的名称
        cat_name: '',
        // 父级分类的Id
        cat_pid: 0,
        // 分类的等级，默认要添加的是1级分类
        cat_level: 0
      },
      addCateFormRules: {
        cat_name: [
          { required: true, message: '请输入分类名称', trigger: 'blur' }
        ]
      },
      parentCateList: [
        {
          'cat-id': 1,
          'cat-name': '大家电',
          'cat-pid': '电视',
          'cat-level': 0,
          'cat-deleted': false,
          children: [
            {
              'cat-id': 1,
              'cat-name': '电视',
              'cat-pid': '电视',
              'cat-level': 0,
              'cat-deleted': false
            }
          ]
        },
         {
          'cat-id': 1,
          'cat-name': '大家电',
          'cat-pid': '电视',
          'cat-level': 0,
          'cat-deleted': false,
          children: [
            {
              'cat-id': 1,
              'cat-name': '电视',
              'cat-pid': '电视',
              'cat-level': 0,
              'cat-deleted': false
            }
          ]
        },
         {
          'cat-id': 1,
          'cat-name': '大家电',
          'cat-pid': '电视',
          'cat-level': 0,
          'cat-deleted': false,
          children: [
            {
              'cat-id': 1,
              'cat-name': '电视',
              'cat-pid': '电视',
              'cat-level': 0,
              'cat-deleted': false
            }
          ]
        },
         {
          'cat-id': 1,
          'cat-name': '大家电',
          'cat-pid': '电视',
          'cat-level': 0,
          'cat-deleted': false,
          children: [
            {
              'cat-id': 1,
              'cat-name': '电视',
              'cat-pid': '电视',
              'cat-level': 0,
              'cat-deleted': false
            }
          ]
        },
         {
          'cat-id': 1,
          'cat-name': '大家电',
          'cat-pid': '电视',
          'cat-level': 0,
          'cat-deleted': false,
          children: [
            {
              'cat-id': 1,
              'cat-name': '电视',
              'cat-pid': '电视',
              'cat-level': 0,
              'cat-deleted': false
            }
          ]
        }
      ],
      cascaderProps: {
        value: 'cat_id',
        label: 'cat-name',
        children: 'children'
      },
      // 选中的父级分类的Id数组
      selectedKeys: []
    }
  },
  methods: {
    showAddCateDialog() {
      this.addCateDialogVisible = true
    },
    parentCateChanged(){
          if (this.selectedKeys.length > 0) {
        // 父级分类的Id
        this.addCateForm.cat_pid = this.selectedKeys[
          this.selectedKeys.length - 1
        ]
        // 为当前分类的等级赋值
        this.addCateForm.cat_level = this.selectedKeys.length
        return
      } else {
        // 父级分类的Id
        this.addCateForm.cat_pid = 0
        // 为当前分类的等级赋值
        this.addCateForm.cat_level = 0
      }
    },
    addCateDialogClosed(){
      this.$refs.addCateFormRef.resetFields()
      this.selectedKeys=[]
      this.addCateForm.cat_pid=0
      this.addCateForm.cat_level=0
    },
    addCate(){
      this.$refs.addCateFormRef.validate(valid=>{
        if(!valid)return
        return this.$message.success('添加成功')
      })
      this.addCateDialogVisible=false
    }
  }
}
</script>

<style lang="less" scoped>
.el-tag {
  margin: 7px;
}
.bdtop {
  border-top: 1px solid #eee;
}
.bdbottom {
  border-bottom: 1px solid #eee;
}
.vcenter {
  display: flex;
  align-items: center;
}
</style>