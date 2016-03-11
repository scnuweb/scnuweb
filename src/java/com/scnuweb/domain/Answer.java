package com.scnuweb.domain;

/**
 * 
 * @author yehao 
 * @date 2016年3月4日
 * @comment 考试提交答案封装类
 */
public class Answer {
	private Long examItemId;
	private String answer;
	private String examItemName;
	private int grade;
	public Long getExamItemId() {
		return examItemId;
	}
	public void setExamItemId(Long examItemId) {
		this.examItemId = examItemId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getExamItemName() {
		return examItemName;
	}
	public void setExamItemName(String examItemName) {
		this.examItemName = examItemName;
	}
	
}
