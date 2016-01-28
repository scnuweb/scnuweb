package com.scnuweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;








import com.scnuweb.util.StaticVar;

@Entity
@Table(name=StaticVar.TABLE_PREFIX+"user")
public class User {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="user_type")	// 1 for administrator, 2 for candidate 
	private Integer userType;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="true_name")
	private String trueName;
	@Column(name="no")
	private String no;
	@Column(name="unit")
	private String unit;
	@Column(name="position")
	private String position;
	@Column(name="basic_info")
	private String basicInfo;
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY,mappedBy="candidates")
	private List<Exam> exams = new ArrayList<>();
	@OneToMany(cascade=CascadeType.REMOVE,fetch=FetchType.LAZY,mappedBy="candidate")
	private List<ExamGrade> examGrades;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getBasicInfo() {
		return basicInfo;
	}
	public void setBasicInfo(String basicInfo) {
		this.basicInfo = basicInfo;
	}
	public List<Exam> getExams() {
		return exams;
	}
	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}
	public List<ExamGrade> getExamGrades() {
		return examGrades;
	}
	public void setExamGrades(List<ExamGrade> examGrades) {
		this.examGrades = examGrades;
	}
	
	
	public User() {}
	public User(Long id,String username,String trueName,String no,String position,String unit) {
		this.id=id;
		this.username=username;
		this.trueName=trueName;
		this.no=no;
		this.position=position;
		this.unit=unit;
	}
	
	
}
