package com.scnuweb.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yehao 
 * @date 2016年3月4日
 * @comment Answer 类的持久化List对象
 */
public class AnswerList {
	private List<Answer> answerList = new ArrayList<>();

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}

	
}
