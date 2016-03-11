package com.scnuweb.domain;

/**
 * 
 * @author yehao 
 * @date 2016年3月5日
 * @comment 用于解析前端提交的answer格式
 */
public class AnswerItem {
	private String id;
	private String type;
	private String value;
	private int order;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
}
