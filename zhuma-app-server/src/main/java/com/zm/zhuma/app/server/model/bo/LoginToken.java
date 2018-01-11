package com.zm.zhuma.app.server.model.bo;

import java.util.Date;

import com.zm.zhuma.commons.enums.CallSourceEnum;
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
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginToken {

	/**
	 * 登陆token ID
	 */
	private String id;

	/**
	 * 生存时长
	 */
	private Long ttl;

	/**
	 * IP
	 */
	private String ip;

	/**
	 * 平台 {@link CallSourceEnum}
	 */
	private String platform;

	/**
	 * 时间
	 */
	private Date loginTime;

	/**
	 * 登录的用户信息
	 */
	private LoginUser loginUser;

	/**
	 * socket连接的sessionId
	 */
	private String socketSessionId;

}
