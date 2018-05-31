package com.zm.zhuma.commons.attributes.model;

import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributesChange<OID> implements Serializable {
	private static final long serialVersionUID = -5008407345712737581L;

	private String objectType;

	private OID objectId;

	private Map<String, AttributeChange> added;

	private Map<String, AttributeChange> updated;

	private Map<String, AttributeChange> removed;

}
