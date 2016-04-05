package com.scnuweb.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scnuweb.dao.ExamItemDAO;
import com.scnuweb.entity.ExamItem;

@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ExamItemDAOImpl extends BaseDAOImpl<ExamItem> implements ExamItemDAO{

	@Override
	protected Class<ExamItem> getEntityClass() {
		// TODO Auto-generated method stub
		return ExamItem.class;
	}

}
