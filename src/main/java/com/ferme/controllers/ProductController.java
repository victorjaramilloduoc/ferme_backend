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

import com.ferme.services.ProductsService;
import com.portafolio.util.entities.ProductEntity;
import com.portafolio.util.rest.client.ResponseUtil;

/**
 * 
 * @author Victor Jaramillo
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/api/v1/products")
public class ProductController {
	
	@Autowired
	private ProductsService service;
	
	/**
	 * De esta manera se crean la rutas que tendrán los servicios.
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getProducts() {
		return ResponseUtil.reponseUtil(service.getProducts(), HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> saveProducts(@RequestBody ProductEntity product) {
		
		Object productSaved = service.saveProduct(product, "save_product");
		
		if( !((Map<String, Object>)productSaved).get("status").equals("error") ) {
			return ResponseUtil.reponseUtil(productSaved, HttpStatus.OK);
		}else {
			return ResponseUtil.reponseUtil(productSaved, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Object> searchProduct(@PathVariable(value = "id") Long id){
		ProductEntity response = service.searchProduct(id);
		
		if(response != null) {
			return ResponseUtil.reponseUtil(response, HttpStatus.OK);
		}else {
			Map<String, Object> resp = new LinkedHashMap<>();
			resp.put("message", "product not exist");
			return ResponseUtil.reponseUtil(response,HttpStatus.NO_CONTENT);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProducts(@RequestBody ProductEntity product) {
		
		if(service.searchProduct(product.getId() != null ? product.getId() : 0l) != null) {
			
			Object productSaved = service.saveProduct(product, "product_updated");
			
			if( !((Map<String, Object>)productSaved).get("status").equals("error") ) {
				return ResponseUtil.reponseUtil(productSaved, HttpStatus.OK);
			}else {
				return ResponseUtil.reponseUtil(productSaved, HttpStatus.BAD_REQUEST);
			}
		}else {
			Map<String, Object> mapResponse = new LinkedHashMap<>();
			mapResponse.put("error", "producto no existe");
			return ResponseUtil.reponseUtil(mapResponse, HttpStatus.CONFLICT);
		}
	}

}
