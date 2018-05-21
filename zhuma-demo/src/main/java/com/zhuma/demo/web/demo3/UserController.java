package com.zhuma.demo.web.demo3;

import com.zm.zhuma.commons.web.annotations.ResponseResult;
import com.zm.zhuma.user.client.UserClient;
import com.zm.zhuma.user.model.po.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @desc  用户控制器
 * 
 * @author zhumaer
 * @since 3/5/2018 23:57 PM
 */
@Api(value = "用户管理", description = "用户管理")
@ResponseResult
@RestController("demo3UserController")
@RequestMapping("demo3/users")
public class UserController {

    @Autowired
    private UserClient userClient;

    @ApiOperation("添加用户")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@Validated @RequestBody User user) {
        return userClient.add(user);
    }

    /**
     * 通常系统都是不允许删除用户的（删除也是逻辑删除），当你的userClient使用了RestfulCrudService时，如果不想支持该方法，
     * 就覆盖它，并抛出MethodNotAllowException
     */
    @ApiOperation("删除用户")
    @DeleteMapping("{id}")
    public void addUser(@PathVariable("id") String id) {
        userClient.deleteById(id);
    }
}
