package com.ferme.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferme.repositories.SupplierRepository;
import com.portafolio.util.entities.SupplierEntity;

@Service
public class SupplierService {


	@Autowired
	private SupplierRepository repository;
	
	public List<SupplierEntity> getSuppliers(){
		return (List<SupplierEntity>) repository.findAll();
	}

    
}