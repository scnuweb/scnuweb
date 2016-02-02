package com.scnuweb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.scnuweb.util.StaticVar;

@Entity
@Table(name=StaticVar.TABLE_PREFIX+"exam")
public class Exam {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="generate_time")
	private Date generateTime;
	@Column(name="start_time")
	private Date startTime;
	@Column(name="end_time")
	private Date endTime;
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name=StaticVar.TABLE_PREFIX+"user_exam",
		joinColumns={ @JoinColumn(name ="exam_id" )},
		inverseJoinColumns = { @JoinColumn(name = "user_id") })
	private List<User> candidates = new ArrayList<User>();
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name=StaticVar.TABLE_PREFIX+"exam_exam_item",
		joinColumns={ @JoinColumn(name ="exam_id" )},
		inverseJoinColumns = { @JoinColumn(name = "exam_item_id") })
	private List<ExamItem> examItems = new ArrayList<>();
	@Column(name="exam_name")
	private String examName;
	@OneToMany(cascade=CascadeType.REMOVE,fetch=FetchType.LAZY,mappedBy="exam")
	private List<ExamGrade> examGrades;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getGenerateTime() {
		return generateTime;
	}
	public void setGenerateTime(Date generateTime) {
		this.generateTime = generateTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public List<User> getCandidates() {
		return candidates;
	}
	public void setCandidates(List<User> candidates) {
		this.candidates = candidates;
	}
	public List<ExamItem> getExamItems() {
		return examItems;
	}
	public void setExamItems(List<ExamItem> examItems) {
		this.examItems = examItems;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public List<ExamGrade> getExamGrades() {
		return examGrades;
	}
	public void setExamGrades(List<ExamGrade> examGrades) {
		this.examGrades = examGrades;
	}
	
	
	
	
}
