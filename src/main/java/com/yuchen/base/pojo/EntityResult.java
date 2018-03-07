package com.yuchen.base.pojo;

import java.io.Serializable;

public class EntityResult implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String entityName;

	private String name;
	
	public String getEntity() {
		return entityName;
	}

	public void setEntity(String entity) {
		this.entityName = entity;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
