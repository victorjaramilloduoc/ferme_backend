package com.ferme.repositories;

import org.springframework.data.repository.CrudRepository;

import com.portafolio.util.entities.ProductSaleEntity;

public interface ProductSaleRepository extends CrudRepository<ProductSaleEntity, Long> {
	
}
