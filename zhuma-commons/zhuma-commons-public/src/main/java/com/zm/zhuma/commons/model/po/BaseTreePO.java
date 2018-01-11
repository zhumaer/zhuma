package com.zm.zhuma.commons.model.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

/**
 * @desc 基础树PO类

 * @author zhuamer
 * @since 18/12/2017 2:14 PM
 */
@Data
public abstract class BaseTreePO<PK> extends BasePO<PK> implements TreePO<PK> {

	@ApiModelProperty(value = "父ID")
	@Column(name = "parent_id")
	private PK parentId;

}
