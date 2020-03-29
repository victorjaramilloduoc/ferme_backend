package com.ferme.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ferme.entities.ProductsEntity;

public interface ProductsRepository extends CrudRepository<ProductsEntity, Long> {

}
