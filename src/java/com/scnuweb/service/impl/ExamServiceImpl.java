package com.scnuweb.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scnuweb.dao.ExamDAO;
import com.scnuweb.entity.Exam;
import com.scnuweb.entity.User;
import com.scnuweb.service.ExamService;
import com.scnuweb.service.UserService;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ExamServiceImpl extends BaseServiceImpl<Exam> implements ExamService{
	
	private ExamDAO examDAO;
	
	@Autowired
	private UserService userService;

	@Override
	@Autowired
	public void setBaseServiceDAO(ExamDAO dao) {
		// TODO Auto-generated method stub
		super.setBaseDAO(dao);
		this.examDAO = dao;
	}

	@Override
	public List<Exam> getAllExams() {
		// TODO Auto-generated method stub
		return examDAO.getObjectList(new HashMap<String, Object>());
	}

	@Override
	public List<Exam> getExamByParams(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return examDAO.getObjectList(params);
	}

	@Override
	public List<Exam> getExamByUser(Long userId) {
		// TODO Auto-generated method stub
		List<Exam> retList = userService.getUserById(userId).getExams();
		return retList;
	}

	@Override
	public Exam getExamById(Long examId) {
		// TODO Auto-generated method stub
		return examDAO.get(examId);
	}

	@Override
	public void delete(Long examId) {
		// TODO Auto-generated method stub
		examDAO.del(examId);
	}

	@Override
	public Exam addExam(Exam exam) {
		// TODO Auto-generated method stub
		Date nowDate = new Date();
		exam.setGenerateTime(nowDate);
		examDAO.save(exam);
		return exam;
	}

	@Override
	public Exam updateExam(Exam exam) {
		// TODO Auto-generated method stub
		examDAO.update(exam);
		return exam;
	}

	@Override
	public void importCandidate(Long examId,String candidateList) {
		// TODO Auto-generated method stub
		String[] list = candidateList.split(",");
		Exam exam = examDAO.get(examId);
		List<User> candidates = exam.getCandidates();
		Set<Long> checkSet = new HashSet<>();
		for(User candidate:candidates) {
			checkSet.add(candidate.getId());
		}
		for(String item:list) {
			Long candidateId = Long.parseLong(item);
			if(checkSet.contains(candidateId))break;
			User candidate = userService.getUserById(candidateId);
			if(candidate!=null) {
				candidates.add(candidate);
			}
		}
		exam.setCandidates(candidates);
		examDAO.update(exam);
	}

	@Override
	public void deleteCandidate(Long examId, Long candidateId) {
		// TODO Auto-generated method stub
		Exam exam = examDAO.get(examId);
		User candidate = userService.getUserById(candidateId);
		if(candidate!=null) {
			exam.getCandidates().remove(candidate);
			examDAO.update(exam);
		}
	}
	
	
}
