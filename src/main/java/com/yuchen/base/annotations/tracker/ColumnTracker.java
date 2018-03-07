package com.yuchen.base.annotations.tracker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.yuchen.base.annotations.Column;
import com.yuchen.base.pojo.ColumnResult;

public class ColumnTracker {
	
	public static List<ColumnResult> tracker(Object object) {
		List<ColumnResult> result = null;
		if (null != object) {
			Class<?> clazz = object.getClass();
			if (null != clazz) {
				result = new ArrayList<>();
				for (Field field : clazz.getDeclaredFields()) {
					Column column = field.getAnnotation(Column.class);
					if (null != column) {
						try {
							field.setAccessible(true);
							ColumnResult columnResult = new ColumnResult();
							Object value = field.get(object);
							columnResult.setField(field.getName());
							columnResult.setName(column.name());
							columnResult.setNullable(column.nullable());
							columnResult.setValue(value);
							columnResult.setPrimaryKey(column.primaryKey());
							result.add(columnResult);
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return result;
	}
}
