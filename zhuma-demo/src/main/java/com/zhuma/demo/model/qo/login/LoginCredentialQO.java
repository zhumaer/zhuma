package com.zhuma.demo.model.qo.login;

import com.zm.zhuma.commons.annotations.EnumValue;
import com.zm.zhuma.user.model.po.LoginCredential;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @desc 登录凭证

 * @author zhumaer
 * @since 6/15/2017 2:48 PM
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginCredentialQO {

	/**
	 * 账号
	 */
	@NotBlank
	@Length(min=1, max=128)
	private String account;

	/**
	 * 密码
	 */
	private String pwd;

	/**
	 * 用户ID
	 */
	@NotBlank
	private String userId;

	/**
	 * 账号类型
	 */
	@EnumValue(enumClass=LoginCredential.TypeEnum.class, enumMethod="isValidName")
	private String type;

}
