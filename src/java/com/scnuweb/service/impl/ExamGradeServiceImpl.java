package com.scnuweb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scnuweb.dao.ExamGradeDAO;
import com.scnuweb.domain.Answer;
import com.scnuweb.domain.AnswerItem;
import com.scnuweb.domain.AnswerList;
import com.scnuweb.domain.Button;
import com.scnuweb.domain.Input;
import com.scnuweb.domain.Item;
import com.scnuweb.domain.ItemList;
import com.scnuweb.domain.Select;
import com.scnuweb.entity.Exam;
import com.scnuweb.entity.ExamGrade;
import com.scnuweb.entity.ExamItem;
import com.scnuweb.entity.User;
import com.scnuweb.service.ExamGradeService;
import com.scnuweb.service.ExamService;
import com.scnuweb.service.UserService;
import com.scnuweb.util.MyJson;

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

	@Override
	public boolean submitAnswer(User user, Exam exam, ExamItem examItem,
			List<String> ans) {
		// TODO Auto-generated method stub
		user = userService.getUserById(user.getId());
		ExamGrade examGrade = getExamGrade(exam.getId(), user.getId());
		if(examGrade==null) {
			examGrade = new ExamGrade();
			examGrade.setCandidate(user);
			examGrade.setExam(exam);
		}
		AnswerList answerList = MyJson.fromJson(examGrade.getCandidateAnswer(), AnswerList.class);
		boolean isSubmitted = false;
		if(answerList!=null) {
			for(Answer answer:answerList.getAnswerList()) {
				if(answer.getExamItemId()==examItem.getId()) {
					isSubmitted=true;
					break;
				}
			}
		} else {
			answerList = new AnswerList();
		}
		if(!isSubmitted) {
			Answer answer = new Answer();
			answer.setExamItemId(examItem.getId());
			answer.setAnswer(MyJson.toJson(ans));
			int grade = calculateGrade(ans, examItem);
			answer.setGrade(grade);
			answer.setExamItemName(examItem.getExamItemName());
			answerList.getAnswerList().add(answer);
			if(answerList.getAnswerList().size()>=exam.getExamItems().size())examGrade.setIsSubmitted(1);
			examGrade.setCandidateAnswer(MyJson.toJson(answerList));
			saveOrUpdate(examGrade);
			return true;
		}
		return false;
	}
	private int calculateGrade(List<String> ans,ExamItem examItem) {
		Map<String,AnswerItem> answerItems = new HashMap<>();
		for(String ansItem:ans) {
			AnswerItem answerItem = MyJson.fromJson(ansItem, AnswerItem.class);
			answerItems.put(answerItem.getId(),answerItem);
		}
		ItemList itemList = MyJson.fromJson(examItem.getQuestionStruct(),ItemList.class);
		int correctCnt = 0;
		int totalCnt = 0;
		for(Item item:itemList.getItemList()) {
			Button button = null;
			Input input = null;
			Select select = null;
			totalCnt++;
			if(item.getType().equals(Item.ITEM_BUTTON)) {
				button = item.getButton();
				if(answerItems.get(button.getItemId())==null)continue;
				AnswerItem tmp = (AnswerItem)answerItems.get(button.getItemId());
				if(!tmp.getType().equals(Item.ITEM_BUTTON)||tmp.getOrder()!=button.getOrderNumber())continue;
				correctCnt++;
			} else if(item.getType().equals(Item.ITEM_INPUT)) {
				input = item.getInput();
				if(answerItems.get(input.getItemId())==null)continue;
				AnswerItem tmp = (AnswerItem)answerItems.get(input.getItemId());
				if(!tmp.getType().equals(Item.ITEM_INPUT)||tmp.getOrder()!=input.getOrderNumber())continue;
				if(input.getIsValueSensitive()!=0) {
					if(input.getInputValue().equals(tmp.getValue()))correctCnt++;
				} else correctCnt++;
			} else {
				select = item.getSelect();
				if(answerItems.get(select.getItemId())==null)continue;
				AnswerItem tmp = (AnswerItem)answerItems.get(select.getItemId());
				if(!tmp.getType().equals(Item.ITEM_SELECT)||tmp.getOrder()!=select.getOrderNumber())continue;
				if(select.getIsValueSensitive()!=0) {
					if(select.getSelectValue().equals(tmp.getValue()))correctCnt++;
				} else correctCnt++;
			}
		}
		Float grade = correctCnt*1F/totalCnt * 100;
		return grade.intValue();
	}

	@Override
	public List<Long> getSubmittedList(ExamGrade examGrade) {
		// TODO Auto-generated method stub
		List<Long> retList = new ArrayList<>();
		AnswerList answerList = MyJson.fromJson(examGrade.getCandidateAnswer(),AnswerList.class);
		if(answerList!=null) {
			for(Answer answer:answerList.getAnswerList()) {
				retList.add(answer.getExamItemId());
			}
		}
		return retList;
	}
}
