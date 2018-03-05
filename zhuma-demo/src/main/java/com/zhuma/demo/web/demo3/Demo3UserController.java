package com.zhuma.demo.web.demo3;

import com.zhuma.demo.helper.PasswordHelper;
import com.zhuma.demo.model.qo.login.LoginCredentialQO;
import com.zhuma.demo.service.user.UserService;
import com.zm.zhuma.commons.enums.ResultCode;
import com.zm.zhuma.commons.exceptions.BusinessException;
import com.zm.zhuma.commons.web.annotations.ResponseResult;
import com.zm.zhuma.user.client.LoginCredentialClient;
import com.zm.zhuma.user.client.UserClient;
import com.zm.zhuma.user.model.po.LoginCredential;
import com.zm.zhuma.user.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @desc  登录凭证控制器（用户登录凭证就是用来存储用的登录账号和密码的地方，以后集成微信、新浪等第三方平台的账号都会存在此表中）
 * 
 * @author zhumaer
 * @since 2/4/2018 23:57 PM
 */
@ResponseResult
@RestController
@RequestMapping("demo3/users")
public class Demo3UserController {

    @Autowired
    private UserClient userClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@Validated @RequestBody User user) {
        return userClient.add(user);
    }

}
