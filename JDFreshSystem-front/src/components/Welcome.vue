<template >
  <div class="block">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">欢迎来到首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/user' }">用户管理</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/user' }">用户列表</el-breadcrumb-item>
    </el-breadcrumb>

    
  </div>
</template>
<script>
export default {
 
}
</script>
<style lang="less" scoped>
.rightullidiv{
  width: 100%;
     height: 500px;
     background: #f2f2f2;
     display: flex;
     justify-content: center;
     align-items: center;
          .rightulliimg {
            max-width: 100%;
            max-height: 500px;
          }

}
</style>