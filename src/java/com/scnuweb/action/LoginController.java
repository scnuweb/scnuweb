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
import com.scnuweb.util.StaticVar;


@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/*")
	public String index(ModelMap modelMap,HttpServletRequest request) {
		return "redirect:/login.html";
	}
	@RequestMapping("login")
	public String login(ModelMap modelMap,HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("currentUser");
		if(user==null) return "login";
		else if(user.getUserType()==StaticVar.USER_TYPE_ADMIN)return "redirect:/admin/index.html";
		else return "redirect:/candidate/index.html";
	}
	@RequestMapping("loginSubmit")
	public String loginSubmit(ModelMap modelMap,HttpServletRequest request,
			String username,String password,String userType) {
		User user = userService.checkLogin(username, password,userType);
		if(user!=null) {
			request.getSession(true).setAttribute("currentUser", user);
			if(user.getUserType()==StaticVar.USER_TYPE_ADMIN) return "redirect:/admin/index.html";
			else return "redirect:/candidate/index.html";
		}
		modelMap.put("incorrect", true);
		return "login";
	}
}
