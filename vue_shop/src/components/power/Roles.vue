<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>角色列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <el-row>
        <el-col>
          <el-button type="primary">添加角色</el-button>
        </el-col>
      </el-row>

      <el-table :data="rolelist" border stripe>
        <el-table-column type="expand">
          <template slot-scope="scope">
            <el-row
              v-for="(item,li) in scope.row.children"
              :key="item.id"
              :class="['bdbottom',li===0 ? 'dbtop':'','vcenter']"
            >
              <el-col :span="5">
                <el-tag>{{item.authName}}</el-tag>
                <i class="el-icon-caret-right"></i>
              </el-col>
              <el-col :span="19">
                <el-row
                  v-for="(item1,li1) in item.children"
                  :key="item1.id"
                  :class="[li1 === 0 ? '' : 'bdtop','vcenter']"
                >
                  <el-col :span="6">
                    <el-tag type="success">{{item1.authName}}</el-tag>
                    <i class="el-icon-caret-right"></i>
                  </el-col>
                  <el-col :span="18">
                    <el-tag
                      v-for="item2 in item1.children"
                      :key="item2.id"
                      type="warning"
                      closable
                      @close="removeRightById"
                    >{{item2.authName}}</el-tag>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
          </template>
        </el-table-column>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="角色名称" prop="roleName"></el-table-column>
        <el-table-column label="角色描述" prop="roleDesc"></el-table-column>
        <el-table-column label="操作" width="300px">
          <template slot-scope="scope">
            <el-button size="mini" type="primary" icon="el-icon-edit">编辑</el-button>
            <el-button size="mini" type="danger" icon="el-icon-delete">删除</el-button>
            <el-button
              size="mini"
              type="warning"
              icon="el-icon-setting"
              @click="showSetRightDialog(scope.row)"
            >分配权限</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog title="分配权限" :visible.sync="setRightDialogVisible" width="30%" @close="setRightDialogClosed">
      <el-tree
        :data="rightslist"
        :props="defaultProps"
        show-checkbox
        node-key="id"
        default-expand-all
        :default-checked-keys="defKeys"
        ref="treeRef"
      ></el-tree>
      <span slot="footer" class="dialog-footer">
        <el-button @click="setRightDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="allotRights">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      rolelist: [
        {
          id: 31,
          roleName: '经理',
          authName: '商品管理',
          roleDesc: '项目负责人',
          children: [
            {
              id: 101,
              authName: '商品管理',
              path: null,
              children: [
                {
                  id: 104,
                  authName: '商品列表',
                  path: null,
                  children: [
                    {
                      id: 105,
                      authName: '添加商品',
                      path: null
                    }
                  ]
                }
              ]
            }
          ]
        },
        {
          id: 31,
          roleName: '经理',
          authName: '商品管理',
          roleDesc: '项目负责人',
          children: [
            {
              id: 101,
              authName: '商品管理',
              path: null,
              children: [
                {
                  id: 104,
                  authName: '商品列表',
                  path: null,
                  children: [
                    {
                      id: 105,
                      authName: '添加商品',
                      path: null
                    }
                  ]
                }
              ]
            }
          ]
        },
        {
          id: 32,
          roleName: '主管',
          roleDesc: '技术负责人',
          authName: '商品管理',
          children: [
            {
              id: 101,
              authName: '商品管理',
              path: null,
              children: [
                {
                  id: 104,
                  authName: '商品列表',
                  path: null,
                  children: [
                    {
                      id: 105,
                      authName: '添加商品',
                      path: null
                    }
                  ]
                }
              ]
            }
          ]
        },
        {
          id: 33,
          roleName: '主管',
          roleDesc: '技术负责人',
          authName: '商品管理',
          children: [
            {
              id: 101,
              authName: '商品管理',
              path: null,
              children: [
                {
                  id: 104,
                  authName: '商品列表',
                  path: null,
                  children: [
                    {
                      id: 105,
                      authName: '添加商品',
                      path: null
                    }
                  ]
                }
              ]
            }
          ]
        },
        {
          id: 34,
          roleName: '主管',
          roleDesc: '技术负责人',
          authName: '商品管理',
          children: [
            {
              id: 101,
              authName: '商品管理',
              path: null,
              children: [
                {
                  id: 104,
                  authName: '商品列表',
                  path: null,
                  children: [
                    {
                      id: 105,
                      authName: '添加商品',
                      path: null
                    }
                  ]
                }
              ]
            }
          ]
        },
        {
          id: 35,
          roleName: '主管',
          roleDesc: '技术负责人',
          authName: '商品管理',
          children: [
            {
              id: 101,
              authName: '商品管理',
              path: null,
              children: [
                {
                  id: 104,
                  authName: '商品列表',
                  path: null,
                  children: [
                    {
                      id: 105,
                      authName: '添加商品',
                      path: null
                    }
                  ]
                }
              ]
            }
          ]
        },
        {
          id: 36,
          roleName: '主管',
          roleDesc: '技术负责人',
          authName: '商品管理',
          children: [
            {
              id: 101,
              authName: '商品管理',
              path: null,
              children: [
                {
                  id: 104,
                  authName: '商品列表',
                  path: null,
                  children: [
                    {
                      id: 105,
                      authName: '添加商品',
                      path: null
                    }
                  ]
                }
              ]
            }
          ]
        }
      ],
      setRightDialogVisible: false,
      rightslist: [],
      defaultProps: {
        label: 'authName',
        children: 'children'
      },
      defKeys: []
    }
  },
  methods: {
    async removeRightById() {
      const confirmResult = await this.$confirm(
        '此操作将永久删除该文件, 是否继续?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch(err => err)

      if (confirmResult !== 'confirm') {
        return this.$message.info('取消了删除')
      }
      this.$message.success('删除成功')
    },
    showSetRightDialog(role) {
      this.rightslist = this.rolelist
      console.log(this.rightslist)
      this.getLeafKeys(role, this.defKeys)

      this.setRightDialogVisible = true
    },
    getLeafKeys(node, arr) {
      if (!node.children) {
        return arr.push(node.id)
      }
      node.children.forEach(element => {
        this.getLeafKeys(element, arr)
      })
    },
    setRightDialogClosed(){
        this.defKeys= []
    },
    allotRights(){
        const keys = [
            ...this.$refs.treeRef.getCheckedKeys(),
            ...this.$refs.treeRef.getHalfCheckedKeys()
        ]
        this.defKeys=keys
        this.$message.success('修改成功')
        this.setRightDialogVisible=false
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