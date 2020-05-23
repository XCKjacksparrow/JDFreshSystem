# JDFreshSystem

## 项目介绍

### 目的与要求:

(1) 有顾客(会员), 京东管理员, 派送员等

(2) 顾客管理, 生鲜品种管理, 生鲜管理, 顾客订购管理, 配送管理, 统计分析(如营业额)等;


###  数据库设计:
顾客: id, 用户名, 密码, 性别, 年龄, 地址, 是否是派送员;
    
    注册, 登录, 下单, 收货, 成为配送员, 接单[派送员]

管理员: id, 用户名, 密码, 性别, 年龄;
    
    登录, 顾客管理, 生鲜管理, 订单管理, 配送管理, 统计分析

订单: id, 订单内容, 下单人, 派送员, 下单时间, 收货时间, 状态(未发货|已发货|在配送|已收货);

生鲜种类: id, 名称

生鲜: id, 生鲜种类, 名称, 数量

## 技术栈
前端: vue vue-router vuex axios

后端: Springboot Jpa Swagger2

## 安装与使用

## 主要项目负责人
前端: 王紫剑

后端: 夏崇康