package com.ferme.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.portafolio.util.entities.LocationEntity;

public interface LocationRepository extends CrudRepository<LocationEntity, Long> {
	
	List<LocationEntity> getLocationsByCityId(Long cityId);

}
