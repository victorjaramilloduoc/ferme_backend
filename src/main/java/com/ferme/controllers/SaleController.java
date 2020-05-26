package com.ferme.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ferme.services.SaleService;
import com.portafolio.util.entities.SaleEntity;
import com.portafolio.util.rest.client.ResponseUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/api/v1/sales")
public class SaleController {
	
	@Autowired
	private SaleService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getSales() {
		Object response = service.getSales();
		if(response != null) {
			return ResponseUtil.reponseUtil(response, HttpStatus.OK);
		}else {
			return ResponseUtil.reponseUtil(response, HttpStatus.NO_CONTENT);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> recordedSale(@RequestBody SaleEntity sale) {
		Object saleResponse = service.recordSale(sale, "product_sale_saved");
		
		if( !((Map<String, Object>)saleResponse).get("status").equals("error") ) {
			return ResponseUtil.reponseUtil(saleResponse, HttpStatus.OK);
		}else {
			return ResponseUtil.reponseUtil(saleResponse, HttpStatus.BAD_REQUEST);
		}
	}
}
