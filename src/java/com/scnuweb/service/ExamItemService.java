package com.scnuweb.service;

import java.util.List;
import java.util.Map;

import com.scnuweb.dao.ExamItemDAO;
import com.scnuweb.entity.Exam;
import com.scnuweb.entity.ExamItem;
import com.scnuweb.entity.User;

public interface ExamItemService extends BaseService<ExamItemDAO>{
	public List<ExamItem> getAllExamItems();
	public List<ExamItem> getExamItemsByParams(Map<String, Object> params);
	public List<ExamItem> getExamItemsByExam(Long examId);
	public ExamItem addExamItem(ExamItem examItem);
	public ExamItem updateExamItem(ExamItem examItem);
	public void delete(Long examItemId);
	public ExamItem getExamItemById(Long examItemId);
	
}
