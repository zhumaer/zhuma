package com.zhuma.demo.web.demo3;

import com.zhuma.demo.model.qo.login.LoginQO;
import com.zhuma.demo.model.vo.login.LoginVO;
import com.zhuma.demo.service.login.LoginService;
import com.zm.zhuma.commons.annotations.LoginAuth;
import com.zm.zhuma.commons.web.annotations.ResponseResult;
import com.zm.zhuma.commons.web.constants.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhumaer
 * @description : 登陆接口
 * @date : 2017/11/9 14点58分
 */
@Api(value = "登陆相关接口" , description = "登陆/登出")
@Slf4j
@ResponseResult
@RestController
@RequestMapping("demo3")
public class Demo3LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "员工登陆",response = LoginVO.class,  notes = "员工登陆使用登陆账号和密码进行登陆" , httpMethod = "POST")
    @PostMapping("/login")
    public LoginVO login(@RequestBody @Validated LoginQO loginQO){
        LoginVO loginVO = loginService.login(loginQO);
        return loginVO;
    }


    @ApiOperation(value = "退出登陆")
    @LoginAuth
    @PostMapping(value = "/logout")
    public void logout(){
        loginService.logout();
    }

}
