package com.scnuweb.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scnuweb.entity.Exam;
import com.scnuweb.entity.ExamItem;
import com.scnuweb.entity.User;
import com.scnuweb.service.ExamService;
import com.scnuweb.service.UserService;

@Controller
@RequestMapping("exam")
public class ExamController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ExamService examService;
	
	
	@RequestMapping("show_exam_content")
	public String showExamContent(ModelMap modelMap,HttpServletRequest request,Long examItemId) {
		return "exam_item/item_"+examItemId;
	}
}
