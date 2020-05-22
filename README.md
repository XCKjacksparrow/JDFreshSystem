# JDFreshSystem

## 项目介绍

### 目的与要求:

(1) 有顾客(会员), 京东管理员, 派送员等

(2) 顾客管理, 生鲜品种管理, 生鲜管理, 顾客订购管理, 配送管理, 统计分析(如营业额)等;


###  数据库设计:
顾客: id, 用户名, 密码, 性别, 年龄, 地址, 是否是派送员;
    注册, 登录, 下单, 收货, 接单[派送员]

管理员: id, 用户名, 密码, 性别, 年龄;

订单: id, 订单内容, 下单人, 派送员, 下单时间, 收货时间, 状态(已接单|已收货);

生鲜: 名称, 数量

## 安装与使用

## 主要项目负责人