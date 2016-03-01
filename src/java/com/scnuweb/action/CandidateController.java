package com.scnuweb.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.scnuweb.entity.Exam;
import com.scnuweb.entity.ExamGrade;
import com.scnuweb.entity.ExamItem;
import com.scnuweb.entity.User;
import com.scnuweb.service.ExamGradeService;
import com.scnuweb.service.ExamService;
import com.scnuweb.service.UserService;
import com.scnuweb.util.MyJson;

@Controller
@RequestMapping("candidate")
public class CandidateController {
	@Autowired
	private UserService userService;
	@Autowired
	private ExamGradeService examGradeService;
	@Autowired
	private ExamService examService;

	private User currUser;

	@RequestMapping("index")
	public String candidate(ModelMap modelMap, HttpServletRequest request) {
		currUser = (User) request.getSession().getAttribute("currentUser");
		return "candidate";
	}

	@RequestMapping("logout")
	public String logout(ModelMap modelMap, HttpServletRequest request) {
		request.getSession(true).setAttribute("currentUser", null);
		return "redirect:/login";
	}

	@RequestMapping("candidate_assessment")
	public String candidateAssessment(ModelMap modelMap, HttpServletRequest request) {
		List<Exam> examList = examService.getExamByUser(currUser.getId());
		Collections.sort(examList, new Comparator<Exam>() {

			@Override
			public int compare(Exam e1, Exam e2) {
				Date t1 = e1.getEndTime();
				Date t2 = e2.getEndTime();
				if (t1.after(t2)) {
					return -1;
				}
				return 1;
			}
		});
		Date nowDate = new Date();
		modelMap.put("examList", examList);
		modelMap.put("nowDate", nowDate);
		return "candidate_assessment";
	}

	@RequestMapping("search_grade")
	public String searchGrade(ModelMap modelMap, HttpServletRequest request, long examId) {
		ExamGrade examGrade = examGradeService.getExamGrade(examId, currUser.getId());
		modelMap.put("user", currUser);
		if (examGrade != null) {
			modelMap.put("examGrade", examGrade);
			modelMap.put("exam", examGrade.getExam());
		}
		return "search_grade";

	}

	@RequestMapping("join_exam")
	public String joinExam(ModelMap modelMap, HttpServletRequest request, long examId) {
		User candidate = (User) request.getSession().getAttribute("currentUser");
		if(candidate==null) {
			return "redirect:/login";
		}
		candidate = userService.getUserById(candidate.getId());
		List<Exam> examList = candidate.getExams();
		Exam exam = examService.getExamById(examId);
		if(exam==null||!examList.contains(exam)) {
			return "redirect:/index";
		}
		List<ExamItem> examItemList = exam.getExamItems();
		modelMap.put("examItemList", examItemList);
		modelMap.put("exam", exam);
		return "join_exam";
	}
	
}
