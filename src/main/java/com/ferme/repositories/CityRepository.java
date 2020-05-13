package com.ferme.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.portafolio.util.entities.CityEntity;

public interface CityRepository extends CrudRepository<CityEntity, Long> {
	
	List<CityEntity> getCitiesByRegionId(Long regionId);
	
}
