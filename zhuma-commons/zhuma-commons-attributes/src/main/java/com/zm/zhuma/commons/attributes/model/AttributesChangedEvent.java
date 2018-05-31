package com.zm.zhuma.commons.attributes.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributesChangedEvent<OID> implements Serializable {
	private static final long serialVersionUID = -5098574719305009319L;

	private AttributesChange<OID> data;

	private Date occurredTime;

}
