package com.ferme.repositories;

import org.springframework.data.repository.CrudRepository;

import com.portafolio.util.entities.StatusPurchaseOrderEntity;

public interface StatusPurchaseOrderRepository extends CrudRepository<StatusPurchaseOrderEntity, Long> {
	
}
