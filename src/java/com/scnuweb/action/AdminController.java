package com.scnuweb.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scnuweb.service.UserService;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("index")
	public String admin(ModelMap modelMap,HttpServletRequest request) {
		return "admin";
	}
	@RequestMapping("candidate_manage")
	public String candidateManage(ModelMap modelMap,HttpServletRequest request) {
		return "candidate_manage";
	}
	@RequestMapping("logout")
	public String logout(ModelMap modelMap,HttpServletRequest request) {
		request.getSession(true).setAttribute("currentUser", null);
		return "login";
	}
}
