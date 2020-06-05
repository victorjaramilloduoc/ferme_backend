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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ferme.services.SupplierService;
import com.portafolio.util.entities.SupplierEntity;
import com.portafolio.util.rest.client.ResponseUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/v1/suppliers")
public class SupplierController {

	@Autowired
	private SupplierService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getSuppliers() {
		Object response = service.getSuppliers();
		if (response != null) {
			return ResponseUtil.reponseUtil(response, HttpStatus.OK);
		} else {
			return ResponseUtil.reponseUtil(response, HttpStatus.NO_CONTENT);
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> saveSupplier(@RequestBody SupplierEntity supplier) {

		Object supplierSaved = service.saveSupplier(supplier, "save_supplier");

		if (!((Map<String, Object>) supplierSaved).get("status").equals("error")) {
			return ResponseUtil.reponseUtil(supplierSaved, HttpStatus.OK);
		} else {
			return ResponseUtil.reponseUtil(supplierSaved, HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Object> updateSupplier(@RequestBody SupplierEntity supplier) {

		if (service.searchSupplier(supplier.getId() != null ? supplier.getId() : 0l) != null) {

			Object supplierSaved = service.saveSupplier(supplier, "supplier_updated");

			if (!((Map<String, Object>) supplierSaved).get("status").equals("error")) {
				return ResponseUtil.reponseUtil(supplierSaved, HttpStatus.OK);
			} else {
				return ResponseUtil.reponseUtil(supplierSaved, HttpStatus.BAD_REQUEST);
			}
		} else {
			Map<String, Object> mapResponse = new LinkedHashMap<>();
			mapResponse.put("error", "supervisor no existe");
			return ResponseUtil.reponseUtil(mapResponse, HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Object> disableSupplier(@PathVariable(value = "id") Long id) {
		Map<String, Object> response = service.disableSupplier(id);

		if (response != null) {
			Map<String, Object> mapResponse = new LinkedHashMap<>();
			if (response.containsKey("supp")) {
				mapResponse.put("message", "proveedor deshabilitado");
				mapResponse.put("supp", response.get("supp"));
				return ResponseUtil.reponseUtil(mapResponse, HttpStatus.OK);
			} else {
				mapResponse.put("error", response.get("error"));
				return ResponseUtil.reponseUtil(mapResponse, HttpStatus.CONFLICT);
			}
		} else {
			return ResponseUtil.reponseUtil(response, HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(value = "/by-product",method = RequestMethod.GET)
	public ResponseEntity<Object> getSuppliersByProductName(@RequestParam(value = "product_name") String productName){
		Object response = service.getSuppliersByProductName(productName);
		if (response != null) {
			return ResponseUtil.reponseUtil(response, HttpStatus.OK);
		} else {
			return ResponseUtil.reponseUtil(response, HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(value = "/by-product-id",method = RequestMethod.GET)
	public ResponseEntity<Object> getSuppliersByProductId(@RequestParam(value = "product_id") Long productId){
		Object response = service.getSuppliersByProducId(productId);
		if (response != null) {
			return ResponseUtil.reponseUtil(response, HttpStatus.OK);
		} else {
			return ResponseUtil.reponseUtil(response, HttpStatus.NO_CONTENT);
		}
	}

}
