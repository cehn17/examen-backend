package com.examen.backend.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examen.backend.ExamenBackendApplication;
import com.examen.backend.dao.IUserDao;
import com.examen.backend.entity.User;

@Service
public class UserServiceImpl implements IUserService {
	
	private static final Logger logger = LoggerFactory.getLogger(ExamenBackendApplication.class);
	
	@Autowired
	private IUserDao userDao;

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		logger.info("INFO - Buscando la lista de Users en la BBDD en la capa del Service");
		return (List<User>) this.userDao.findAll();
	}
	
	@Transactional(readOnly=true)
	@Override
	public User findById(Long id) {
		logger.info("INFO - Buscando al User con ID: ".concat(id.toString()).concat(" en la BBDD en la capa del Service"));
		return this.userDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public User save(User user) {
		logger.info("INFO - Persistiendo al User con Nombre: ".concat(user.getFirstName())
				.concat(", apellido: ").concat(user.getLastName())
				.concat(", Email: ").concat(user.getEmail())
				.concat(" en la BBDD en la capa del Service"));
		return this.userDao.save(user);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		logger.info("INFO - Eliminando al User con ID: ".concat(id.toString()).concat(" de la BBDD en la capa del Service"));
		this.userDao.deleteById(id);
	}

}
