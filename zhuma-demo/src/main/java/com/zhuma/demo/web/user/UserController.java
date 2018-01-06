package com.zhuma.demo.web.user;

import java.util.Date;

import com.zhuma.demo.annotation.ResponseResult;
import com.zhuma.demo.comm.result.DefaultErrorResult;
import com.zhuma.demo.comm.result.PlatformResult;
import com.zhuma.demo.comm.result.Result;
import com.zhuma.demo.exception.BusinessException;
import com.zhuma.demo.exception.DataNotFoundException;
import com.zhuma.demo.exception.UserNotLoginException;
import com.zhuma.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.zhuma.demo.model.po.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @desc 用户管理控制器
 * 
 * @author zhumaer
 * @since 6/20/2017 16:37 PM
 */
@ResponseResult
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User getUser(@PathVariable("id") String id) {
       User user = userService.getById(id);
       if (user == null) {
           throw new DataNotFoundException();
       }

        return user;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@Validated @RequestBody User user) {
        user.setId(10000L);
        user.setCreateTime(new Date());
        return user;
    }

}
