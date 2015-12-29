package com.scnuweb.service;

import com.scnuweb.dao.BaseDAO;
import com.scnuweb.dao.impl.BaseDAOImpl;

public interface BaseService <T>{
	public void setBaseServiceDAO(T dao);
}
