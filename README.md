# JDFreshSystem

## 项目介绍

### 目的与要求:

(1) 有顾客(会员), 京东管理员, 派送员等

(2) 顾客管理, 生鲜品种管理, 生鲜管理, 顾客订购管理, 配送管理, 统计分析(如营业额)等;


###  数据库设计:
顾客: <u>id</u>, 用户名, 密码, ~~性别, 年龄,~~ 地址, 是否是派送员;
    
顾客方法: 注册, 登录, 下单, 查看订单, 收货, 成为配送员, 接单[派送员]

管理员: <u>id</u>, 用户名, 密码, ~~性别, 年龄~~;
    
管理员方法: 登录, 顾客管理, 生鲜管理, 订单管理, ~~配送管理~~, 统计分析

订单: <u>id</u>, 订单内容, <u>下单人</u>, <u>派送员</u>, 下单时间, 收货时间, 状态(未发货|已发货|在配送|已收货);

生鲜种类: <u>id</u>, 名称

生鲜: <u>id</u>, <u>生鲜种类</u>, 名称, 数量

## 技术栈
前端: vue vue-router vuex axios element-ui

后端: Springboot Jpa Swagger2

## 安装与使用

## 运行效果图

## 主要项目负责人
前端: 王紫剑

后端: 夏崇康
