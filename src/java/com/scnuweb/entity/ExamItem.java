package com.scnuweb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.scnuweb.util.StaticVar;

@Entity
@Table(name=StaticVar.TABLE_PREFIX+"exam_item")
public class ExamItem {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="question_struct")
	private String questionStruct;
	@Column(name="exam_item_name")
	private String examItemName;
	@Column(name="generate_time")
	private Date generateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestionStruct() {
		return questionStruct;
	}
	public void setQuestionStruct(String questionStruct) {
		this.questionStruct = questionStruct;
	}
	public String getExamItemName() {
		return examItemName;
	}
	public void setExamItemName(String examItemName) {
		this.examItemName = examItemName;
	}
	public Date getGenerateTime() {
		return generateTime;
	}
	public void setGenerateTime(Date generateTime) {
		this.generateTime = generateTime;
	}
	
	
	
}
