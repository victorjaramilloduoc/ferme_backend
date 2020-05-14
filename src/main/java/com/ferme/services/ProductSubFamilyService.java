package com.ferme.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferme.repositories.ProductSubFamilyRepository;
import com.portafolio.util.entities.ProductSubFamilyEntity;

@Service

public class ProductSubFamilyService {


    @Autowired
	private ProductSubFamilyRepository repository;
	
	public List<ProductSubFamilyEntity> getSubFamily(){
		return (List<ProductSubFamilyEntity>) repository.findAll();
	}
    
}