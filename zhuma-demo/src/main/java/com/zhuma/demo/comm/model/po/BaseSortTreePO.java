package com.zhuma.demo.comm.model.po;

import javax.persistence.Column;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @desc 基础排序树PO类

 * @author zhuamer
 * @since 18/12/2017 2:14 PM
 */
@Data
public abstract class BaseSortTreePO<PK> extends BaseTreePO<PK> implements SortTreePO<PK>{

	@ApiModelProperty(value = "排序值")
	@Column
	private Long sort;

}
