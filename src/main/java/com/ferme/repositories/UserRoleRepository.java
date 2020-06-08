package com.ferme.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.portafolio.util.entities.UserRoleEntity;

public interface UserRoleRepository extends CrudRepository<UserRoleEntity, Long> {
	
	List<UserRoleEntity> getUserRolesByUserId(Long userId);

}
