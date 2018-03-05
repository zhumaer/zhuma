package com.zhuma.demo.model.vo.login;

import com.zm.zhuma.commons.annotations.EnumValue;
import com.zm.zhuma.commons.model.Model;
import com.zm.zhuma.commons.model.po.BasePO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @desc 登录凭证VO

 * @author zhumaer
 * @since 6/15/2017 2:48 PM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginCredentialVO implements Model {

    private static final long serialVersionUID = 5550420394013305835L;

    @ApiModelProperty(value = "凭证ID")
    private Long id;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "账号类型")
    private String type;

}
