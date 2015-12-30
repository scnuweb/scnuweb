package com.scnuweb.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scnuweb.entity.User;
import com.scnuweb.service.UserService;


@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("login")
	public String login(ModelMap modelMap,HttpServletRequest request) {
		return "login";
	}
	@RequestMapping("loginSubmit")
	public String loginSubmit(ModelMap modelMap,HttpServletRequest request,
			String username,String password,String userType) {
		User user = userService.checkLogin(username, password,userType);
		if(user!=null) {
			request.getSession(true).setAttribute("currentUser", user);
			return "index";
		}
		modelMap.put("incorrect", true);
		return "login";
	}
	@RequestMapping("logout")
	public String logout(ModelMap modelMap,HttpServletRequest request) {
		request.getSession(true).setAttribute("currentUser", null);
		return "login";
	}
}
