package com.yuchen.base.exception;

public class ColumnValueNullException extends BaseException {

	private static final long serialVersionUID = 1L;
	
	public ColumnValueNullException() {
		super();
	}
	
	public ColumnValueNullException(String columnName) {
		super("");
	}
	
}
