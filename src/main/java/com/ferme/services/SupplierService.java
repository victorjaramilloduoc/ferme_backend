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

import com.ferme.repositories.SupplierRepository;
import com.portafolio.util.entities.SupplierEntity;

@Service
public class SupplierService {
	
	private Logger LOG = LoggerFactory.getLogger(SupplierService.class);

	@Autowired
	private SupplierRepository repository;
	
	public List<SupplierEntity> getSuppliers(){
		List<SupplierEntity> response = new ArrayList<>();
		try {
			response = (List<SupplierEntity>) repository.findAll();
		} catch (Exception e) {
			LOG.error("Error al buscar los proveedores", e.getMessage(), e);
		}
		return response;
	}
	
	public Object saveSupplier(SupplierEntity supplier, String message) {
		Map<String, Object> response = new LinkedHashMap<>();
		try {
			SupplierEntity supp = repository.save(supplier);
			response.put("status", "OK");
			response.put(message, supp);
		} catch (Exception e) {
			response.put("status", "error");
			response.put("cause", e.getMessage());
			response.put("details", e);
			LOG.error("Error al " + message + " el proveedor. Cause: {}", e.getMessage(), e);
		}
		return response;
	}
	
	public SupplierEntity searchSupplier(Long id) {
		Optional<SupplierEntity> response = null;
		try {
			response = repository.findById(id);
		} catch (Exception e) {
			LOG.error("Error al buscar el proveedor. Causa: {}", e.getMessage(), e);
		}
		return response.isPresent() ? response.get(): null;
	}
	
	public Map<String, Object> disableSupplier(Long id) {
		SupplierEntity su = null;
		Map<String, Object> responseMap = new LinkedHashMap<>();
		try {
			su = searchSupplier(id);
			if(su != null) {
				su.setEnable(false);
				su = repository.save(su);
				responseMap.put("supp", su);
			}else {
				responseMap.put("error", "el proveedor no existe");
			}
		} catch (Exception e) {
			LOG.error("Error al deshabilitar el proveedor, causa: {}", e.getMessage(), e);
			responseMap.put("error", "Error al deshabilitar el proveedor");
			responseMap.put("cause", e.getMessage());
			responseMap.put("details", e);
		}
		return responseMap;
	}
}
