package com.yuchen.base.test;

import java.util.Date;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.yuchen.base.entity.Visitor;
import com.yuchen.base.util.StructuredQueryLanguageUtil;

public class TestSQLUtil {

	Visitor visitor;

	@Before
	public void setUp() {
		visitor = new Visitor();
		visitor.setId(4L);
		visitor.setName("yuchen352416");
		visitor.setEmail("yuchen352416@163.com");
		visitor.setStatus(1);
		visitor.setCreateTime(new Date());
	}

	@Test
	public void testInsert() {
		Map<String, Object> map = StructuredQueryLanguageUtil.getInsertSQL(visitor);
		System.out.println(map.get("sql"));
		System.out.println(map.get("params"));
	}
	
	@Test
	public void testDelete() {
		Map<String, Object> map = StructuredQueryLanguageUtil.getDeleteSQL(visitor);
		System.out.println(map.get("sql"));
		System.out.println(map.get("params"));
	}
	
	@Test
	public void testUpdate() {
		Map<String, Object> map = StructuredQueryLanguageUtil.getUpdateSQL(visitor, true);
		System.out.println(map.get("sql"));
		System.out.println(map.get("params"));
	}

	@Test
	public void testSelect() {
		Map<String, Object> map = StructuredQueryLanguageUtil.getInsertSQL(visitor);
		System.out.println(map.get("sql"));
		System.out.println(map.get("params"));
	}
}
