package com.zhuma.demo.comm.model.qo;

import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc 分页查询对象

 * @author zhuamer
 * @since 7/6/2017 2:48 PM
 */
@ApiModel("分页查询对象")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageQO<T> {

	/**
	 * 按创建时间倒序排序
	 */
	public static final String ORDER_BY_CREATE_TIME_DESC = "create_time desc";

	@ApiModelProperty(value = "当前页号")
	@Range(min = 1, max = Integer.MAX_VALUE)
	private int pageNum = 1;

	@ApiModelProperty(value = "一页数量")
	@Range(min = 1, max = Integer.MAX_VALUE)
	private int pageSize = 10;

	@ApiModelProperty(value = "排序", notes = "例：create_time desc,update_time desc")
	private String orderBy;

	private T condition;

	public PageQO(int pageNum, int pageSize) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public int getOffset() {
		return (this.pageNum - 1) * this.pageSize;
	}

}
