package com.yuchen.base.pojo;

import java.io.Serializable;

public class ColumnResult implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String fieldName;

	private String name;
	
	private Object value;
	
	private Boolean nullable;
	
	private Boolean primaryKey;

	public String getField() {
		return fieldName;
	}

	public void setField(String field) {
		this.fieldName = field;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Boolean getNullable() {
		return nullable;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}
	
	public Boolean getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Boolean primaryKey) {
		this.primaryKey = primaryKey;
	}
}
