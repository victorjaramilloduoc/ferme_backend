package com.ferme.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Object> searchUser(@PathVariable(value = "id") Long id){
		Object response = service.searchUser(id);
		if(response != null) {
			return ResponseEntity.ok(response);
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}
	}

}
