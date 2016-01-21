package com.scnuweb.dao.impl;

import org.springframework.stereotype.Repository;

import com.scnuweb.dao.ExamDAO;
import com.scnuweb.entity.Exam;

@Repository
public class ExamDAOImpl extends BaseDAOImpl<Exam> implements ExamDAO{

	@Override
	protected Class<Exam> getEntityClass() {
		// TODO Auto-generated method stub
		return Exam.class;
	}

}
