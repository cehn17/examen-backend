package com.examen.backend.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.examen.backend.entity.Loan;

public interface ILoanService {

	public List<Loan> findAll();
	
	public Page<Loan> findAll(Pageable pageable);
	
	public Page<Loan> findAll(Pageable pageable, Long user_id);
}
