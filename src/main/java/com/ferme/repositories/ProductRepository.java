package com.ferme.repositories;

import org.springframework.data.repository.CrudRepository;

import com.portafolio.util.entities.ProductEntity;



public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

}
