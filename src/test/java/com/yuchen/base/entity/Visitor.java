package com.yuchen.base.entity;

import java.io.Serializable;
import java.util.Date;

import com.yuchen.base.annotations.Column;
import com.yuchen.base.annotations.Entity;
import com.yuchen.base.date.DateUtil;

@Entity(name="visitor")
public class Visitor implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="id", primaryKey=true)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="create_time")
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Override
	public String toString() {
		return String.format(
				"{Id: %d, Name: %s, Email: %s, CreateTime: %s}", 
				getId(), getName(), getEmail(),DateUtil.dateToString(this.getCreateTime(), DateUtil.DATETIME));
	}
	
	
}
