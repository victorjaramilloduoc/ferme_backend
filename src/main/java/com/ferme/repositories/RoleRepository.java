package com.ferme.repositories;

import org.springframework.data.repository.CrudRepository;

import com.portafolio.util.entities.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
	
}
