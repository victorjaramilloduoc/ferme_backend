package com.ferme.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.ferme.repositories.UserRepository;
import com.portafolio.util.entities.UserEntity;
import com.portafolio.util.login.LoginUtil;

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
			response.forEach(x -> x.setPassword(LoginUtil.encodeBase64(x.getPassword())));
			
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
//			user.setPassword(LoginUtil.encodeBase64(user.getPassword()));
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
	
	public Map<String, Object> disableUser(Long id) {
		UserEntity us = null;
		Map<String, Object> responseMap = new LinkedHashMap<>();
		try {
			us = searchUser(id);
			if(us != null) {
				us.setEnable(false);
				us = repository.save(us);
				us.setPassword(LoginUtil.encodeBase64(us.getPassword()));
				responseMap.put("user", us);
			}else {
				responseMap.put("error", "el usuario no existe");
			}
		} catch (Exception e) {
			LOG.error("Error al desabilidar el usuario, causa: {}", e.getMessage(), e);
			responseMap.put("error", "Error al desabilidar el usuario");
			responseMap.put("cause", e.getMessage());
			responseMap.put("details", e);
		}
		return responseMap;
	}
	
	public Object fermeLogin(HttpHeaders httpHeaders) {
		Map<String, String> credentialsMap = LoginUtil.getCredentialsOfHeader(httpHeaders);
		Map<String, Object> response = new LinkedHashMap<>();
		UserEntity user = repository.getUserByEmailAndPassword(credentialsMap.get("username"),
				credentialsMap.get("password"));
//		LoginUtil.encodeBase64(credentialsMap.get("password"))
		if(user != null) {
			credentialsMap.clear();
			credentialsMap.put("id", user.getId().toString());
			credentialsMap.put("name", user.getName());
			credentialsMap.put("lastName", user.getLastName());
			credentialsMap.put("email", user.getEmail());
			response.put("status", "Ok");
			response.put("welcome", credentialsMap);
			return response;
		}else {
			return null;
		}
	}
	
}
