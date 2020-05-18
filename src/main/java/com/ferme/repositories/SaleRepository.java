package com.ferme.repositories;

import org.springframework.data.repository.CrudRepository;

import com.portafolio.util.entities.SaleEntity;

public interface SaleRepository extends CrudRepository<SaleEntity, Long> {
	
}
