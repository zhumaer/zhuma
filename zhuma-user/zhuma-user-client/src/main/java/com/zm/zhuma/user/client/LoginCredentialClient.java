package com.zm.zhuma.user.client;

import com.zm.zhuma.commons.constants.ServerConstants;
import com.zm.zhuma.commons.service.RestfulCrudService;
import com.zm.zhuma.user.model.po.LoginCredential;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = ServerConstants.USER, path = "login-credentials")
public interface LoginCredentialClient extends RestfulCrudService<LoginCredential, Long> {

    @GetMapping("_by-account-type")
    LoginCredential getLoginCredential(@RequestParam("account") String account, @RequestParam("type") String type);

    @GetMapping("_by-account-types")
    List<LoginCredential> getLoginCredentialList(@RequestParam("account") String account,@RequestParam("type") List<String> type);

}
