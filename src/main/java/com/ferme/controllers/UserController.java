package com.ferme.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferme.services.UserService;

@RestController
@RequestMapping(value="/api/v1/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value="/")
	public ResponseEntity<Object> getUsers() {
		Map<String, Object> response = new LinkedHashMap<>();
		response.put("message", "estoy llegando");
		response.put("users", service.getUsers());
		return ResponseEntity.ok(response);
	}

}
