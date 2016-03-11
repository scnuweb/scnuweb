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
	private Button button;
	private Input input;
	private Select select;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Button getButton() {
		return button;
	}
	public void setButton(Button button) {
		this.button = button;
	}
	public Input getInput() {
		return input;
	}
	public void setInput(Input input) {
		this.input = input;
	}
	public Select getSelect() {
		return select;
	}
	public void setSelect(Select select) {
		this.select = select;
	}
	
	
	
}
