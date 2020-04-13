package com.ferme.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ferme.services.UserService;
import com.portafolio.util.entities.UserEntity;

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
	
	@RequestMapping(value = "/{id}/disable",method = RequestMethod.PATCH)
	public ResponseEntity<Object> disableUser(@PathVariable(value = "id") Long id) {
		Map<String, Object> response = service.disableUser(id);
		
		if (response != null) {
			Map<String, Object> mapResponse = new LinkedHashMap<>();
			if(response.containsKey("user")) {
				mapResponse.put("message", "usuario deshabilitado");
				mapResponse.put("user", response.get("user"));
				return reponseUtil(mapResponse, HttpStatus.OK);
			}else {
				mapResponse.put("error", response.get("error"));
				return reponseUtil(mapResponse, HttpStatus.CONFLICT);
			}
		} else {
			return reponseUtil(response, HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public Map<String, String> login(@RequestHeader HttpHeaders httpHeaders) {
		return null;
	}
	
	private ResponseEntity<Object> reponseUtil(Object response, HttpStatus httpStatus) {
		return ResponseEntity.status(httpStatus).body(response);
	}
	

}
