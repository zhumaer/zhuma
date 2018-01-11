package com.zm.zhuma.app.server.model.bo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @desc 登录的用户
 *
 * @author zhumaer
 * @since 6/20/2017 3:00 PM
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements Serializable{

	private static final long serialVersionUID = -3675314128118074922L;

	@ApiModelProperty(value = "员工ID")
	private String id;

	@ApiModelProperty(value = "真实姓名")
	private String realName;

	@ApiModelProperty(value = "登陆账号")
	private String loginAccount;

	@ApiModelProperty(value = "头像")
	private String avatar;

	@ApiModelProperty(value = "描述")
	private String description;

	@ApiModelProperty(value = "手机号码")
	private String phone;

	@ApiModelProperty(value = "最新登陆IP")
	private String latestLoginIp;

	@ApiModelProperty(value = "最新登陆时间")
	private Date latestLoginTime;

}
