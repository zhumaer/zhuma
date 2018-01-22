package com.zm.zhuma.user.client;

import com.zm.zhuma.commons.constants.ServerConstants;
import com.zm.zhuma.commons.service.RestfulCrudService;
import com.zm.zhuma.user.model.po.LoginCredential;
import com.zm.zhuma.user.model.po.User;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = ServerConstants.USER, path = "login-credentials")
public interface LoginCredentialClient extends RestfulCrudService<LoginCredential, Long>{
}
