package com.ferme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferme.repositories.LocationRepository;
import com.portafolio.util.entities.LocationEntity;

@Service
public class LocationService {
	
	@Autowired
	private LocationRepository repository;
	
	public List<LocationEntity> getLocations(){
		return (List<LocationEntity>) repository.findAll();
	}
	
	public List<LocationEntity> getLocationsByCityId(Long cityId){
		return (List<LocationEntity>) repository.getLocationsByCityId(cityId);
	}
}
