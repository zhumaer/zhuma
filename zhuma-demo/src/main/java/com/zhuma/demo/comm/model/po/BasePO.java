package com.zhuma.demo.comm.model.po;

import java.util.Date;

import javax.persistence.Column;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @desc 基础PO类

 * @author zhuamer
 * @since 7/3/2017 2:14 PM
 */
@Data
public abstract class BasePO<PK> implements PO<PK> {

	//	private PK id;

	@ApiModelProperty(value = "创建时间")
	@Column(name = "create_time")
	private Date createTime;

	@ApiModelProperty(value = "更新时间")
	@Column(name = "update_time")
	private Date updateTime;

}
