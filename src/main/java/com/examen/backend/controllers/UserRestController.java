package com.examen.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.examen.backend.entity.User;
import com.examen.backend.services.IUserService;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/users")
	public List<User> findAll(){
		return this.userService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User findById(@PathVariable Long id){
		return this.userService.findById(id);
	}
	
	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public User save(@RequestBody User user) {
		return this.userService.save(user);
	}
	
	@DeleteMapping("/users/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		this.userService.delete(id);
	}

}
