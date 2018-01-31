package com.zhuma.demo.model.po;

import com.zhuma.demo.annotation.EnumValue;
import com.zhuma.demo.comm.model.po.BasePO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @desc 用户PO

 * @author zhumaer
 * @since 6/15/2017 2:48 PM
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User extends BasePO<String> {

	private static final long serialVersionUID = 1831625735139271430L;

	/**
	 * 用户ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
	@Length(min=1, max=64)
	private String id;

	/**
	 * 昵称
	 */
	@NotBlank
	@Length(min=1, max=64)
	private String nickname;

	/**
	 * 性别
	 */
	@NotBlank
	@EnumValue(enumClass=UserGenderEnum.class, enumMethod="isValidName")
	private String gender;

	/**
	 * 头像
	 */
	@Length(max=256)
	private String avatar;

	/**
	 * 状态
	 */
	@NotBlank
	@EnumValue(enumClass=UserTypeEnum.class, enumMethod="isValidName")
	private String type;

	/**
	 * 账号状态
	 */
	@EnumValue(enumClass=UserStatusEnum.class, enumMethod="isValidName")
	private String status;

	/**
	 * 用户性别枚举
	 */
	public enum UserGenderEnum {
		/**男*/
		MALE,
		/**女*/
		FEMALE,
		/**未知*/
		UNKNOWN;

		public static boolean isValidName(String name) {
			for (UserGenderEnum userGenderEnum : UserGenderEnum.values()) {
				if (userGenderEnum.name().equals(name)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 用户类型枚举
	 */
	public enum UserTypeEnum {
		/**普通*/
		NORMAL,
		/**管理员*/
		ADMIN;

		public static boolean isValidName(String name) {
			for (UserTypeEnum userTypeEnum : UserTypeEnum.values()) {
				if (userTypeEnum.name().equals(name)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 用户状态枚举
	 */
	public enum UserStatusEnum {
		/**启用*/
		ENABLED,
		/**禁用*/
		DISABLED;

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
