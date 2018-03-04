package com.zhuma.demo.model.qo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * @desc 登录QO
 *
 * @author zhumaer
 * @since 3/3/2018 22:39 PM
 */
@ApiModel("用户登陆")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginQO {

    @NotBlank
    @ApiModelProperty(value = "账号")
    private String loginAccount;

    @NotBlank
    @ApiModelProperty(value = "密码")
    private String pwd;

    @NotBlank
    @ApiModelProperty(value = "凭证类型")
    private List<String> type;
}
