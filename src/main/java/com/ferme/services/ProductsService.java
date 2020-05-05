package com.ferme.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferme.repositories.ProductsRepository;
import com.portafolio.util.entities.ProductEntity;

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
	public List<ProductEntity> getProducts(){
		List<ProductEntity> response = new ArrayList<>();
		try {
			response = (List<ProductEntity>) repository.findAll();
		} catch (Exception e) {
			LOG.error("Error al buscar los productos", e.getMessage(), e);
		}
		return response;
	}
	
	public Object saveProduct(ProductEntity product, String message) {
		Map<String, Object> response = new LinkedHashMap<>();
		try {
			ProductEntity prod = repository.save(product);
			response.put("status", "OK");
			response.put(message, prod);
		} catch (Exception e) {
			response.put("status", "error");
			response.put("cause", e.getMessage());
			response.put("details", e);
			LOG.error("Error al " + message + " el producto. Cause: {}", e.getMessage(), e);
		}
		return response;
	}
	
	public ProductEntity searchProduct(Long id) {
		Optional<ProductEntity> response = null;
		try {
			response = repository.findById(id);
		} catch (Exception e) {
			LOG.error("Error al buscar el producto. Causa: {}", e.getMessage(), e);
		}
		return response.isPresent() ? response.get(): null;
	}

}
