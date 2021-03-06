package com.examen.backend.services;

import java.util.List;

import com.examen.backend.entity.User;

public interface IUserService {
	
	public List<User> findAll();
	
	public User findById(Long id);
	
	public User save(User user);
	
	public void delete(Long id);

}
