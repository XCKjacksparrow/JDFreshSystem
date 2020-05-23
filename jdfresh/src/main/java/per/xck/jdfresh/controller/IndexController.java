package per.xck.jdfresh.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import per.xck.jdfresh.entity.User;


@RestController
public class IndexController {

    @ApiOperation("获得一个用户信息")
    @PostMapping("getOne")
    public User getOne(@ApiParam("用户名")@RequestParam("username") String username){
        User user = new User();
        user.setId(1);
        user.setUsername(username);
        user.setPassword("233");
        return user;
    }
}
