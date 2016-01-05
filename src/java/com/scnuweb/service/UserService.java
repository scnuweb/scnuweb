package com.scnuweb.service;

import java.util.List;
import java.util.Map;

import com.scnuweb.dao.UserDAO;
import com.scnuweb.entity.User;

public interface UserService extends BaseService<UserDAO>{
	public User checkLogin(String username,String password,String userType);
	public User addUser(User user);
	public void updateUser(User user);
	public void deleteUser(Long id);
	public User getUserById(Long id);
	public List<User> getUsersByParams(Map<String, Object> params);
	public List<User> getAllUsers();
	public List<User> getAllCandidates();
}
