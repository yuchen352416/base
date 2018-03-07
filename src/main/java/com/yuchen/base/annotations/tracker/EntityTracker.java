package com.yuchen.base.annotations.tracker;

import com.yuchen.base.annotations.Entity;
import com.yuchen.base.pojo.EntityResult;

public class EntityTracker {

	public static EntityResult tracker(Class<?> clazz) {
		EntityResult result = null;
		if (null != clazz) {
			Entity entity = clazz.getAnnotation(Entity.class);
			if (null != entity) {
				result = new EntityResult();
				result.setName(entity.name());
				result.setEntity(clazz.getSimpleName());
			}
		}
		return result;
	}
}
