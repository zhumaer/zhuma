package com.zhuma.demo.comm.model.po;

import java.util.Date;

import javax.persistence.Column;

/**
 * @desc 基础PO类

 * @author yujia.cheng@baidao.com
 * @since 7/3/2017 2:14 PM
 */
public class BasePO {

	@Column
	private Date createTime;

	@Column
	private Date updateTime;

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
