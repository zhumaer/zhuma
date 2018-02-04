package com.zhuma.demo.web.demo1;

import java.util.Date;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhuma.demo.mapper.UserMapper;
import com.zm.zhuma.commons.annotations.ResponseResult;
import com.zm.zhuma.commons.model.qo.PageQO;
import com.zm.zhuma.commons.model.vo.PageVO;
import com.zm.zhuma.user.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @desc 用户管理控制器
 * 
 * @author zhumaer
 * @since 6/20/2017 16:37 PM
 */
@ResponseResult
@RestController
@RequestMapping("demo1/users")
public class Demo1UserController {

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
