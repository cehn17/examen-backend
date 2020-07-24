package com.examen.backend.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.backend.entity.User;
import com.examen.backend.services.IUserService;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<?> findAll(){
		List<User> users = this.userService.findAll();
		
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		User user = null;
		Map<String, Object> response = new HashMap<>();
		try {
			user = this.userService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		if(user == null) {
			response.put("mensaje", "El User ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> save(@Valid @RequestBody User user, BindingResult result) {
		User nuevo = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo " + err.getField() + " " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		try {
			nuevo = this.userService.save(user);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		response.put("mensaje", "El User ha sido creado con éxito");
		response.put("User", nuevo);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			this.userService.delete(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		response.put("mensaje", "El User ID: ".concat(id.toString().concat(" ha sido eliminado con éxito")));
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}

}
