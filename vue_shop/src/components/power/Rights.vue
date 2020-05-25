<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>权限列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <el-table :data="rightsList" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="权限名称" prop="authName"></el-table-column>
        <el-table-column label="路径" prop="path"></el-table-column>
        <el-table-column label="权限等级" prop="level">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.level === '0'">一级</el-tag>
            <el-tag type="success" v-else-if="scope.row.level === '1'">二级</el-tag>
            <el-tag type="warning" v-else>三级</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      rightsList: [
        {
          id: 101,
          authName: '商品管理',
          level: '0',
          pid: 0,
          path: 'roles'
        },
        {
          id: 102,
          authName: '订单管理',
          level: '0',
          pid: 0,
          path: 'rigths'
        },
        {
          id: 103,
          authName: '分类参数',
          level: '0',
          pid: 0,
          path: 'parents'
        },
        {
          id: 104,
          authName: '订单管理',
          level: '2',
          pid: 0,
          path: 'rigths'
        },
        {
          id: 105,
          authName: '订单管理',
          level: '1',
          pid: 0,
          path: 'rigths'
        },
        {
          id: 106,
          authName: '订单管理',
          level: '2',
          pid: 0,
          path: 'rigths'
        },
        {
          id: 107,
          authName: '订单管理',
          level: '2',
          pid: 0,
          path: 'rigths'
        },
        {
          id: 108,
          authName: '订单管理',
          level: '3',
          pid: 0,
          path: 'rigths'
        },
        {
          id: 109,
          authName: '订单管理',
          level: '1',
          pid: 0,
          path: 'rigths'
        },
        {
          id: 1010,
          authName: '订单管理',
          level: '2',
          pid: 0,
          path: 'rigths'
        },
        {
          id: 1011,
          authName: '订单管理',
          level: '3',
          pid: 0,
          path: 'rigths'
        },
        {
          id: 1012,
          authName: '订单管理',
          level: '2',
          pid: 0,
          path: 'rigths'
        }
      ]
    }
  }
}
</script>
<style lang="less" scoped>
</style>