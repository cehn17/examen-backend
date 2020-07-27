package com.examen.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examen.backend.dao.ILoanDao;
import com.examen.backend.entity.Loan;

@Service
public class LoanServiceImpl implements ILoanService{

	@Autowired
	private ILoanDao loanDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Loan> findAll() {
		return this.loanDao.findAll(); 
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Loan> findAll(Pageable pageable) {
		return this.loanDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Loan> findAll(Pageable pageable, Long user_id) {
		return this.loanDao.findAllWithUserId(pageable, user_id);
	}

	
}
