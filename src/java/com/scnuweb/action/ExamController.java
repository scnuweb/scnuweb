package com.scnuweb.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scnuweb.domain.Answer;
import com.scnuweb.domain.AnswerList;
import com.scnuweb.domain.Item;
import com.scnuweb.domain.ItemList;
import com.scnuweb.domain.SessionValue;
import com.scnuweb.entity.Exam;
import com.scnuweb.entity.ExamGrade;
import com.scnuweb.entity.ExamItem;
import com.scnuweb.entity.User;
import com.scnuweb.service.ExamGradeService;
import com.scnuweb.service.ExamItemService;
import com.scnuweb.service.ExamService;
import com.scnuweb.service.UserService;
import com.scnuweb.util.MyJson;

@Controller
@RequestMapping("exam")
public class ExamController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private ExamItemService examItemService;
	
	@Autowired
	private ExamGradeService examGradeService;
	
	@RequestMapping("show_exam_content")
	public String showExamContent(ModelMap modelMap,HttpServletRequest request,Long examItemId,Long examId) {
		HttpSession session = request.getSession(true);
		session.setAttribute("is_in_exam", null);
		User curUser = (User) request.getSession().getAttribute("currentUser");
		Exam exam = examService.getExamById(examId);
		session.setAttribute("cur_exam", exam);
		ExamGrade examGrade = examGradeService.getExamGrade(examId, curUser.getId());
		if(examGrade!=null) {
			AnswerList answerList = MyJson.fromJson(examGrade.getCandidateAnswer(), AnswerList.class);
			for(Answer answer:answerList.getAnswerList()) {
				if(answer.getExamItemId().equals(examItemId)) {
					modelMap.put("msg", "该题目已经提交！");
					modelMap.put("redirect_url", "../candidate/join_exam.do?examId="+examId);
					return "exam_item/exam_info";
				}
			}
		}
		modelMap.put("examItem", examItemService.getExamItemById(examItemId));
		return "exam_item/item_"+examItemId;
	}
	
	@RequestMapping("exit_exam")
	public String exitExam(ModelMap modelMap,HttpServletRequest request) {
		request.getSession(true).setAttribute("is_in_exam", null);
		return "redirect:/index";
	}
	
	@RequestMapping("show_exam_content_js")
	public String showExamContentJs(ModelMap modelMap,HttpServletRequest request,Long examItemId) {
		ExamItem examItem = examItemService.getExamItemById(examItemId);
		ItemList itemList = MyJson.fromJson(examItem.getQuestionStruct(), ItemList.class);
		List<Item> items = itemList.getItemList();
		modelMap.put("itemList", items);
		modelMap.put("examItem", examItem);
		return "exam_item/dynamic_js";
	}
	
	@RequestMapping("push_operation")
	public String pushOperation(ModelMap modelMap,HttpServletRequest request,String val,Long examItemId) {
		HttpSession session = request.getSession(false);
		Assert.notNull(session,"session can not be null");
		Exam exam = (Exam)session.getAttribute("cur_exam");
		Assert.notNull(exam,"exam can not be null");
		Long nowTime = System.currentTimeMillis();
		if(nowTime<exam.getStartTime().getTime()||nowTime>exam.getEndTime().getTime()) {
			modelMap.put("info", "fail");
			return "json";
		}
		synchronized (session) {
			if(session.getAttribute("is_in_exam")==null) {
				session.setAttribute("is_in_exam", true);
				session.setAttribute("operation_on_"+examItemId, null);
			}
			@SuppressWarnings("unchecked")
			List<String> tmpList = (List<String>)session.getAttribute("operation_on_"+examItemId);
			if(tmpList==null) {
				tmpList = new ArrayList<>();
			}
			tmpList.add(val);
			session.setAttribute("operation_on_"+examItemId,tmpList);
		}
//		System.out.println(session.getAttribute("operation_on_"+examItemId));
		modelMap.put("info", "success");
		return "json";
	}
	@RequestMapping("exam_item_submit")
	public String examItemSubmit(ModelMap modelMap,HttpServletRequest request,Long examItemId,Long examId) {
		User curUser = (User) request.getSession().getAttribute("currentUser");
		Exam exam = examService.getExamById(examId);
		ExamItem examItem = examItemService.getExamItemById(examItemId);
		HttpSession session = request.getSession(false);
		Assert.notNull(session,"session can not be null");
		@SuppressWarnings("unchecked")
		List<String> tmpList = (List<String>)session.getAttribute("operation_on_"+examItemId);
		if(tmpList==null) {
			tmpList = new ArrayList<>();
		}
		boolean isSuccess = examGradeService.submitAnswer(curUser, exam, examItem, tmpList);
		request.getSession(false).setAttribute("operation_on_"+examItemId,null);
		modelMap.put("info", isSuccess?"true":"false");
		return "json";
	}
}
