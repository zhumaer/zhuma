package com.zhuma.demo.web.demo3;

import com.zhuma.demo.service.UserService;
import com.zm.zhuma.commons.annotations.LoginAuth;
import com.zm.zhuma.commons.annotations.ResponseResult;
import com.zm.zhuma.commons.exceptions.DataNotFoundException;
import com.zm.zhuma.user.model.bo.LoginUser;
import com.zm.zhuma.user.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @desc  Spring自定参数解析器之《自动注入已登录用户》
 * 
 * @author zhumaer
 * @since 2/4/2018 23:57 PM
 */
@ResponseResult
@RestController
@RequestMapping("demo2/my-account")
public class Demo3UserController {

    @Autowired
    private UserService userService;

    @LoginAuth
    @GetMapping
    public User account(@ApiIgnore LoginUser loginUser) {
        User user = userService.selectByPk(loginUser.getId());
        if (user == null) {
            throw new DataNotFoundException();
        }

        return user;
    }

}
