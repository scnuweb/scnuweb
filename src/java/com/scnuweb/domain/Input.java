package com.scnuweb.domain;


/**
 * 
 * @author yehao 
 * @date 2016年2月16日
 * @comment 对应ExamItem 里面的 input 控件
 */
public class Input {
	 private String itemId;
	 private int orderNumber;
	 private String inputValue;
	 private int isValueSensitive;
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
	public String getInputValue() {
		return inputValue;
	}
	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}
	public int getIsValueSensitive() {
		return isValueSensitive;
	}
	public void setIsValueSensitive(int isValueSensitive) {
		this.isValueSensitive = isValueSensitive;
	}
	 
}
