package com.zhuma.demo.model.po;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * @desc 用户PO

 * @author zhumaer
 * @since 6/15/2017 2:48 PM
 */
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
	@Pattern
	private String pwd;

	/**
	 * 昵称
	 */
	@NotBlank
	@Length(min=1, max=64, grou )
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
	 * 性别 {@link} 0 男 1 女
	 */
	@Range(min = 0, max = 1)
	private Integer gender;


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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
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

}
