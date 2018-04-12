package com.zhuma.demo.web.demo3;

import com.zhuma.demo.helper.PasswordHelper;
import com.zhuma.demo.model.qo.login.LoginCredentialQO;
import com.zm.zhuma.commons.enums.ResultCode;
import com.zm.zhuma.commons.exceptions.BusinessException;
import com.zm.zhuma.commons.web.annotations.ResponseResult;
import com.zm.zhuma.user.client.LoginCredentialClient;
import com.zm.zhuma.user.model.po.LoginCredential;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @desc  登录凭证控制器（用户登录凭证就是用来存储用的登录账号和密码的地方，以后集成微信、新浪等第三方平台的账号都会存在此表中）
 * 
 * @author zhumaer
 * @since 2/4/2018 23:57 PM
 */
@Api(value = "登录凭证管理", description = "登录凭证管理")
@ResponseResult
@RestController("demo3LoginCredentialController")
@RequestMapping("demo3/login-credentials")
public class LoginCredentialController {

    @Autowired
    private LoginCredentialClient loginCredentialClient;

    @ApiOperation("添加登录凭证")
    @PostMapping
    public LoginCredential add(@RequestBody @Validated LoginCredentialQO loginCredentialQO) {

        LoginCredential dbLoginCredential = loginCredentialClient.getLoginCredential(loginCredentialQO.getAccount(), loginCredentialQO.getType());
        if (dbLoginCredential != null) {
            throw new BusinessException(ResultCode.LOGIN_CREDENTIAL_EXISTED);
        }

        String randomSalt = PasswordHelper.generateRandomSalt();
        String encodePwd = PasswordHelper.encodeBySalt(loginCredentialQO.getPwd(), randomSalt);

        LoginCredential loginCredential = com.zm.zhuma.user.model.po.LoginCredential.builder()
                .account(loginCredentialQO.getAccount())
                .pwd(encodePwd)
                .randomSalt(randomSalt)
                .type(loginCredentialQO.getType())
                .userId(loginCredentialQO.getUserId())
                .build();

        return loginCredentialClient.add(loginCredential);
    }

}
