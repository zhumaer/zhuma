package com.zhuma.demo.model.po;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.zhuma.demo.annotation.EnumValueAnn;
import com.zhuma.demo.util.StringUtil;

/**
 * @desc 用户PO

 * @author zhumaer
 * @since 6/15/2017 2:48 PM
 */
public class User implements Serializable {

	private static final long serialVersionUID = 2594274431751408585L;

	private Long id;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 投顾真实姓名
	 */
	@NotBlank
	private String realName;

	/**
	 * 昵称
	 */
	@NotBlank
	private String nickname;

	/**
	 * 头像
	 */
	private String img;

	/**
	 * 简介
	 */
	private String desc;

	/**
	 * 状态 {@link StatusEnum}
	 */
	@EnumValueAnn(enumClass = StatusEnum.class, enumMethod = "isValidCode")
	private Integer status;

	/**
	 * 用户账号状态 {@link AccountStatusEnum}
	 */
	@EnumValueAnn(enumClass = AccountStatusEnum.class, enumMethod = "isValidCode")
	private Integer accountStatus;

	/**
	 * 电话
	 */
	@Pattern(regexp = "^1[3-9]\\d{9}$")
	private String phone;

	/**
	 * 性别 {@link} 0 男 1 女
	 */
	@Range(min = 0, max = 1)
	private Integer gender;

	/**
	 * 登录密码
	 */
	@NotBlank
	private String pwd;

	/**
	 * 随机盐（目前用于密码加密）
	 */
	private String randomSalt;

	/**
	 * 用户环信UUID
	 */
	private String hxUserUuid;

	/**
	 * 用户环信密码
	 */
	private String hxPwd;

	/**
	 * 角色Code {@link RoleCodeEnum}
	 */
	@NotBlank
	private String roleCode;

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

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", nickname='" + nickname + '\'' +
				", img='" + img + '\'' +
				", desc='" + desc + '\'' +
				", status=" + status +
				", accountStatus=" + accountStatus +
				", phone='" + phone + '\'' +
				", gender=" + gender +
				", pwd='" + pwd + '\'' +
				", hxUserUuid='" + hxUserUuid + '\'' +
				", hxPwd='" + hxPwd + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}

	public enum StatusEnum {
		// 用户状态
		ONLINE(0, "在线"),
		OFFLINE(1, "离线"),
		BUSY(2, "忙碌");

		private Integer code;

		private String desc;

		StatusEnum(Integer code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public Integer getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}

		public static boolean isValidCode(Integer code) {
			for (StatusEnum status : StatusEnum.values()) {
				if (status.getCode().equals(code)) {
					return true;
				}
			}
			return false;
		}

		public static boolean isValidName(String value) {
			if (StringUtil.isEmpty(value)) {
				return false;
			}

			for (StatusEnum status : StatusEnum.values()) {
				if (status.name().equals(value)) {
					return true;
				}
			}
			return false;
		}

		public static Optional<Integer> getCodeByName(String enumName) {
			for (StatusEnum status : StatusEnum.values()) {
				if (status.name().equals(enumName)) {
					return Optional.of(status.getCode());
				}
			}
			return Optional.empty();
		}

		public static Optional<String> getNameByCode(Integer code) {
			for (StatusEnum status : StatusEnum.values()) {
				if (status.getCode().intValue() == code) {
					return Optional.of(status.name());
				}
			}
			return Optional.empty();
		}
	}

	public enum AccountStatusEnum {
		// 账户状态
		ACCOUNT_NORMAL(0, "正常"),
		ACCOUNT_SUSPENDED(1, "停用"),
		ACCOUNT_DELETED(2, "已删除");

		private Integer code;

		private String desc;

		AccountStatusEnum(Integer code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public Integer getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}

		public static boolean isValidCode(Integer code) {

			if (code == null) {
				return false;
			}

			for (AccountStatusEnum status : AccountStatusEnum.values()) {
				if (status.getCode().equals(code)) {
					return true;
				}
			}
			return false;
		}

		public static Optional<Integer> getCodeByName(String enumName) {
			for (AccountStatusEnum status : AccountStatusEnum.values()) {
				if (status.name().equals(enumName)) {
					return Optional.of(status.getCode());
				}
			}
			return Optional.empty();
		}

		public static Optional<String> getNameByCode(Integer code) {
			for (AccountStatusEnum status : AccountStatusEnum.values()) {
				if (status.getCode().intValue() == code) {
					return Optional.of(status.name());
				}
			}
			return Optional.empty();
		}
	}

	public enum RoleConversion {
		NORMAL(3, "普通用户", "normal"),
		PROFESSIONAL(1, "分析师", RoleCodeEnum.ANALYST.getCode()),
		COUNSELOR(0, "投顾", RoleCodeEnum.ADVISER.getCode());

		private Integer code;
		private String name;
		private String roleCode;

		RoleConversion(Integer code, String name, String roleCode) {
			this.code = code;
			this.name = name;
			this.roleCode = roleCode;
		}

		public Integer getCode() {
			return this.code;
		}

		public String getName() {
			return this.name;
		}

		public String getRoleCode() {
			return this.roleCode;
		}
	}

	public enum RoleCodeEnum {
		ADVISER("adviser", "投顾"),
		DIRECTOR("director", "主任"),
		MANAGER("manager", "经理"),
		SUPER_ADMIN("super_admin", "超级管理员"),
		ANALYST("analyst", "分析师"),
		RESEARCHER("researcher", "研究员");

		private String code;

		private String desc;

		RoleCodeEnum(String code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public String getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}

		public static boolean isValidCode(String code) {

			if (code == null) {
				return false;
			}

			for (RoleCodeEnum role : RoleCodeEnum.values()) {
				if (role.getCode().equals(code)) {
					return true;
				}
			}
			return false;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getHxUserUuid() {
		return hxUserUuid;
	}

	public void setHxUserUuid(String hxUserUuid) {
		this.hxUserUuid = hxUserUuid;
	}

	public String getHxPwd() {
		return hxPwd;
	}

	public void setHxPwd(String hxPwd) {
		this.hxPwd = hxPwd;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRandomSalt() {
		return randomSalt;
	}

	public void setRandomSalt(String randomSalt) {
		this.randomSalt = randomSalt;
	}

	public Date getLatestLoginTime() {
		return latestLoginTime;
	}

	public void setLatestLoginTime(Date latestLoginTime) {
		this.latestLoginTime = latestLoginTime;
	}

	public String getLatestLoginIp() {
		return latestLoginIp;
	}

	public void setLatestLoginIp(String latestLoginIp) {
		this.latestLoginIp = latestLoginIp;
	}

}
