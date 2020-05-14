package com.ferme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferme.repositories.RoleRepository;
import com.portafolio.util.entities.RoleEntity;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository repository;
	
	public List<RoleEntity> getRoles(){
		return (List<RoleEntity>) repository.findAll();
	}
	
}
