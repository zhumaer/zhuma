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
public class AttributesChangement<OID> implements Serializable {
	private static final long serialVersionUID = -5008407345712737581L;

	private String objectType;

	private OID objectId;

	private Map<String, AttributeChangement> added;

	private Map<String, AttributeChangement> updated;

	private Map<String, AttributeChangement> removed;

}
