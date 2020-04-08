package com.ferme.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferme.entities.UserEntity;
import com.ferme.repositories.UserRepository;

/**
 * 
 * @author victor Jaramillo
 * @date 2020-03-28
 *
 */
@Service
public class UserService {
	
	private Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository  repository;

	/**
	 * Método que retorna una lista con todos los usuarios registrados en la base de datos.
	 * @return response
	 */
	public List<UserEntity> getUsers(){
		List<UserEntity> response = new ArrayList<>();
		try {
			/**
			 * @tip ** Hibernate ** será nuestro framework de persistencia, será el encargado de transaccionar con la base de datos.
			 * 
			 * @comment con el uso de ["repository.findAll()"]
			 * le indicaremos a Hibernate, que nos traiga desde la base de datos, todos los usuarios 
			 * de la tabla a la cual estamos haciendo referencia en la Clase ** UserEntity **.
			 */
			response = (List<UserEntity>) repository.findAll();
		} catch (Exception e) {
			LOG.error("Error al buscar los usuarios", e.getMessage(), e);
		}
		return response;
	}
	
	public UserEntity searchUser(Long id) {
		Optional<UserEntity> response = null;
		try {
			response = repository.findById(id);
		} catch (Exception e) {
			LOG.error("Error al buscar el usuario, causa: {}", e.getMessage(), e);
		}
		return response.isPresent() ? response.get(): null;
	}
	
	public Object saveUser(UserEntity user, String message) {
		Map<String, Object> response = new LinkedHashMap<>();
		try {
			UserEntity us = repository.save(user);
			response.put("status", "Ok");
			response.put(message, us);
		} catch (Exception e) {
			response.put("status", "error");
			response.put("cause", e.getMessage());
			response.put("details", e);
			LOG.error("error al guardar el usuario, causa: {}", e.getMessage(), e);
		}
		return response;
	}
	
}
