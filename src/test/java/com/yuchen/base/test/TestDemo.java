package com.yuchen.base.test;

import java.util.Date;

import org.junit.Test;

import com.yuchen.base.dao.DatabaseAccessObject;
import com.yuchen.base.entity.Visitor;

public class TestDemo {
	
	@Test
	public void testEntity() {
		Visitor visitor = new Visitor();
		visitor.setName("yuchen352416");
		visitor.setEmail("yuchen352416@163.com");
		visitor.setStatus(1);
		visitor.setCreateTime(new Date());
		
		System.out.println(visitor);
	}
	
	@Test
	public void testInsert() {
		Visitor visitor = new Visitor();
		DatabaseAccessObject dao = new DatabaseAccessObject();
		
		visitor.setName("yuchen352416");
		visitor.setEmail("yuchen352416@163.com");
		visitor.setStatus(1);
		visitor.setCreateTime(new Date());
		Long id = dao.insert(visitor);
		
		System.out.println(id);
	}
	
	@Test
	public void testDelete() {
		DatabaseAccessObject dao = new DatabaseAccessObject();
		Visitor visitor = new Visitor();
		visitor.setId(4L);
		boolean result = dao.delete(visitor);
		System.out.println(result);
	}
	
	@Test
	public void testUpdate() {
		DatabaseAccessObject dao = new DatabaseAccessObject();
		Visitor visitor = new Visitor();
		visitor.setId(4L);
		visitor.setEmail("13463170232@139.com");
		visitor.setName("13463170232");
		visitor.setStatus(9);
		boolean result = dao.update(visitor, true);
		System.out.println(result);
	}
	
	
	
}
