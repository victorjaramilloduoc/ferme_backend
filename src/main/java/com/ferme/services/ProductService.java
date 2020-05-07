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

import com.ferme.repositories.ProductFamilyRepository;
import com.ferme.repositories.ProductRepository;
import com.ferme.repositories.ProductSubFamilyRepository;
import com.portafolio.util.entities.ProductEntity;
import com.portafolio.util.entities.ProductFamilyEntity;
import com.portafolio.util.entities.ProductSubFamilyEntity;

/**
 * 
 * @author victor Jaramillo
 *
 */
@Service
public class ProductService {
	
	private Logger LOG = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository  repository;
	
	@Autowired
	private ProductSubFamilyRepository subFamilyRepo;
	
	@Autowired
	private ProductFamilyRepository familyRepo;

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
	
	public Map<String, Object> disableProduct(Long id) {
		ProductEntity prod = null;
		Map<String, Object> responseMap = new LinkedHashMap<>();
		try {
			prod = searchProduct(id);
			if(prod != null) {
				prod.setEnable(false);
				prod = repository.save(prod);
				responseMap.put("product", prod);
			}else {
				responseMap.put("error", "el producto no existe");
			}
		} catch (Exception e) {
			LOG.error("Error al desabilidar el usuario, causa: {}", e.getMessage(), e);
			responseMap.put("error", "Error al desabilidar el usuario");
			responseMap.put("cause", e.getMessage());
			responseMap.put("details", e);
		}
		return responseMap;
	}
	
	public List<ProductSubFamilyEntity> getProductsSubFamily(){
		List<ProductSubFamilyEntity> response = new ArrayList<>();
		try {
			response = (List<ProductSubFamilyEntity>) subFamilyRepo.findAll();
		} catch (Exception e) {
			LOG.error("Error al buscar las subfamilias", e.getMessage(), e);
		}
		return response;
	}
	
	public List<ProductFamilyEntity> getProductsFamily(){
		List<ProductFamilyEntity> response = new ArrayList<>();
		try {
			response = (List<ProductFamilyEntity>) familyRepo.findAll();
		} catch (Exception e) {
			LOG.error("Error al buscar las familias", e.getMessage(), e);
		}
		return response;
	}

}
