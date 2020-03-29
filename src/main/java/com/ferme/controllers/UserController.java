package com.ferme.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@RequestMapping(value="/")
	public ResponseEntity<Object> getUsers() {
		Map<String, Object> response = new LinkedHashMap<>();
		response.put("message", "estoy llegando");
		response.put("users", service.getUsers());
		return ResponseEntity.ok(response);
	}

}
