package com.scnuweb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scnuweb.dao.ExamItemDAO;
import com.scnuweb.entity.Exam;
import com.scnuweb.entity.ExamItem;
import com.scnuweb.service.ExamItemService;
import com.scnuweb.service.ExamService;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ExamItemServiceImpl extends BaseServiceImpl<ExamItem> implements ExamItemService{
	
	private ExamItemDAO examItemDAO;
	
	@Autowired
	private ExamService examService;
	
	@Override
	@Autowired
	public void setBaseServiceDAO(ExamItemDAO dao) {
		// TODO Auto-generated method stub
		super.setBaseDAO(dao);
		this.examItemDAO = dao;
	}

	@Override
	public List<ExamItem> getAllExamItems() {
		// TODO Auto-generated method stub
		return examItemDAO.getObjectList(new HashMap<String, Object>());
	}

	@Override
	public List<ExamItem> getExamItemsByParams(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return examItemDAO.getObjectList(params);
	}

	@Override
	public List<ExamItem> getExamItemsByExam(Long examId) {
		// TODO Auto-generated method stub
		List<ExamItem> retList = examService.getExamById(examId).getExamItems();
		return retList;
	}

	@Override
	public ExamItem addExamItem(ExamItem examItem) {
		// TODO Auto-generated method stub
		examItemDAO.save(examItem);
		return examItem;
	}

	@Override
	public ExamItem updateExamItem(ExamItem examItem) {
		// TODO Auto-generated method stub
		examItemDAO.update(examItem);
		return examItem;
	}

	@Override
	public void delete(Long examItemId) {
		// TODO Auto-generated method stub
		examItemDAO.del(examItemId);
	}

	@Override
	public ExamItem getExamItemById(Long examItemId) {
		// TODO Auto-generated method stub
		return examItemDAO.get(examItemId);
	}

}
