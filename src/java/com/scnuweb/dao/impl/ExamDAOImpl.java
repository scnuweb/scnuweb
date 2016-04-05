package com.scnuweb.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scnuweb.dao.ExamDAO;
import com.scnuweb.entity.Exam;

@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ExamDAOImpl extends BaseDAOImpl<Exam> implements ExamDAO{

	@Override
	protected Class<Exam> getEntityClass() {
		// TODO Auto-generated method stub
		return Exam.class;
	}

}
