package com.scnuweb.service;

import com.scnuweb.dao.UserDAO;
import com.scnuweb.entity.User;

public interface UserService extends BaseService<UserDAO>{
	public User checkLogin(String username,String password,String userType);
}
