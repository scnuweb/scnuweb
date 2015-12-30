package com.scnuweb.dao.impl;

import org.springframework.stereotype.Repository;

import com.scnuweb.dao.UserDAO;
import com.scnuweb.entity.User;

@Repository
public class UserDAOImpl extends BaseDAOImpl<User> implements UserDAO{

	@Override
	protected Class<User> getEntityClass() {
		// TODO Auto-generated method stub
		return User.class;
	}

}
