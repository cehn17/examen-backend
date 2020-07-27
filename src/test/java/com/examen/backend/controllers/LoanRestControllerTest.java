package com.examen.backend.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import com.examen.backend.entity.Loan;
import com.examen.backend.entity.User;
import com.examen.backend.services.ILoanService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = LoanRestController.class)
class LoanRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ILoanService loanService;

	@InjectMocks
	@Autowired
	private LoanRestController controller;

	@Mock
	BindingResult result;

	private List<Loan> loanList;
	
	@BeforeEach
	void setUp() {
		this.loanList = new ArrayList<>();
		this.loanList.add(new Loan(1L,2500.0,new User(1L, "user1@gmail.com", "Name1", "LastName1")));
		this.loanList.add(new Loan(2L,2500.0,new User(2L, "user1@gmail.com", "Name1", "LastName1")));
		this.loanList.add(new Loan(3L,2500.0,new User(1L, "user1@gmail.com", "Name1", "LastName1")));
	}
	
	@DisplayName("Test Find all loans - OK")
	@Test
	void TestFindAllUsersOK() throws Exception {

		when(loanService.findAll()).thenReturn(loanList);

		this.mockMvc.perform(get("/loans")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(loanList.size())));
	}
	
	@DisplayName("Test Find all loans by Page, Size - OK")
	@Test
	void TestFindAllUsersPageSizeOK() throws Exception {
//		Integer page = 0, size = 1;
		PageRequest pageRequest = PageRequest.of(0, 1);
		List<Loan> lista = new ArrayList<>();
		lista.add(loanList.get(0));
		Page<Loan> page = new PageImpl<>(lista, pageRequest, lista.size());
		
		when(loanService.findAll(PageRequest.of(0, 1))).thenReturn(page);

		this.mockMvc.perform(get("/loans/{page}/{size}", 0, 1)).andExpect(status().isOk());
	}
	
	@DisplayName("Test Find all loans by Page, Size - BAD_REQUEST")
	@Test
	void TestFindAllUsersPageSizeBAD_REQUEST() throws Exception {
//		Integer page = 0, size = 1;
		PageRequest pageRequest = PageRequest.of(0, 1);
		List<Loan> lista = new ArrayList<>();
		lista.add(loanList.get(0));
		Page<Loan> page = new PageImpl<>(lista, pageRequest, lista.size());
		
		when(loanService.findAll(PageRequest.of(0, 1))).thenReturn(page);

		this.mockMvc.perform(get("/loans/{page}/{size}", -10, 1)).andExpect(status().isBadRequest());
	}
	
	@DisplayName("Test Find all loans by Page, Size, User id - OK")
	@Test
	void TestFindAllUsersPageSizeUserIdOK() throws Exception {
//		Integer page = 0, size = 1;
		PageRequest pageRequest = PageRequest.of(0, 1);
		List<Loan> lista = new ArrayList<>();
		lista.add(loanList.get(0));
		Page<Loan> page = new PageImpl<>(lista, pageRequest, lista.size());
		
		when(loanService.findAll(PageRequest.of(0, 1),1L)).thenReturn(page);

		this.mockMvc.perform(get("/loans/{page}/{size}/{user_id}", 0, 1,1L)).andExpect(status().isOk());
	}
	
	@DisplayName("Test Find all loans by Page, Size, User id - BAD_REQUEST")
	@Test
	void TestFindAllUsersPageSizeUserIdBAD_REQUEST() throws Exception {
//		Integer page = 0, size = 1;
		PageRequest pageRequest = PageRequest.of(0, 1);
		List<Loan> lista = new ArrayList<>();
		lista.add(loanList.get(0));
		Page<Loan> page = new PageImpl<>(lista, pageRequest, lista.size());
		
		when(loanService.findAll(PageRequest.of(0, 1),1L)).thenReturn(page);

		this.mockMvc.perform(get("/loans/{page}/{size}/{user_id}", -10, 1,1L)).andExpect(status().isBadRequest());
	}
	

}
