package com.scnuweb.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.scnuweb.util.StaticVar;


@Entity
@Table(name=StaticVar.TABLE_PREFIX+"exam_grade")
public class ExamGrade {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="grade")
	private String grade;
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="candidate_id",referencedColumnName="id",unique=false)
	private User candidate;
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="exam_id",referencedColumnName="id",unique=false)
	private Exam exam;
	@Column(name="candidate_answer",columnDefinition = "text")
	private String candidateAnswer;
	@Column(name="is_submitted")
	private int isSubmitted;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public User getCandidate() {
		return candidate;
	}
	public void setCandidate(User candidate) {
		this.candidate = candidate;
	}
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	public String getCandidateAnswer() {
		return candidateAnswer;
	}
	public void setCandidateAnswer(String candidateAnswer) {
		this.candidateAnswer = candidateAnswer;
	}
	public int getIsSubmitted() {
		return isSubmitted;
	}
	public void setIsSubmitted(int isSubmitted) {
		this.isSubmitted = isSubmitted;
	}
	
	
}
