package com.scnuweb.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scnuweb.dao.UserDAO;
import com.scnuweb.entity.User;
import com.scnuweb.service.UserService;

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
		map.put("password", password);
		map.put("userType", Integer.parseInt(userType));
		User user = userDAO.getObject(map);
		return user;
	}

}
