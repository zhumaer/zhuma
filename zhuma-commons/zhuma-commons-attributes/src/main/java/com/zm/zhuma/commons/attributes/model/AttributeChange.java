package com.zm.zhuma.commons.attributes.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeChange implements Serializable {
	private static final long serialVersionUID = -662090239071614840L;

	private Object previous;

	private Object current;

}