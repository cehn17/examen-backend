package com.examen.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examen.backend.dao.IUserDao;
import com.examen.backend.entity.User;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDao userDao;

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return (List<User>) this.userDao.findAll();
	}
	
	@Transactional(readOnly=true)
	@Override
	public User findById(Long id) {
		return this.userDao.findById(id).orElse(null);
	}

	@Override
	public User save(User user) {
		return this.userDao.save(user);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		this.userDao.deleteById(id);
	}
	
	

	

}
