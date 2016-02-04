package com.scnuweb.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.scnuweb.entity.Exam;
import com.scnuweb.entity.ExamGrade;
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
		Date nowDate = new Date();
		modelMap.put("examList", examList);
		modelMap.put("nowDate", nowDate);
		return "candidate_assessment";
	}

	@RequestMapping("search_grade")
	public String searchGrade(ModelMap modelMap, HttpServletRequest request) {
	//	List<ExamGrade> gradeList = examGradeService.getExamGradeById(examGradeId);
		return "search_grade";

	}

}
