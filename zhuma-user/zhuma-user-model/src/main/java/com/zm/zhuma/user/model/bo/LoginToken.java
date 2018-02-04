package com.zm.zhuma.user.model.bo;

import java.util.Date;

import com.zm.zhuma.commons.enums.CallSourceEnum;
import com.zm.zhuma.user.model.po.LoginCredential;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @desc 登录的TOKEN
 *
 * @author zhumaer
 * @since 6/20/2017 3:00 PM
 */
@ApiModel("登录的TOKEN")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginToken {

	@ApiModelProperty(value = "登陆token ID", required = true, position = 0)
	private String id;

	@ApiModelProperty(value = "生存时长(单位：秒)", required = true, position = 1)
	private Long ttl;

	@ApiModelProperty(value = "登录IP", required = true, position = 2)
	private String ip;

	/**
	 * 平台 {@link CallSourceEnum}
	 */
	@ApiModelProperty(value = "登录平台", required = true, position = 3)
	private String platform;

	@ApiModelProperty(value = "登录时间", required = true, position = 4)
	private Date createTime;

	@ApiModelProperty(value = "登录凭证", required = true, position =5)
	LoginCredential loginCredential;

	@ApiModelProperty(value = "登录的用户信息", required = true, position = 6)
	private LoginUser loginUser;

}
