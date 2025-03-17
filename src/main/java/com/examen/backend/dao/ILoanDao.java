package com.examen.backend.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.examen.backend.entity.Loan;

public interface ILoanDao extends JpaRepository<Loan, Long>{

	@Query ("SELECT l FROM Loan l WHERE l.user.id = :user_id") 
    public Page<Loan> findAllWithUserId (Pageable pageable,  @Param("user_id") Long user_id);
    
}
