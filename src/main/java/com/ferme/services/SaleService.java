package com.ferme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferme.repositories.SaleRepository;
import com.portafolio.util.entities.SaleEntity;

@Service
public class SaleService {
	
	@Autowired
	private SaleRepository repository;
	
	public List<SaleEntity> getSales(){
		return (List<SaleEntity>) repository.findAll();
	}

}
