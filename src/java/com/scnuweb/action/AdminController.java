package com.scnuweb.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scnuweb.entity.User;
import com.scnuweb.service.UserService;
import com.scnuweb.util.MD5;
import com.scnuweb.util.StaticVar;

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
	public String candidateManage(ModelMap modelMap,HttpServletRequest request,String searchType,
			String searchValue) {
		List<User> candidateList = null;
		if(searchType!=null&&searchValue!=null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userType", StaticVar.USER_TYPE_CANDIDATE);
			map.put(searchType, searchValue);
			candidateList = userService.getUsersByParams(map);
			modelMap.put("searchValue", searchValue);
			if(searchType.equals("no"))modelMap.put(searchType, 1);
			else modelMap.put(searchType, 1);
		} else candidateList = userService.getAllCandidates();
		modelMap.put("candidateList", candidateList);
		return "candidate_manage";
	}
	
	@RequestMapping("add_candidate")
	public String addCandidate(ModelMap modelMap) {
		return "add_candidate";
	}
	
	@RequestMapping("add_candidate_submit")
	public String addCandidateSubmit(ModelMap modelMap,User user) {
		user.setUserType(StaticVar.USER_TYPE_CANDIDATE);
		user.setPassword(MD5.getMD5(user.getPassword().getBytes()));
		userService.addUser(user);
		return "redirect:/admin/candidate_manage.html";
	}
	
	@RequestMapping("edit_candidate")
	public String updateCandidate(ModelMap modelMap,Long userId) {
		User user = userService.getUserById(userId);
		modelMap.put("candidate", user);
		return "edit_candidate";
	}
	
	@RequestMapping("edit_candidate_submit")
	public String updateCandidateSubmit(ModelMap modelMap,User user) {
		if(user.getPassword()==null||user.getPassword().trim().length()<1) {
			User oringinalUser = userService.getUserById(user.getId());
			user.setPassword(oringinalUser.getPassword());
		} else user.setPassword(MD5.getMD5(user.getPassword().getBytes()));
		user.setUserType(StaticVar.USER_TYPE_CANDIDATE);
		userService.updateUser(user);
		return "redirect:/admin/candidate_manage.html";
	}
	
	@RequestMapping("deleteCandidate")
	public String deleteCandidate(ModelMap modelMap,HttpServletRequest request,Long userId) {
		userService.deleteUser(userId);
		return "redirect:/admin/candidate_manage.html";
	}
	
	@RequestMapping("checkIsUsernameValid")
	public String checkIsUsernameValid(ModelMap modelMap,HttpServletRequest request,String username) {
		String ret = null;
		if(username==null||username.trim().length()<4||username.trim().length()>16)ret = "false";
		else {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("username", username);
			if((userService.getUsersByParams(paramMap)).size()>0)ret = "false";
			else ret = "true";
		}
		modelMap.put("info", ret);
		return "json";
	}
	@RequestMapping("logout")
	public String logout(ModelMap modelMap,HttpServletRequest request) {
		request.getSession(true).setAttribute("currentUser", null);
		return "login";
	}
}
