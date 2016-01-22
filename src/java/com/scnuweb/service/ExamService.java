package com.scnuweb.service;

import java.util.List;
import java.util.Map;

import com.scnuweb.dao.ExamDAO;
import com.scnuweb.entity.Exam;

public interface ExamService extends BaseService<ExamDAO>{
	public List<Exam> getAllExams();
	public List<Exam> getExamByParams(Map<String, Object> params);
	public List<Exam> getExamByUser(Long userId);
	public Exam addExam(Exam exam);
	public Exam updateExam(Exam exam);
	public Exam getExamById(Long examId);
	public void delete(Long examId);
	public void importCandidate(Long examId,String candidateList);
	public void importExamItem(Long examId,String examItemList);
	public void deleteCandidate(Long examId,Long candidateId);
	public void deleteExamItem(Long examId,Long examItemId);
}
