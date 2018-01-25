package com.zm.zhuma.user.service.web;

import com.zm.zhuma.commons.service.impl.RestfulCrudServiceImpl;
import com.zm.zhuma.commons.utils.JsonUtil;
import com.zm.zhuma.user.client.UserClient;
import com.zm.zhuma.user.model.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户服务实现
 * @author zhumaer
 * @since 2018-1-20 10:58:51
 */
@Slf4j
@RestController
@RequestMapping("/tests")
public class TestController {

    @PostMapping
    public void test(@RequestBody User user) {
        log.info(JsonUtil.object2Json(user));
    }
}
