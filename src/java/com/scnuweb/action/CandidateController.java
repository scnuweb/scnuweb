package com.scnuweb.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.scnuweb.service.UserService;

@Controller
@RequestMapping("candidate")
public class CandidateController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("index")
	public String candidate(ModelMap modelMap,HttpServletRequest request) {
		return "candidate";
	}
	
	@RequestMapping("logout")
	public String logout(ModelMap modelMap,HttpServletRequest request) {
		request.getSession(true).setAttribute("currentUser", null);
		return "login";
	}
}
