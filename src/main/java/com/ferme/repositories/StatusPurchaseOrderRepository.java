package com.ferme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.portafolio.util.entities.StatusPurchaseOrderEntity;

public interface StatusPurchaseOrderRepository extends CrudRepository<StatusPurchaseOrderEntity, Long> {
	
	@Query("SELECT p FROM StatusPurchaseOrderEntity p JOIN PurchaseOrderEntity pr ON pr.id = p.purchaseOrder.id")
	List<StatusPurchaseOrderEntity> getPurchaseOrders();
	
}
