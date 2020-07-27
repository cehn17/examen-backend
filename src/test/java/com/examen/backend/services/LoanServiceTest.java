package com.examen.backend.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.examen.backend.dao.ILoanDao;
import com.examen.backend.entity.Loan;
import com.examen.backend.entity.User;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {
	
	@Mock
	private ILoanDao loanDao;
	
	@InjectMocks
	private LoanServiceImpl loanService;
	
//	@Spy
//	List<Loan> loans = new ArrayList<>();
//	
//	@Before
//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//        loans = getLoansList();
////        employees = getEmployeeList();
//    }
	
	@DisplayName("TEST Find All Loans OK")
	@Test
	public void testFindAllLoansOK() {
		List<Loan> loans = getLoansList();
		when(loanDao.findAll()).thenReturn(loans);
		
		List<Loan> expected = loanService.findAll();
		
		assertEquals(expected, loans);
		assertEquals(expected.size(), 8 );
	}
	
	@DisplayName("TEST Find All Loans OK PAGE Y SIZE")
	@Test
	public void testFindAllLoandPageSize() {
		PageRequest pageRequest = PageRequest.of(0, 8);
		Page<Loan> page = new PageImpl<>(getLoansList(), pageRequest, getLoansList().size());
		
		when(loanDao.findAll(Mockito.any(PageRequest.class))).thenReturn(page);
		
		Pageable p = PageRequest.of(0,8);
		Page<Loan> expected = loanService.findAll(p);
		
		assertEquals(expected, page);
	}
	
	@DisplayName("TEST Find All Loans OK PAGE, SIZE Y USER_ID")
	@Test
	public void testFindAllLoandPageSizeUser() {
		
		List<Loan> lista = new ArrayList<>();
		lista.add(new Loan(3L,5000.00,new User(2L, "test2@test.com","name2","lastName2")));
		lista.add(new Loan(5L,5000.00,new User(2L, "test2@test.com","name2","lastName2")));
		lista.add(new Loan(7L,5000.00,new User(2L, "test2@test.com","name2","lastName2")));
		
		PageRequest pageRequest = PageRequest.of(0, 8);
		Page<Loan> page = new PageImpl<>(lista, pageRequest, lista.size());
		
		when(loanDao.findAllWithUserId(Mockito.any(PageRequest.class),Mockito.anyLong())).thenReturn(page);
		
		Pageable p = PageRequest.of(0,8);
		Page<Loan> expected = loanService.findAll(p,2L);
		
		assertEquals(expected, page);
	}
	

	public List<Loan> getLoansList(){
		User user1 = new User(1L, "test1@test.com","name1","lastName1");
		User user2 = new User(2L, "test2@test.com","name2","lastName2");
		List<Loan> loans = new ArrayList<>();
		loans.add(new Loan(1L,5000.00,user1));
		loans.add(new Loan(2L,5000.00,user1));
		loans.add(new Loan(3L,5000.00,user2));
		loans.add(new Loan(4L,5000.00,user1));
		loans.add(new Loan(5L,5000.00,user2));
		loans.add(new Loan(6L,5000.00,user1));
		loans.add(new Loan(7L,5000.00,user2));
		loans.add(new Loan(8L,5000.00,user1));
		return loans;
    }

}
