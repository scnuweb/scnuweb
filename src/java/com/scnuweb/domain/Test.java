package com.scnuweb.domain;

import java.util.ArrayList;
import java.util.List;

import com.scnuweb.util.MyJson;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Item item = new Item();
		Button button = new Button();
		button.setItemId("test1");
		button.setOrderNumber(1);
		item.setWidget(button);
		item.setType(Item.ITEM_BUTTON);
		System.out.println(MyJson.toJson(item));
		*/
		ItemList itemList = new ItemList();
		List<Item> items = new ArrayList<>();
		
		items.add(getInputItem("need1", 1, 1, "123456"));
		items.add(getInputItem("question2", 1, 1, "123456"));
		items.add(getButtonItem("first-step-btn", 2));
		items.add(getInputItem("saveN", 3, 1, "555"));
		items.add(getButtonItem("second-step-btn", 4));
		items.add(getButtonItem("third-step-btn", 5));
		items.add(getButtonItem("final-step-btn", 6));
		itemList.setItemList(items);
		String json = MyJson.toJson(itemList);
		System.out.println(json);
		
	}
	public static Item getButtonItem(String itemId,int order) {
		Button button = new Button();
		button.setItemId(itemId);
		button.setOrderNumber(order);
		Item item = new Item();
		item.setType(Item.ITEM_BUTTON);
		item.setButton(button);
		return item;
	}
	public static Item getInputItem(String itemId,int order,int isSensitive,String value) {
		Input input = new Input();
		input.setItemId(itemId);
		input.setOrderNumber(order);
		input.setInputValue(value);
		input.setIsValueSensitive(isSensitive);
		Item item = new Item();
		item.setType(Item.ITEM_INPUT);
		item.setInput(input);
		return item;
	}
}
