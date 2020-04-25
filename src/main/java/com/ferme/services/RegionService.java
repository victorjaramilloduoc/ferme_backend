package com.ferme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferme.repositories.RegionRepository;
import com.portafolio.util.entities.RegionEntity;

@Service
public class RegionService {
	
	@Autowired
	private RegionRepository repository;
	
	public List<RegionEntity> getRegions(){
		return (List<RegionEntity>) repository.findAll();
	}

}
