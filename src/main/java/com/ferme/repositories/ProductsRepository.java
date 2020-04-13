package com.ferme.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ferme.entities.ProductEntity;


public interface ProductsRepository extends CrudRepository<ProductEntity, Long> {

}
