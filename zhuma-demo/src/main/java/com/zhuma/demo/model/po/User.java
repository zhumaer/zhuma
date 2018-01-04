package com.zhuma.demo.model.po;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.zhuma.demo.annotation.EnumValue;

/**
 * @desc 用户PO

 * @author zhumaer
 * @since 6/15/2017 2:48 PM
 */
@Data
public class User implements Serializable {

	private static final long serialVersionUID = 2594274431751408585L;

	/**
	 * 用户ID
	 */
	private Long id;

	/**
	 * 登录密码
	 */
	@NotBlank
	private String pwd;

	/**
	 * 昵称
	 */
	@NotBlank
	@Length(min=1, max=64)
	private String nickname;

	/**
	 * 头像
	 */
	private String img;

	/**
	 * 电话
	 */
	@Pattern(regexp = "^1[3-9]\\d{9}$")
	private String phone;

	/**
	 * 账号状态
	 */
	@EnumValue(enumClass=UserStatusEnum.class, enumMethod="isValidName")
	private String status;

	/**
	 * 最新的登录时间
	 */
	private Date latestLoginTime;

	/**
	 * 最新的登录IP
	 */
	private String latestLoginIp;

	private Date createTime;
	private Date updateTime;
	
	/**
	 * 用户状态枚举
	 */
	public enum UserStatusEnum {
		/**正常的*/
		NORMAL,
		/**禁用的*/
		DISABLED,
		/**已删除的*/
		DELETED;

		/**
		 * 判断参数合法性
		 */
		public static boolean isValidName(String name) {
			for (UserStatusEnum userStatusEnum : UserStatusEnum.values()) {
				if (userStatusEnum.name().equals(name)) {
					return true;
				}
			}
			return false;
		}
	}
	
}
