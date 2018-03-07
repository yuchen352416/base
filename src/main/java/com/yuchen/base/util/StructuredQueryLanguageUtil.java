package com.yuchen.base.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuchen.base.annotations.tracker.ColumnTracker;
import com.yuchen.base.annotations.tracker.EntityTracker;
import com.yuchen.base.pojo.ColumnResult;
import com.yuchen.base.pojo.EntityResult;

public class StructuredQueryLanguageUtil {
	
	private StructuredQueryLanguageUtil() { }
	
	public static Map<String, Object> getInsertSQL(Object object) {
		String comma = ", ";
		List<Object> objects = null;
		Map<String, Object> result = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder field = new StringBuilder();
		StringBuilder question = new StringBuilder();
		
		EntityResult entity = EntityTracker.tracker(object.getClass());
		List<ColumnResult> columns = ColumnTracker.tracker(object);
		
		if (null != entity) {
			sql.append("INSERT INTO " + entity.getName().toUpperCase());
		} else {
			// TODO Temporarily use RuntimeException  
			throw new RuntimeException("This object does not use 'Entity' Annotation");
		}
		if (null != columns && !columns.isEmpty()) {
			field.append("(");
			question.append("(");
			objects = new ArrayList<Object>();
			for(int i = 0; i < columns.size(); i++) {
				if (!columns.get(i).getPrimaryKey()) {
					field.append(columns.get(i).getName().toUpperCase());
					question.append("?");
					objects.add(columns.get(i).getValue());
					if (i < columns.size()-1) {
						field.append(comma);
						question.append(comma);					
					}					
				}
			}
			field.append(")");
			question.append(")");
			sql.append(field).append(" VALUES ").append(question);
			result = new HashMap<>();
			result.put("sql", sql.toString());
			result.put("params", objects.toArray());
		} else {
			// TODO Temporarily use RuntimeException  
			throw new RuntimeException("This object does not use 'Column' Annotation");
		}
		return result;
	}
	
	public static Map<String, Object> getDeleteSQL(Object object) {
		boolean flag = true;
		List<Object> objects = null;
		Map<String, Object> result = null;
		StringBuilder sql = new StringBuilder();
		
		List<ColumnResult> columns = ColumnTracker.tracker(object);
		EntityResult entity = EntityTracker.tracker(object.getClass());
		
		if (null != entity) {
			sql.append("DELETE FROM " + entity.getName().toUpperCase());
		} else {
			// TODO Temporarily use RuntimeException  
			throw new RuntimeException("This object does not use 'Entity' Annotation");
		}
		if (null != columns) {
			for (ColumnResult column : columns) {
				if (column.getPrimaryKey()) {
					flag = false;
					if (null != column.getValue()) {
						objects = new ArrayList<>();
						sql.append(" WHERE " + column.getName().toUpperCase() + " = ?");
						objects.add(column.getValue());
						result = new HashMap<>();
						result.put("sql", sql.toString());
						result.put("params", objects.toArray());
						break;				
					} else {
						// TODO Temporarily use RuntimeException  
						throw new RuntimeException("This object primary key is empty");
					}
				}
			}
			if (flag) {
				// TODO Temporarily use RuntimeException  
				throw new RuntimeException("This object does not have a primary key");
			}
		} else {
			// TODO Temporarily use RuntimeException  
			throw new RuntimeException("This object does not use 'Column' Annotation");
		}
		return result;
	}
	
	
	public static Map<String, Object> getUpdateSQL(Object object, boolean isfull) {
		boolean flag = true;
		Map<String, Object> condition = null;
		List<Object> objects = null;
		Map<String, Object> result = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder field = new StringBuilder();
		
		List<ColumnResult> columns = ColumnTracker.tracker(object);
		EntityResult entity = EntityTracker.tracker(object.getClass());
		
		if (null != entity) {
			sql.append("UPDATE " + entity.getName().toUpperCase());
		} else {
			// TODO Temporarily use RuntimeException  
			throw new RuntimeException("This object does not use 'Entity' Annotation");
		}
		if (null != columns && !columns.isEmpty()) {
			field.append(" SET ");
			objects = new ArrayList<Object>();
			for(int i = 0; i < columns.size(); i++) {
				if (!columns.get(i).getPrimaryKey()) {
					if (!isfull) {
						Object o = columns.get(i).getValue();
						if (o instanceof String) {
							if (StringUtil.isEmpty((String) o)) {
								continue;
							}
						} else {
							if (null == o) {
								continue;
							}
						}
					}
					field.append(columns.get(i).getName().toUpperCase() + " = ?, ");
					objects.add(columns.get(i).getValue());
				} else {
					flag = false;
					Object o = columns.get(i).getValue();
					if (o instanceof String) {
						if (StringUtil.isEmpty((String) o)) {
							// TODO Temporarily use RuntimeException  
							throw new RuntimeException("This object primary key is empty");
						}
					} else {
						if (null == o) {
							// TODO Temporarily use RuntimeExceptio
							throw new RuntimeException("This object primary key is empty");
						}
					}
					condition = new HashMap<>();
					condition.put("where",	" WHERE " + columns.get(i).getName().toUpperCase() + " = ?");
					condition.put("value", o);
				}
			}
			if (flag) {
				// TODO Temporarily use RuntimeException  
				throw new RuntimeException("This object not included primary key");
			}
			if (null == objects || objects.size() == 0) {
				// TODO Temporarily use RuntimeException  
				throw new RuntimeException("This object has no updatable fields");
			}
			
			sql.append(field.toString().substring(0, field.length() - 2)); // Remove the last comma
			sql.append(condition.get("where"));
			objects.add(condition.get("value"));
			result = new HashMap<>();
			result.put("sql", sql.toString());
			result.put("params", objects.toArray());
		} else {
			// TODO Temporarily use RuntimeException  
			throw new RuntimeException("This object does not use 'Column' Annotation");
		}
		return result;
	}
	
	
}
