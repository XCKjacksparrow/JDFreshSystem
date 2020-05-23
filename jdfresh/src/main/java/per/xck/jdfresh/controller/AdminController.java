package per.xck.jdfresh.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @ApiOperation("管理员登录")
    @PostMapping("/api/login")
    public String login(@ApiParam("管理员用户名") @RequestParam("username") String username,
                        @ApiParam("管理员密码") @RequestParam("password") String password
                        ){

        return "";
    }
}
