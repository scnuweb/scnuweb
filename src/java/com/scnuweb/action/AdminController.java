package com.scnuweb.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scnuweb.entity.Exam;
import com.scnuweb.entity.ExamItem;
import com.scnuweb.entity.User;
import com.scnuweb.service.ExamItemService;
import com.scnuweb.service.ExamService;
import com.scnuweb.service.UserService;
import com.scnuweb.util.JsonRetObj;
import com.scnuweb.util.MD5;
import com.scnuweb.util.MyJson;
import com.scnuweb.util.StaticVar;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private UserService userService;
	@Autowired
	private ExamService examService;
	@Autowired
	private ExamItemService examItemService;
	
	@RequestMapping("index")
	public String admin(ModelMap modelMap,HttpServletRequest request) {
		return "admin";
	}
	@RequestMapping("candidate_manage")
	public String candidateManage(ModelMap modelMap,HttpServletRequest request,User search) {
		List<User> candidateList = null;
		if(search!=null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userType", StaticVar.USER_TYPE_CANDIDATE);
			if(search.getNo()!=null&&search.getNo().trim().length()>0)map.put("no", search.getNo().trim());
			if(search.getUsername()!=null&&search.getUsername().trim().length()>0)map.put("username", search.getUsername().trim());
			if(search.getTrueName()!=null&&search.getTrueName().trim().length()>0)map.put("trueName", search.getTrueName().trim());
			if(search.getUnit()!=null&&search.getUnit().trim().length()>0)map.put("unit", search.getUnit().trim());
			modelMap.put("search", search);
			candidateList = userService.getUsersByParams(map);
		} else {
			candidateList = userService.getAllCandidates();
			modelMap.put("search", new User());
		}
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
	
	@RequestMapping("delete_candidate")
	public String deleteCandidate(ModelMap modelMap,HttpServletRequest request,Long userId) {
		userService.deleteUser(userId);
		return "redirect:/admin/candidate_manage.html";
	}
	
	@RequestMapping("check_is_username_valid")
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
	
	@RequestMapping("delete_candidates")
	public String deleteCandidates(ModelMap modelMap,HttpServletRequest request,String deleteList) {
		userService.deleteUsers(deleteList);
		modelMap.put("info", "true");
		return "json";
	}
	
	@RequestMapping("exam_manage")
	public String examManage(ModelMap modelMap,HttpServletRequest request) {
		List<Exam> examList = examService.getAllExams();
		Date nowDate = new Date();
		modelMap.put("examList", examList);
		modelMap.put("nowDate", nowDate);
		return "exam_manage";
	}
	
	@RequestMapping("add_exam")
	public String addExam(ModelMap modelMap,HttpServletRequest request,User search) {
		List<User> candidateList = null;
		if(search!=null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userType", StaticVar.USER_TYPE_CANDIDATE);
			if(search.getNo()!=null&&search.getNo().trim().length()>0)map.put("no", search.getNo().trim());
			if(search.getUsername()!=null&&search.getUsername().trim().length()>0)map.put("username", search.getUsername().trim());
			if(search.getTrueName()!=null&&search.getTrueName().trim().length()>0)map.put("trueName", search.getTrueName().trim());
			if(search.getUnit()!=null&&search.getUnit().trim().length()>0)map.put("unit", search.getUnit().trim());
			modelMap.put("search", search);
			candidateList = userService.getUsersByParams(map);
		} else {
			candidateList = userService.getAllCandidates();
			modelMap.put("search", new User());
		}
		modelMap.put("candidateList", candidateList);
		return "add_exam";
	}
	
	@RequestMapping("add_exam_submit")
	public String addExamSubmit(ModelMap modelMap,HttpServletRequest request,String examName,String startTime,String endTime) {
		boolean flag = true;
		JsonRetObj retObj = new JsonRetObj();
		if(examName==null||examName.length()<1)flag = false;
		if(startTime==null||startTime.length()<1)flag = false;
		if(endTime==null||endTime.length()<1)flag = false;
		if(!flag) {
			retObj.setStatus(false);
			retObj.setInfo("请完成必填项");
		} else {
			Exam exam = new Exam();
			exam.setExamName(examName);
			try {
				SimpleDateFormat format = new SimpleDateFormat(StaticVar.UNIVERSIAL_TIME_FORMAT);
				Date startDate = format.parse(startTime);
				Date endDate = format.parse(endTime);
				if(startDate.getTime()>=endDate.getTime()) {
					retObj.setStatus(false);
					retObj.setInfo("请填写有效的开始和结束时间");
				} else {
					exam.setStartTime(startDate);
					exam.setEndTime(endDate);
					examService.addExam(exam);
					retObj.setStatus(true);
					retObj.setInfo("保存成功");
					retObj.setRetId(exam.getId());
				}
			} catch (Exception e) {
				// TODO: handle exception
				retObj.setStatus(false);
				retObj.setInfo("请填写有效的时间");
			}
		}
		modelMap.put("info", MyJson.toJson(retObj));
		return "json";
	}
	
	@RequestMapping("edit_exam_submit")
	public String editExamSubmit(ModelMap modelMap,HttpServletRequest request,Long examId,String examName,String startTime,String endTime) {
		boolean flag = true;
		JsonRetObj retObj = new JsonRetObj();
		if(examName==null||examName.length()<1)flag = false;
		if(startTime==null||startTime.length()<1)flag = false;
		if(endTime==null||endTime.length()<1)flag = false;
		if(!flag) {
			retObj.setStatus(false);
			retObj.setInfo("请完成必填项");
		} else {
			Exam exam = examService.getExamById(examId);
			exam.setExamName(examName);
			try {
				SimpleDateFormat format = new SimpleDateFormat(StaticVar.UNIVERSIAL_TIME_FORMAT);
				Date startDate = format.parse(startTime);
				Date endDate = format.parse(endTime);
				if(startDate.getTime()>=endDate.getTime()) {
					retObj.setStatus(false);
					retObj.setInfo("请填写有效的开始和结束时间");
				} else {
					exam.setStartTime(startDate);
					exam.setEndTime(endDate);
					examService.updateExam(exam);
					retObj.setStatus(true);
					retObj.setInfo("保存成功");
					retObj.setRetId(exam.getId());
				}
			} catch (Exception e) {
				// TODO: handle exception
				retObj.setStatus(false);
				retObj.setInfo("请填写有效的时间");
			}
		}
		modelMap.put("info", MyJson.toJson(retObj));
		return "json";
	}
	
	@RequestMapping("edit_exam")
	public String editExam(ModelMap modelMap,HttpServletRequest request,Long examId,User search) {
		List<User> candidateList = null;
		if(search!=null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userType", StaticVar.USER_TYPE_CANDIDATE);
			if(search.getNo()!=null&&search.getNo().trim().length()>0)map.put("no", search.getNo().trim());
			if(search.getUsername()!=null&&search.getUsername().trim().length()>0)map.put("username", search.getUsername().trim());
			if(search.getTrueName()!=null&&search.getTrueName().trim().length()>0)map.put("trueName", search.getTrueName().trim());
			if(search.getUnit()!=null&&search.getUnit().trim().length()>0)map.put("unit", search.getUnit().trim());
			modelMap.put("search", search);
			candidateList = userService.getUsersByParams(map);
		} else {
			candidateList = userService.getAllCandidates();
			modelMap.put("search", new User());
		}
		Exam exam = examService.getExamById(examId);
		modelMap.put("candidateList", candidateList);
		modelMap.put("exam", exam);
		return "edit_exam";
	}
	
	@RequestMapping("delete_exam")
	public String deleteExam(ModelMap modelMap,HttpServletRequest request,Long deleteId) {
		examService.delete(deleteId);
		return "redirect:/admin/exam_manage.html";
	}
	
	@RequestMapping("get_candidate_list")
	public String getCandidateList(ModelMap modelMap,HttpServletRequest request,User search) {
		List<User> retList = userService.getCandidateListItem(search);
		modelMap.put("info", MyJson.toJson(retList));
		return "json";
	}
	
	@RequestMapping("import_candidate_list")
	public String importCandidate(ModelMap modelMap,HttpServletRequest request,Long examId,String candidateList) {
		examService.importCandidate(examId, candidateList);
		modelMap.put("info", "true");
		return "json";
	}
	
	@RequestMapping("delete_candidate_from_exam")
	public String deleteCandidateFromExam(ModelMap modelMap,HttpServletRequest request,Long examId,Long candidateId) {
		examService.deleteCandidate(examId, candidateId);
		modelMap.put("info", "true");
		return "json";
	}
	
	@RequestMapping("get_exam_candidate_list")
	public String getExamCandidateList(ModelMap modelMap,HttpServletRequest request,Long examId) {
		Exam exam = examService.getExamById(examId);
		List<User> candiateList = exam.getCandidates();
		List<Long> retList = new ArrayList<>();
		for(User user:candiateList) {
			retList.add(user.getId());
		}
		modelMap.put("info", MyJson.toJson(retList));
		return "json";
	}
	
	@RequestMapping("logout")
	public String logout(ModelMap modelMap,HttpServletRequest request) {
		request.getSession(true).setAttribute("currentUser", null);
		return "login";
	}
}
