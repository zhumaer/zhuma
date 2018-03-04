package com.zm.zhuma.user.service.web;

import com.zm.zhuma.commons.service.impl.RestfulCrudServiceImpl;
import com.zm.zhuma.user.client.LoginCredentialClient;
import com.zm.zhuma.user.model.po.LoginCredential;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 登录凭证服务实现
 * @author zhumaer
 * @since 2018-1-20 10:58:51
 */
@Slf4j
@RestController("loginCredentialClientService")
@RequestMapping("/login-credentials")
public class LoginCredentialClientService extends RestfulCrudServiceImpl<LoginCredential, Long> implements LoginCredentialClient {

    @Override
    public LoginCredential getLoginCredential(String account, String type) {
        Assert.notNull(account, "account is not null");
        Assert.notNull(type, "type is not null");

        LoginCredential loginCredentialQO = new LoginCredential();
        loginCredentialQO.setAccount(account);
        loginCredentialQO.setType(type);

        return crudMapper.selectOne(loginCredentialQO);
    }

    @Override
    public List<LoginCredential> getLoginCredentialList(String account, List<String> type) {
        Assert.notNull(account, "account is not null");
        Assert.notEmpty(type, "type is not empty");

        Example example = new Example(LoginCredential.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("account", account);
        criteria.andIn("type", type);

        return crudMapper.selectByExample(example);
    }

}
