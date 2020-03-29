package com.ferme.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferme.entities.UserEntity;
import com.ferme.repositories.UserRepository;

@Service
public class UserService {
	
	private Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository  repository;

	public List<UserEntity> getUsers(){
		List<UserEntity> response = new ArrayList<>();
		try {
			response = (List<UserEntity>) repository.findAll();
		} catch (Exception e) {
			LOG.error("Error al buscar los usuarios", e.getMessage(), e);
		}
		return response;
	}

}
