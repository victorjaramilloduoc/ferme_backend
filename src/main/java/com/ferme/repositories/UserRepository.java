package com.ferme.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ferme.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
