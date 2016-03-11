package com.scnuweb.service;

import java.util.List;

import com.scnuweb.dao.ExamGradeDAO;
import com.scnuweb.entity.Exam;
import com.scnuweb.entity.ExamGrade;
import com.scnuweb.entity.ExamItem;
import com.scnuweb.entity.User;

public interface ExamGradeService extends BaseService<ExamGradeDAO>{
	public ExamGrade getExamGradeById(Long examGradeId);
	public ExamGrade getExamGrade(Long examId,Long candidateId);
	public void addExamGrade(ExamGrade examGrade);
	public void updateExamGrade(ExamGrade examGrade);
	public void deleteExamGrade(Long examGradeId);
	public boolean submitAnswer(User user,Exam exam,ExamItem examItem,List<String> ans);
	public List<Long> getSubmittedList(ExamGrade examGrade);
}
