package com.scnuweb.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yehao 
 * @date 2016年3月2日
 * @comment 封装itemlist
 */
public class ItemList {
	private List<Item> itemList = new ArrayList<>();

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	
}
