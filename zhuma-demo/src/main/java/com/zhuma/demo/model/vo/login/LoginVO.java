package com.zhuma.demo.model.vo.login;

import com.zm.zhuma.commons.model.Model;
import com.zm.zhuma.user.model.bo.LoginUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @desc 登录VO
 *
 * @author zhumaer
 * @since 3/3/2018 22:39 PM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO implements Model {

    private static final long serialVersionUID = -9111387775700628962L;

    @ApiModelProperty(value = "用户登陆TOKEN")
    private String token;

    @ApiModelProperty(value = "过期时间（单位：秒）")
    private Long ttl;

    @ApiModelProperty(value = "登陆IP")
    private String ip;

    @ApiModelProperty(value = "登陆平台")
    private String platform;

    @ApiModelProperty(value = "登陆时间")
    private Date loginTime;

    @ApiModelProperty(value = "用户信息")
    private LoginUser user;

    @ApiModelProperty(value = "凭证")
    private LoginCredentialVO loginCredential;
}
