package com.examen.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.examen.backend.entity.User;

public interface IUserDao extends CrudRepository<User, Long>{

}
