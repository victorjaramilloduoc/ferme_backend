package com.ferme.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.ferme.services.SupplierService;
import com.portafolio.util.rest.client.ResponseUtil;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/api/v1/suppliers")
public class SupplierController {

    @Autowired
	private SupplierService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getSuppliers() {
		Object response = service.getSuppliers();
		if(response != null) {
			return ResponseUtil.reponseUtil(response, HttpStatus.OK);
		}else {
			return ResponseUtil.reponseUtil(response, HttpStatus.NO_CONTENT);
		}
	}
    
}