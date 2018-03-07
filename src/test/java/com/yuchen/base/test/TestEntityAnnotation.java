package com.yuchen.base.test;

import java.util.Date;

import org.junit.Test;

import com.yuchen.base.annotations.tracker.EntityTracker;
import com.yuchen.base.entity.Visitor;
import com.yuchen.base.pojo.EntityResult;

public class TestEntityAnnotation {
	
	@Test
	public void testEntity() throws Exception {
		Visitor visitor = new Visitor();
		visitor.setName("yuchen352416");
		visitor.setEmail("yuchen352416@163.com");
		visitor.setStatus(1);
		visitor.setCreateTime(new Date());
		
		EntityResult result = EntityTracker.tracker(visitor.getClass());
		System.out.println(result);
	}
}
