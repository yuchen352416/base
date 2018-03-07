package com.yuchen.base.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.yuchen.base.annotations.tracker.ColumnTracker;
import com.yuchen.base.entity.Visitor;
import com.yuchen.base.pojo.ColumnResult;

public class TestColumnAnnotation {
	
	@Test
	public void testColumn() throws Exception {
		Visitor visitor = new Visitor();
		visitor.setName("yuchen352416");
		visitor.setEmail("yuchen352416@163.com");
		visitor.setStatus(1);
		visitor.setCreateTime(new Date());
		
		List<ColumnResult> list = ColumnTracker.tracker(visitor);
		System.out.println(list);
	}
}
