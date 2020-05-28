package com.ferme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferme.repositories.PurchaseOrderRepository;
import com.ferme.repositories.StatusPurchaseOrderRepository;
import com.portafolio.util.entities.PurchaseOrderEntity;
import com.portafolio.util.entities.StatusPurchaseOrderEntity;

@Service
public class PurchaseOrderService {
	
	@Autowired
	private PurchaseOrderRepository orderRepository;
	
	private StatusPurchaseOrderRepository statusOrderRepository;
	
	public List<StatusPurchaseOrderEntity> getPurchaseOrders(){
		System.out.println(statusOrderRepository.findAll());
		return (List<StatusPurchaseOrderEntity>) statusOrderRepository.findAll();
	}
	
	public PurchaseOrderEntity savePurchaseOrder(PurchaseOrderEntity userRole) {
		return (PurchaseOrderEntity) orderRepository.save(userRole);
	}
	
}
