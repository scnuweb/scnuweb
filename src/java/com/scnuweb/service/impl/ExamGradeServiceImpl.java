package com.scnuweb.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scnuweb.dao.ExamGradeDAO;
import com.scnuweb.entity.Exam;
import com.scnuweb.entity.ExamGrade;
import com.scnuweb.entity.User;
import com.scnuweb.service.ExamGradeService;
import com.scnuweb.service.ExamService;
import com.scnuweb.service.UserService;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ExamGradeServiceImpl extends BaseServiceImpl<ExamGrade> implements ExamGradeService{

	private ExamGradeDAO examGradeDAO;
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private UserService userService;
	
	
	@Override
	@Autowired
	public void setBaseServiceDAO(ExamGradeDAO dao) {
		// TODO Auto-generated method stub
		super.setBaseDAO(dao);
		this.examGradeDAO = dao;
	}

	@Override
	public ExamGrade getExamGradeById(Long examGradeId) {
		// TODO Auto-generated method stub
		return examGradeDAO.get(examGradeId);
	}

	@Override
	public ExamGrade getExamGrade(Long examId, Long candidateId) {
		// TODO Auto-generated method stub
		Exam exam = examService.getExamById(examId);
		User candidate = userService.getUserById(candidateId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("exam", exam);
		params.put("candidate", candidate);
		return examGradeDAO.getObject(params);
	}

	@Override
	public void addExamGrade(ExamGrade examGrade) {
		// TODO Auto-generated method stub
		ExamGrade preExamGrade = getExamGrade(examGrade.getExam().getId(), examGrade.getCandidate().getId());
		if(preExamGrade==null)examGradeDAO.save(examGrade);
	}

	@Override
	public void updateExamGrade(ExamGrade examGrade) {
		// TODO Auto-generated method stub
		examGradeDAO.update(examGrade);
	}

	@Override
	public void deleteExamGrade(Long examGradeId) {
		// TODO Auto-generated method stub
		examGradeDAO.del(examGradeId);
	}

}
