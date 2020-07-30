package com.examen.backend.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;

import com.examen.backend.ExamenBackendApplication;
import com.examen.backend.entity.Loan;
import com.examen.backend.services.ILoanService;
import com.examen.backend.utils.Paging;

@RestController
@RequestMapping("/")
public class LoanRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExamenBackendApplication.class);
	
	@Autowired
	private ILoanService loanService;
	
	
	@GetMapping("/loans")
	public ResponseEntity<?> findAll(@RequestParam Integer page, @RequestParam Integer size, @RequestParam(required = false) Long user_id){
		logger.info("INFO - Buscando la lista de Loans en la BBDD, Page: " + page + ", Size: " + size
				+" y User id: " + user_id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(page < 0 || size < 0 ) {
			logger.error("Error - los parametros ingresados no pueden ser menor a 0");
			response.put("mensaje", "Error - los parametros ingresados no pueden ser menor a 0");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
		}
		
		if(user_id == null)
			return this.findAll(this.loanService.findAll(PageRequest.of(page, size)));
		else
			return this.findAll(this.loanService.findAll(PageRequest.of(page, size),user_id));
	}
	
	private ResponseEntity<?> findAll(Page<Loan> pageMethod){
		Page<Loan> pageloans = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			pageloans = pageMethod;
			
		}catch(DataAccessException e) {
			logger.error("Error - Error al realizar la consulta en la base de datos");
			logger.error("Error - "+e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		List<Loan> loans = new ArrayList<>();
		pageloans.getContent().forEach(l -> loans.add(l));
		Paging paging = new Paging(pageMethod.getNumber(), pageMethod.getSize(), pageloans.getTotalPages());
		response.put("items", loans);
		response.put("paging", paging);
		
		logger.info("INFO - Lista de Loans cargada con ÉXITO");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
