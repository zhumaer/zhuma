package com.zhuma.demo.service.login;

import com.zhuma.demo.model.qo.login.LoginQO;
import com.zhuma.demo.model.vo.login.LoginVO;

/**
 * @desc 登录服务
 *
 * @author zhumaer
 * @since 3/3/2018 22:39 PM
 */
public interface LoginService {

    LoginVO login(LoginQO loginQO);

    void logout();
}
