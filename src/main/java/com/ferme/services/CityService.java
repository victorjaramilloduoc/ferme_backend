package com.ferme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferme.repositories.CityRepository;
import com.portafolio.util.entities.CityEntity;

@Service
public class CityService {
	
	@Autowired
	private CityRepository repository;
	
	public List<CityEntity> getCities(){
		return (List<CityEntity>) repository.findAll();
	}

}
