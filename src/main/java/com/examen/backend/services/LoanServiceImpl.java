package com.examen.backend.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examen.backend.ExamenBackendApplication;
import com.examen.backend.dao.ILoanDao;
import com.examen.backend.entity.Loan;

@Service
public class LoanServiceImpl implements ILoanService{
	
	private static final Logger logger = LoggerFactory.getLogger(ExamenBackendApplication.class);

	@Autowired
	private ILoanDao loanDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Loan> findAll() {
		logger.info("INFO - Buscando la lista de Loans en la BBDD en la capa del Service");
		return this.loanDao.findAll(); 
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Loan> findAll(Pageable pageable) {
		logger.info("INFO - Buscando la lista de Loans en la BBDD en la capa del Service: Page: "
				.concat(""+pageable.getPageNumber()).concat(", Size: ")
				.concat(pageable.getPageSize()+""));
		return this.loanDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Loan> findAll(Pageable pageable, Long user_id) {
		logger.info("INFO - Buscando la lista de Loans en la BBDD en la capa del Service: Page: "
				.concat(""+pageable.getPageNumber()).concat(", Size: ")
				.concat(pageable.getPageSize()+"").concat(", User ID: ").concat(user_id.toString()));
		return this.loanDao.findAllWithUserId(pageable, user_id);
	}

	
}
