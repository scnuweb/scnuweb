package com.scnuweb.dao.impl;

import org.springframework.stereotype.Repository;

import com.scnuweb.dao.ExamGradeDAO;
import com.scnuweb.entity.ExamGrade;

@Repository
public class ExamGradeDAOImpl extends BaseDAOImpl<ExamGrade> implements ExamGradeDAO{

	@Override
	protected Class<ExamGrade> getEntityClass() {
		// TODO Auto-generated method stub
		return ExamGrade.class;
	}

}
