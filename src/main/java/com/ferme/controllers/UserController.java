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

import com.ferme.services.UserRoleService;
import com.ferme.services.UserService;
import com.portafolio.util.entities.UserEntity;
import com.portafolio.util.entities.UserRoleEntity;
import com.portafolio.util.rest.client.ResponseUtil;

/**
 * 
 * @author Victor Jaramillo
 * @date 2020-03-28
 * @comment Este es el controlador de ejemplo para la creación de los futuros.
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/v1/users")
public class UserController {

	/**
	 * De esta manera se le indica a "Spring" que inyecte este servicio(bean), en la
	 * creación de este controlador.
	 */
	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;


	/**
	 * De esta manera se crean la rutas que tendrán los servicios.
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getUsers() {
		Object response = userService.getUsers();
		if (response != null) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseUtil.reponseUtil(response, HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> searchUser(@PathVariable(value = "id") Long id) {
		Object response = userService.searchUser(id);
		if (response != null) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseUtil.reponseUtil(response, HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> saveUser(@RequestBody UserEntity user) {
		Object response = userService.saveUser(user, "user_saved");

		if (response != null) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseUtil.reponseUtil(response, HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Object> updateUser(@RequestBody UserEntity user) {
		if (userService.searchUser(user.getId() != null ? user.getId() : 0l) != null) {
			Object response = userService.saveUser(user, "user_updated");

			if (response != null) {
				return ResponseEntity.ok(response);
			} else {
				return ResponseUtil.reponseUtil(response, HttpStatus.NO_CONTENT);
			}
		} else {
			Map<String, Object> mapResponse = new LinkedHashMap<>();
			mapResponse.put("error", "usuario no existe");
			return ResponseUtil.reponseUtil(mapResponse, HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Object> disableUser(@PathVariable(value = "id") Long id) {
		Map<String, Object> response = userService.disableUser(id);

		if (response != null) {
			Map<String, Object> mapResponse = new LinkedHashMap<>();
			if (response.containsKey("user")) {
				mapResponse.put("message", "usuario deshabilitado");
				mapResponse.put("user", response.get("user"));
				return ResponseUtil.reponseUtil(mapResponse, HttpStatus.OK);
			} else {
				mapResponse.put("error", response.get("error"));
				return ResponseUtil.reponseUtil(mapResponse, HttpStatus.CONFLICT);
			}
		} else {
			return ResponseUtil.reponseUtil(response, HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestHeader(required = true) HttpHeaders httpHeaders) {
		Object login = userService.fermeLogin(httpHeaders);
		if (login != null) {
			return ResponseUtil.reponseUtil(login, HttpStatus.OK);
		} else {
			Map<String, Object> response = new LinkedHashMap<>();
			response.put("status", "error");
			response.put("message", "user or password not exist");
			return ResponseUtil.reponseUtil(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/{userId}/roles", method = RequestMethod.GET)
	public ResponseEntity<Object> getUserRolesByUserId(@PathVariable(value = "userId") Long userId) {
		Object response = userRoleService.getUserRolesByUserId(userId);
		if (response != null) {
			return ResponseUtil.reponseUtil(response, HttpStatus.OK);
		} else {
			return ResponseUtil.reponseUtil(response, HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ResponseEntity<Object> getUserRoles() {
		Object response = userRoleService.getUserRoles();
		if (response != null) {
			return ResponseUtil.reponseUtil(response, HttpStatus.OK);
		} else {
			return ResponseUtil.reponseUtil(response, HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/roles/save-user-role", method = RequestMethod.POST)
	public ResponseEntity<Object> saveUserRole(@RequestBody UserRoleEntity userRole) {
		Object response = userRoleService.saveUserRole(userRole);
		if (response != null) {
			return ResponseUtil.reponseUtil(response, HttpStatus.OK);
		} else {
			return ResponseUtil.reponseUtil(response, HttpStatus.NO_CONTENT);
		}
	}

}
