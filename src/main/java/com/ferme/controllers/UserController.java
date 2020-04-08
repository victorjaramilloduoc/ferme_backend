package com.ferme.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ferme.entities.UserEntity;
import com.ferme.services.UserService;

/**
 * 
 * @author Victor Jaramillo
 * @date 2020-03-28
 * @comment Este es el controlador de ejemplo para la creación de los futuros.
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/api/v1/users")
public class UserController {
	
	/**
	 * De esta manera se le indica a "Spring" que inyecte este servicio(bean), en la creación de este controlador.
	 */
	@Autowired
	private UserService service;
	
	/**
	 * De esta manera se crean la rutas que tendrán los servicios.
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getUsers() {
		Object response = service.getUsers();
		if(response != null) {
			return ResponseEntity.ok(response);
		}else {
			return reponseUtil(response, HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Object> searchUser(@PathVariable(value = "id") Long id){
		Object response = service.searchUser(id);
		if(response != null) {
			return ResponseEntity.ok(response);
		}else {
			return reponseUtil(response, HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> saveUser(@RequestBody UserEntity user){
		Object response = service.saveUser(user, "user_saved");
		
		if(response != null) {
			return ResponseEntity.ok(response);
		}else {
			return reponseUtil(response, HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Object> updateUser(@RequestBody UserEntity user){
		if(service.searchUser(user.getId() != null ? user.getId() : 0l) != null) {
			Object response = service.saveUser(user, "user_updated");
			
			if(response != null) {
				return ResponseEntity.ok(response);
			}else {
				return reponseUtil(response, HttpStatus.NO_CONTENT);
			}
		}else {
			Map<String, Object> mapResponse = new LinkedHashMap<>();
			mapResponse.put("error", "usuario no existe");
			return reponseUtil(mapResponse, HttpStatus.CONFLICT);
		}
	}

	private ResponseEntity<Object> reponseUtil(Object response, HttpStatus httpStatus) {
		return ResponseEntity.status(httpStatus).body(response);
	}

}
