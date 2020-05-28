package com.ferme.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ferme.services.PurchaseOrderService;
import com.portafolio.util.rest.client.ResponseUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/api/v1/purchase/orders")
public class PurchaseOrderController {
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getPurchaseOrders() {
		return ResponseUtil.reponseUtil(purchaseOrderService.getPurchaseOrders(), HttpStatus.OK);
	}

}
