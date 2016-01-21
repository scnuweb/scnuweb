package com.scnuweb.dao.impl;

import org.springframework.stereotype.Repository;

import com.scnuweb.dao.ExamItemDAO;
import com.scnuweb.entity.ExamItem;

@Repository
public class ExamItemDAOImpl extends BaseDAOImpl<ExamItem> implements ExamItemDAO{

	@Override
	protected Class<ExamItem> getEntityClass() {
		// TODO Auto-generated method stub
		return ExamItem.class;
	}

}
