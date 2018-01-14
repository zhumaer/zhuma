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
public class Attribute<OID> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private OID objectId;
	
	private String key;
	
	private String value;
	
	private String type;

}
