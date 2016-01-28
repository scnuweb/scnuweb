package com.scnuweb.service;

import com.scnuweb.dao.ExamGradeDAO;
import com.scnuweb.entity.ExamGrade;

public interface ExamGradeService extends BaseService<ExamGradeDAO>{
	public ExamGrade getExamGradeById(Long examGradeId);
	public ExamGrade getExamGrade(Long examId,Long candidateId);
	public void addExamGrade(ExamGrade examGrade);
	public void updateExamGrade(ExamGrade examGrade);
	public void deleteExamGrade(Long examGradeId);
}
