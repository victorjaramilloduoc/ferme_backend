package com.ferme.repositories;

import org.springframework.data.repository.CrudRepository;

import com.portafolio.util.entities.PurchaseOrderEntity;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrderEntity, Long> {
	
}
