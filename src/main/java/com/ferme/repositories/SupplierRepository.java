package com.ferme.repositories;

import org.springframework.data.repository.CrudRepository;

import com.portafolio.util.entities.SupplierEntity;

public interface SupplierRepository  extends CrudRepository<SupplierEntity, Long> {

    
}