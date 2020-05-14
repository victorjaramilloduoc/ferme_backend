package com.ferme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferme.repositories.UserRoleRepository;
import com.portafolio.util.entities.UserRoleEntity;

@Service
public class UserRoleService {
	
	@Autowired
	private UserRoleRepository repository;
	
	public List<UserRoleEntity> getUserRolesByUserId(Long userId){
		return (List<UserRoleEntity>) repository.getUserRolesByUserId(userId);
	}

}
