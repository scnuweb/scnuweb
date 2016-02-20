package com.scnuweb.domain;

import com.scnuweb.util.MyJson;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Item item = new Item();
		Button button = new Button();
		button.setItemId("test1");
		button.setOrderNumber(1);
		item.setWidget(button);
		item.setType(Item.ITEM_BUTTON);
		System.out.println(MyJson.toJson(item));
	}

}
