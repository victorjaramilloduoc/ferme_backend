package com.ferme.repositories;

import org.springframework.data.repository.CrudRepository;

import com.portafolio.util.entities.UserEntity;


public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	UserEntity getUserByEmailAndPassword(String email, String password);

}
