package com.scnuweb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.scnuweb.dao.UserDAO;
import com.scnuweb.entity.User;
import com.scnuweb.service.UserService;
import com.scnuweb.util.MD5;
import com.scnuweb.util.StaticVar;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

	private UserDAO userDAO;
	
	@Override
	@Autowired
	public void setBaseServiceDAO(UserDAO dao) {
		// TODO Auto-generated method stub
		super.setBaseDAO(dao);
		this.userDAO = dao;
	}

	@Override
	public User checkLogin(String username, String password,String userType) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", MD5.getMD5(password.getBytes()));
		map.put("userType", Integer.parseInt(userType));
		User user = userDAO.getObject(map);
		return user;
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		userDAO.save(user);
		return user;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDAO.update(user);
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		userDAO.del(id);
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return userDAO.get(id);
	}

	@Override
	public List<User> getUsersByParams(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return userDAO.getObjectList(params);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userDAO.getObjectList(new HashMap<String,Object>());
	}

	@Override
	public List<User> getAllCandidates() {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userType", StaticVar.USER_TYPE_CANDIDATE);
		List<User> retList = userDAO.getObjectList(params);
		return retList;
	}

	@Override
	public void deleteUsers(String deleteIdList) {
		// TODO Auto-generated method stub
		String[] list = deleteIdList.split(",");
		for(String listItem:list) {
			Long deleteId = Long.parseLong(listItem);
			User user = userDAO.get(deleteId);
			if(user!=null)userDAO.del(user);
		}
	}

	@Override
	public List<User> getCandidateListItem(User search) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<>();	
		String hql = "select new User(user.id,user.username,user.trueName,user.no,user.position,user.unit) from User user where user.userType=:userType";
		params.put("userType", StaticVar.USER_TYPE_CANDIDATE);
		if(search.getNo()!=null&&search.getNo().trim().length()>0) {
			hql+=" and user.no=:no";
			params.put("no", search.getNo().trim());
		}
		if(search.getUsername()!=null&&search.getUsername().trim().length()>0) {
			hql +=" and user.username=:username";
			params.put("username", search.getUsername().trim());
		}
		if(search.getTrueName()!=null&&search.getTrueName().trim().length()>0) {
			hql+=" and user.trueName=:trueName";
			params.put("trueName", search.getTrueName().trim());
		}
		if(search.getUnit()!=null&&search.getUnit().trim().length()>0) {
			hql+=" and user.unit=:unit";
			params.put("unit", search.getUnit().trim());
		}
		return userDAO.getByHQL(hql, params);
	}

}
