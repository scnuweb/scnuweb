package com.scnuweb.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scnuweb.dao.ExamGradeDAO;
import com.scnuweb.entity.ExamGrade;

@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ExamGradeDAOImpl extends BaseDAOImpl<ExamGrade> implements ExamGradeDAO{

	@Override
	protected Class<ExamGrade> getEntityClass() {
		// TODO Auto-generated method stub
		return ExamGrade.class;
	}

}
