package com.zhuma.demo.web.user;

import java.util.Date;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhuma.demo.annotation.ResponseResult;
import com.zhuma.demo.comm.model.qo.PageQO;
import com.zhuma.demo.comm.model.vo.PageVO;
import com.zhuma.demo.comm.result.DefaultErrorResult;
import com.zhuma.demo.comm.result.PlatformResult;
import com.zhuma.demo.comm.result.Result;
import com.zhuma.demo.exception.BusinessException;
import com.zhuma.demo.exception.DataNotFoundException;
import com.zhuma.demo.exception.UserNotLoginException;
import com.zhuma.demo.mapper.UserMapper;
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
    private UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@Validated @RequestBody User user) {
        Date currentDate = new Date();
        user.setCreateTime(currentDate);
        user.setUpdateTime(currentDate);
        userMapper.insert(user);
        return userMapper.selectByPrimaryKey(user.getId());
    }

    @GetMapping
    public PageVO<User> getList(PageQO pageQO) {
        Page<User> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize(), pageQO.getOrderBy());
        userMapper.selectAll();
        return PageVO.build(page);
    }
}
