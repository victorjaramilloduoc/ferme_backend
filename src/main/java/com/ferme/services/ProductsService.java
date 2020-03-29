package com.ferme.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferme.entities.ProductsEntity;
import com.ferme.repositories.ProductsRepository;

/**
 * 
 * @author victor Jaramillo
 *
 */
@Service
public class ProductsService {
	
	private Logger LOG = LoggerFactory.getLogger(ProductsService.class);
	
	@Autowired
	private ProductsRepository  repository;

	/**
	 * Método que retorna una lista con todos los productos registrados en la base de datos.
	 * @return response
	 */
	public List<ProductsEntity> getProducts(){
		List<ProductsEntity> response = new ArrayList<>();
		try {
			response = (List<ProductsEntity>) repository.findAll();
		} catch (Exception e) {
			LOG.error("Error al buscar los productos", e.getMessage(), e);
		}
		return response;
	}
	
	public boolean saveProduct(ProductsEntity product) {
		boolean response = false;
		try {
			repository.save(product);
			response = true;
		} catch (Exception e) {
			LOG.error("Error al guardar el producto. Cause: {}",e.getMessage());
		}
		return response;
	}

}
