package com.scnuweb.domain;

/**
 * 
 * @author yehao 
 * @date 2016年2月16日
 * @comment 用来包装button,select,input的类
 */
public class Item {
	public static final String ITEM_BUTTON = "button";
	public static final String ITEM_SELECT = "select";
	public static final String ITEM_INPUT = "input";
	private String type;
	private Object widget;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getWidget() {
		return widget;
	}
	public void setWidget(Object widget) {
		this.widget = widget;
	}
	
	
}
