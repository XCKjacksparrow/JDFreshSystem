package per.xck.jdfresh.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.xck.jdfresh.entity.Admin;
import per.xck.jdfresh.entity.User;
import per.xck.jdfresh.repository.AdminRepository;
import per.xck.jdfresh.repository.UserRepository;
import per.xck.jdfresh.util.LoginType;

import java.util.List;

@RestController
@CrossOrigin
public class AdminController {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    UserRepository userRepository;

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
    @GetMapping("/api/admin/getAllUsers")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @ApiOperation("添加用户")
    @PostMapping("/api/admin/addUser")
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
    @PostMapping("/api/admin/deleteUserById")
    public String deleteUserById(@ApiParam("id")@RequestParam("id") String id){
        userRepository.deleteById(Integer.parseInt(id));
        return "删除成功";
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/api/admin/modifyUser")
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
}
