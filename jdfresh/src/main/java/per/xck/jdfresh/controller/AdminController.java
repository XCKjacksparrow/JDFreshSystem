package per.xck.jdfresh.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.xck.jdfresh.entity.*;
import per.xck.jdfresh.repository.*;
import per.xck.jdfresh.util.LoginType;

import java.util.List;

@RestController
@CrossOrigin
public class AdminController {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FreshCategoryRepository freshCategoryRepository;
    @Autowired
    FreshRepository freshRepository;
    @Autowired
    OrdersRepository ordersRepository;

    @ApiOperation("管理员登录")
    @PostMapping("/api/admin/login")
    public LoginType login(@ApiParam("管理员用户名") @RequestParam("username") String username,
                        @ApiParam("管理员密码") @RequestParam("password") String password
                        ){
        List<Admin> allAdmins = adminRepository.findAll();
        LoginType loginType = new LoginType();
        for (Admin admin : allAdmins){
            if (admin.getUsername().equals(username)){      // 用户存在
                if (!admin.getPassword().equals(password)){  // 密码错误
                    loginType.setStatus("fail");
                    loginType.setErrorMsg("密码错误");
                    return loginType;
                }
                loginType.setStatus("success");             // 登录成功
                loginType.setErrorMsg("");
                return loginType;
            }
        }
        loginType.setStatus("fail");
        loginType.setErrorMsg("用户名不存在");
        return loginType;
    }

    @ApiOperation("获取所有顾客")
    @GetMapping("/api/admin-user/getAllUsers")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @ApiOperation("添加用户")
    @PostMapping("/api/admin-user/addUser")
    public String addUser(@ApiParam("顾客用户名")@RequestParam("username") String username,
                          @ApiParam("顾客密码")@RequestParam("password") String password,
                          @ApiParam("顾客地址")@RequestParam("address") String address,
                          @ApiParam("是否配送员")@RequestParam("isdeliverier") String isdeliverier
                          ){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAddress(address);
        user.setIsdeliverier(Integer.parseInt(isdeliverier));
        userRepository.save(user);
        return "添加成功!";
    }

    @ApiOperation("删除用户")
    @PostMapping("/api/admin-user/deleteUserById")
    public String deleteUserById(@ApiParam("id")@RequestParam("id") String id){
        userRepository.deleteById(Integer.parseInt(id));
        return "删除成功";
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/api/admin-user/modifyUser")
    public String modifyUser(@ApiParam("id")@RequestParam("id") String id,
                             @ApiParam("顾客用户名")@RequestParam("username") String username,
                             @ApiParam("顾客密码")@RequestParam("password") String password,
                             @ApiParam("顾客地址")@RequestParam("address") String address,
                             @ApiParam("是否配送员")@RequestParam("isdeliverier") String isdeliverier){
        User user = userRepository.getOne(Integer.parseInt(id));
        user.setUsername(username);
        user.setPassword(password);
        user.setAddress(address);
        user.setIsdeliverier(Integer.parseInt(isdeliverier));
        userRepository.save(user);
        return "修改成功";
    }

    @ApiOperation("获取生鲜种类")
    @GetMapping("/api/admin-fresh/getAllFreshCategories")
    public List<FreshCategory> getAllFreshCategories(){
        return freshCategoryRepository.findAll();
    }

    @ApiOperation("添加生鲜种类")
    @PostMapping("/api/admin-fresh/addFreshCategories")
    public String addFreshCategories(@ApiParam("生鲜种类名称") @RequestParam("name")String name){
        FreshCategory freshCategory = new FreshCategory();
        freshCategory.setName(name);
        freshCategoryRepository.save(freshCategory);
        return "添加成功";
    }

    @ApiOperation("删除生鲜种类")
    @PostMapping("/api/admin-fresh/deleteFreshCategoriesById")
    public String deleteFreshCategoriesById(@ApiParam("id") @RequestParam("id")String id){
        freshCategoryRepository.deleteById(Integer.parseInt(id));
        return "删除成功";
    }

    @ApiOperation("修改生鲜种类信息")
    @PostMapping("/api/admin-fresh/ModifyFreshCategories")
    public String ModifyFreshCategories(@ApiParam("id") @RequestParam("id")String id,
                                        @ApiParam("生鲜种类名称") @RequestParam("name")String name){
        FreshCategory one = freshCategoryRepository.getOne(Integer.parseInt(id));
        one.setName(name);
        freshCategoryRepository.save(one);
        return "删除成功";
    }

    @ApiOperation("根据生鲜种类获取所有生鲜")
    @PostMapping("/api/admin-fresh/getAllFreshByFreshCategory")
    public List<Fresh>  getAllFreshByFreshCategory(@ApiParam("生鲜种类")@RequestParam("freshCategory") String freshCategory){
        return freshRepository.getAllFreshByFreshCategory(freshCategory);
    }

    @ApiOperation("添加生鲜")
    @PostMapping("/api/admin-fresh/addFresh")
    public String addFresh(@ApiParam("生鲜名称") @RequestParam("name")String name,
                           @ApiParam("生鲜种类名称") @RequestParam("freshCategory")String freshCategory,
                           @ApiParam("数量") @RequestParam("amount")String amount,
                           @ApiParam("单价") @RequestParam("price")String price
                           ){
        Fresh fresh = new Fresh();
        fresh.setName(name);
        fresh.setFreshCategory(freshCategory);
        fresh.setAmount(Integer.parseInt(amount));
        fresh.setPrice(Double.parseDouble(price));
        freshRepository.save(fresh);
        return "添加成功";
    }

    @ApiOperation("删除生鲜")
    @PostMapping("/api/admin-fresh/deleteFreshById")
    public String deleteFreshById(@ApiParam("id") @RequestParam("id")String id){
        freshRepository.deleteById(Integer.parseInt(id));
        return "删除成功";
    }

    @ApiOperation("修改生鲜信息")
    @PostMapping("/api/admin-fresh/modifyFresh")
    public String modifyFresh(@ApiParam("id") @RequestParam("id")String id,
                           @ApiParam("生鲜名称") @RequestParam("name")String name,
                           @ApiParam("生鲜种类名称") @RequestParam("freshCategory")String freshCategory,
                           @ApiParam("数量") @RequestParam("amount")String amount,
                           @ApiParam("单价") @RequestParam("price")String price
    ){
        Fresh fresh = freshRepository.getOne(Integer.parseInt(id));
        fresh.setName(name);
        fresh.setFreshCategory(freshCategory);
        fresh.setAmount(Integer.parseInt(amount));
        fresh.setPrice(Double.parseDouble(price));
        freshRepository.save(fresh);
        return "修改成功";
    }

    @ApiOperation("获取所有订单信息")
    @GetMapping("/api/admin-order/getAllOrders")
    public List<Orders> getAllOrders(){
        return ordersRepository.findAll();
    }

    @ApiOperation("删除订单")
    @PostMapping("/api/admin-order/deleteOrdersById")
    public String deleteOrdersById(@ApiParam("id") @RequestParam("id") String id){
        ordersRepository.deleteById(Integer.parseInt(id));
        return "删除成功";
    }

    @ApiOperation("修改订单")
    @PostMapping("/api/admin-order/modifyOrders")
    public String modifyOrders(@ApiParam("id") @RequestParam("id") String id,
                               @ApiParam("订单内容") @RequestParam("content") String content,
                               @ApiParam("下单人") @RequestParam("username") String username,
                               @ApiParam("派送员") @RequestParam("deliverier") String deliverier,
                               @ApiParam("送货地址") @RequestParam("address") String address,
                               @ApiParam("订单价格") @RequestParam("price") String price,
                               @ApiParam("是否付款") @RequestParam("isPaid") Integer isPaid,
                               @ApiParam("下单时间") @RequestParam("orderTime") String orderTime,
                               @ApiParam("状态") @RequestParam("status") String status
                               ){
        Orders orders = ordersRepository.getOne(Integer.parseInt(id));
        orders.setContent(content);
        orders.setAddress(address);
        orders.setStatus(status);
        orders.setDeliverier(deliverier);
        orders.setUsername(username);
        orders.setPrice(Double.parseDouble(price));
        orders.setIsPaid(isPaid);
        ordersRepository.save(orders);
        return "修改成功";
    }

    @ApiOperation("修改订单状态与地址")
    @PostMapping("/api/admin-order/modifyOrdersStatusAndAddress")
    public String modifyOrdersStatusAndAddress(@ApiParam("id") @RequestParam("id") String id,
                               @ApiParam("送货地址") @RequestParam("address") String address,
                               @ApiParam("状态") @RequestParam("status") String status
    ){
        Orders orders = ordersRepository.getOne(Integer.parseInt(id));
        orders.setAddress(address);
        orders.setStatus(status);
        ordersRepository.save(orders);
        return "修改成功";
    }
}
