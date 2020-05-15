package com.ferme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferme.repositories.HeadingRepository;
import com.portafolio.util.entities.HeadingEntity;

@Service
public class HeadingService {
	
	@Autowired
	private HeadingRepository repository;
	
	public List<HeadingEntity> getHeadings(){
		return (List<HeadingEntity>) repository.findAll();
	}

}
