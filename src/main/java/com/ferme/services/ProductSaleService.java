package com.ferme.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferme.repositories.ProductSaleRepository;
import com.portafolio.util.entities.ProductEntity;
import com.portafolio.util.entities.ProductSaleEntity;

@Service
public class ProductSaleService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductSaleService.class);

	
	@Autowired
	private ProductSaleRepository repository;
	
	@Autowired
	private ProductService productService;
	
	public List<ProductSaleEntity> getSales(){
		return (List<ProductSaleEntity>) repository.findAll();
	}
	
	@SuppressWarnings("unchecked")
	public Object saveSale(ProductSaleEntity sale, String message) {
		Map<String, Object> response = new LinkedHashMap<>();
		try {
			ProductEntity searchProduct = productService.searchProduct(sale.getProduct().getId());
			if(searchProduct.getStock() >= sale.getQuantity() ? true : false) {
				
				searchProduct.setStock(searchProduct.getStock() - sale.getQuantity());
				Object resp = productService.saveProduct(searchProduct, "");
				
				if( ((Map<String, Object>)resp).get("status").toString().equals("OK") ) {
					ProductSaleEntity saleResponse = repository.save(sale);
					response.put("status", "OK");
					response.put(message, saleResponse);
				}
			}else {
				response.put("status", "error");
				response.put("message", "error, el stock es insuficiente");
			}
		} catch (Exception e) {
			response.put("status", e.getCause());
			response.put("cause", e.getMessage());
			response.put("details", e);
			LOG.error("Error al " + message + " el producto. Cause: {}", e.getMessage(), e);
		}
		return response;
	}

}
