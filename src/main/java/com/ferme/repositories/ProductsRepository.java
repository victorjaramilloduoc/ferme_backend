package com.ferme.repositories;

import org.springframework.data.repository.CrudRepository;

import com.portafolio.util.entities.ProductEntity;

public interface ProductsRepository extends CrudRepository<ProductEntity, Long> {

}
