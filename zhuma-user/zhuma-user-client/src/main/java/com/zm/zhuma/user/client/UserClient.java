package com.zm.zhuma.user.client;

import com.zm.zhuma.commons.constants.ServerConstants;
import com.zm.zhuma.commons.service.RestfulCrudService;
import com.zm.zhuma.user.model.po.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(value = ServerConstants.USER, path = "users")
public interface UserClient extends RestfulCrudService<User, String>{

}
