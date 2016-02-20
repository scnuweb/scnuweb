package com.scnuweb.domain;

/**
 * 
 * @author yehao 
 * @date 2016年2月16日
 * @comment 对应ExamItem 里面的select控件
 */
public class Select {
	private String itemId;
	private int orderNumber;
	private String selectValue;
	private int isValueSensitive; //1 for yes, 0 for no
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getSelectValue() {
		return selectValue;
	}
	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}
	public int getIsValueSensitive() {
		return isValueSensitive;
	}
	public void setIsValueSensitive(int isValueSensitive) {
		this.isValueSensitive = isValueSensitive;
	}
	
}
