package com.yc.bean;

import java.io.Serializable;

public class Tag implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer t_id;
	private String t_name;
	private Integer t_count;
	private String [] tname;
	public String[] getTname() {
		return tname;
	}
	public void setTname(String[] tname) {
		this.tname = tname;
	}
	public Integer getT_id() {
		return t_id;
	}
	public void setT_id(Integer t_id) {
		this.t_id = t_id;
	}
	public String getT_name() {
		return t_name;
	}
	@Override
	public String toString() {
		return "Tag [t_id=" + t_id + ", t_name=" + t_name + ", t_count=" + t_count + "]";
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public Integer getT_count() {
		return t_count;
	}
	public void setT_count(Integer t_count) {
		this.t_count = t_count;
	}
	public Tag() {
		super();
	}
	public Tag(String t_name, Integer t_count) {
		super();
		this.t_name = t_name;
		this.t_count = t_count;
	}
	
}
