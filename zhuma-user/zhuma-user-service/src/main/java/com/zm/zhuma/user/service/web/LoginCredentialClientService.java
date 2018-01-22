package com.zm.zhuma.user.service.web;

import com.zm.zhuma.commons.service.impl.RestfulCrudServiceImpl;
import com.zm.zhuma.user.client.LoginCredentialClient;
import com.zm.zhuma.user.client.UserClient;
import com.zm.zhuma.user.model.po.LoginCredential;
import com.zm.zhuma.user.model.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录凭证服务实现
 * @author zhumaer
 * @since 2018-1-20 10:58:51
 */
@Slf4j
@RestController("loginCredentialClientService")
@RequestMapping("/login-credentials")
public class LoginCredentialClientService extends RestfulCrudServiceImpl<LoginCredential, Long> implements LoginCredentialClient {
}
