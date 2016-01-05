package com.scnuweb.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.scnuweb.entity.User;
import com.scnuweb.service.UserService;

public class InitRunner implements InitializingBean{
	@Autowired
	private UserService userService;
	private String adminUsername;
	private String adminPassword;
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		if(userService.getAllUsers().size()==0) {
			User user = new User();
			user.setUsername(adminUsername);
			user.setPassword(MD5.getMD5(adminPassword.getBytes()));
			user.setUserType(StaticVar.USER_TYPE_ADMIN);
			userService.addUser(user);
		}
	}
	public String getAdminUsername() {
		return adminUsername;
	}
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
}
