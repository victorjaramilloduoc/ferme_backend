package com.ferme.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ferme.services.CityService;
import com.portafolio.util.rest.client.ResponseUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/api/v1/cities")
public class CityController {
	
	@Autowired
	private CityService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getCities() {
		Object response = service.getCities();
		if(response != null) {
			return ResponseUtil.reponseUtil(response, HttpStatus.OK);
		}else {
			return ResponseUtil.reponseUtil(response, HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(value = "/by-region/{regionId}",method = RequestMethod.GET)
	public ResponseEntity<Object> getCitiesByRegionId(@PathVariable(value = "regionId") Long regionId) {
		Object response = service.getCitiesByRegionId(regionId);
		if(response != null) {
			return ResponseUtil.reponseUtil(response, HttpStatus.OK);
		}else {
			return ResponseUtil.reponseUtil(response, HttpStatus.NO_CONTENT);
		}
	}

}
