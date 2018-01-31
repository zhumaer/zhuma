package com.zhuma.demo.web.demo2;

import com.zhuma.demo.annotation.ResponseResult;
import com.zhuma.demo.comm.model.qo.PageQO;
import com.zhuma.demo.comm.model.vo.PageVO;
import com.zhuma.demo.model.po.User;
import com.zhuma.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @desc 用户管理控制器
 * 
 * @author zhumaer
 * @since 1/31/2018 23:57 PM
 */
@ResponseResult
@RestController
@RequestMapping("demo2/users")
public class Demo2UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@Validated @RequestBody User user) {
        String userId = userService.insert(user);
        if (userId != null) {
            return userService.selectByPk(userId);
        }
        return null;
    }

    @GetMapping
    public PageVO<User> getList(PageQO pageQO, User userQO) {
        pageQO.setCondition(userQO);
        return userService.selectPage(pageQO);
    }

}
