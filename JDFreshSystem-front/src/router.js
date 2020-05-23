import Vue from 'vue'
import Router from 'vue-router'
import Login from './components/Login.vue'
import Home from './components/Home.vue'
import Weclome from './components/Welcome.vue'
import User from './components/User/user.vue'
import Rights from './components/power/Rights.vue'
import Roles from './components/power/Roles.vue'
import Cate from './components/goods/Cate.vue'
import Params from './components/goods/Params.vue'
import List from './components/goods/List.vue'
import GoodAdd from './components/goods/Add.vue'
import Order from './components/order/Order.vue'
import Report from './components/report/Report.vue'
Vue.use(Router)

const router = new Router({
  routes: [
    { path: '/', redirect: '/login' },
    { path: '/login', component: Login },
    {
      path: '/home',
      component: Home,
      redirect: '/welcome',
      children: [
        { path: '/welcome', component: Weclome },
        { path: '/user', component: User },
        { path: '/user1', component: Rights },
        { path: '/user4', component: Roles },
        { path: '/user5', component: Cate },
        { path: '/user6', component: Params },
        { path: '/user7', component: List },
        { path: '/user7/add', component: GoodAdd },
        { path: '/user3', component: Order },
        {
          path: '/user2', component: Report
        }

      ]
    }
  ]
})

// 挂载路由导航守卫

export default router
