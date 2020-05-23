<template>
  <el-container class="home-container">
    <el-header>
      <div>
        <img src="../assets/heima.png" />
        <span>京东后台管理系统</span>
      </div>
      <el-button type="info" @click="logout">退出</el-button>
    </el-header>
    <el-container>
      <el-aside :width="isCollapse? '64px' : '200px'">
        <div class="toggle-button" @click="toggleCollapse">|||</div>
<el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      background-color="#333744"
      text-color="#fff"
      active-text-color="#ffd04b"
      router
      >
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>用户管理</span>
        </template>
        
        
          <el-menu-item index="user">
            <template slot="title">
          <i class="el-icon-location"></i>
          <span>用户列表</span>
        </template>
        </el-menu-item>
        
      </el-submenu>
           <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>商品管理</span>
        </template>
        
        
          <el-menu-item index="user7">
            <template slot="title">
          <i class="el-icon-location"></i>
          <span>商品列表</span>
        </template>
        </el-menu-item>
        
      </el-submenu>
           <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>订单管理</span>
        </template>
        
        
          <el-menu-item index="user3">
            <template slot="title">
          <i class="el-icon-location"></i>
          <span>订单列表</span>
        </template>
        </el-menu-item>
        
 
        
      </el-submenu>
           <el-submenu index="4">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>统计管理</span>
        </template>
        
        
          <el-menu-item index="user2">
            <template slot="title">
          <i class="el-icon-location"></i>
          <span>统计列表</span>
        </template>
        </el-menu-item>
        
      </el-submenu>


      
    </el-menu>



        

      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      menulist: [
        { id: 1, authName: '用户管理', path: 'user' },
        { id: 2, authName: '商品管理', path: 'user' },
        { id: 3, authName: '订单管理', path: 'user' },
        { id: 4, authName: '配送管理', path: 'user' },
        { id: 5, authName: '数据统计', path: 'user' }
      ],
      menulist1: [
        { id: 1, authName: '用户列表', path: 'user' },
        { id: 2, authName: '权限管理', path: 'user1' },
        { id: 3, authName: '数据分析', path: 'user2' },
        { id: 4, authName: '订单管理', path: 'user3' },
        { id: 5, authName: '角色列表', path: 'user4' },
        { id: 6, authName: '商品分类', path: 'user5' },
        { id: 7, authName: '分类参数', path: 'user6' },
        { id: 8, authName: '商品列表', path: 'user7' }
      ],
      consObj: {
        '1': 'iconfont icon-user',
        '2': 'iconfont icon-tijikongjian',
        '3': 'iconfont icon-shangpin',
        '4': 'iconfont icon-danju',
        '5': 'iconfont icon-baobiao'
      },
      isCollapse: false,
      activePath: ''
    }
  },
  created() {
    this.activePath = window.sessionStorage.getItem('activePath')
  },
  methods: {
    logout() {
      window.sessionStorage.clear()
      this.$router.push('/login')
    },
    toggleCollapse() {
      this.isCollapse = !this.isCollapse
    },
    saveNavState(activePath) {
      window.sessionStorage.setItem('activePath', activePath)
      this.activePath = activePath
    }
  }
}
</script>

<style lang="less" scoped>
.home-container {
  height: 100%;
}
.el-header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  color: #fff;
  font-size: 20px;
  > div {
    display: flex;
    align-items: center;
    span {
      margin-left: 15px;
    }
  }
}
.el-aside {
  background-color: #333744;
  .el-menu {
    border-right: none;
  }
}

.el-main {
  background-color: #eaedf1;
}

.iconfont {
  margin-right: 10px;
}

.toggle-button {
  background-color: #4a5064;
  font-size: 10px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
}
</style>
