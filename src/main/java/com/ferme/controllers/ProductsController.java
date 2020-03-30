package com.ferme.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ferme.entities.ProductsEntity;
import com.ferme.services.ProductsService;

/**
 * 
 * @author Victor Jaramillo
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/api/v1/products")
public class ProductsController {
	
	@Autowired
	private ProductsService service;
	
	/**
	 * De esta manera se crean la rutas que tendrán los servicios.
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Object> getProducts() {
		return ResponseEntity.ok(service.getProducts());
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Object> saveProducts(@RequestBody ProductsEntity product) {
		Map<String, Object> response = new LinkedHashMap<>();
		
		boolean productSaved = service.saveProduct(product);
		
		if(productSaved) {
			response.put("products", productSaved);
			return ResponseEntity.ok(response);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

}
