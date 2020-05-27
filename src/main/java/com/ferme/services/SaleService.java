package com.ferme.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferme.repositories.SaleRepository;
import com.portafolio.util.entities.ProductEntity;
import com.portafolio.util.entities.ProductSaleEntity;
import com.portafolio.util.entities.SaleEntity;

@Service
public class SaleService {
	
	private static final Logger LOG = LoggerFactory.getLogger(SaleService.class);

	
	@Autowired
	private SaleRepository repository;
	
	@Autowired
	private ProductService productService;
	
	public List<SaleEntity> getSales(){
		return (List<SaleEntity>) repository.findAll();
	}
	
	@SuppressWarnings("unchecked")
	public Object recordSale(SaleEntity sale, String message) {
		Map<String, Object> response = new LinkedHashMap<>();
		int i = 0;
		try {
			for (ProductSaleEntity data : sale.getProductsSale()) {

				ProductEntity searchProduct = productService.searchProduct(data.getProduct().getId());
				if (searchProduct.getStock() >= data.getQuantity() ? true : false) {

					searchProduct.setStock(searchProduct.getStock() - data.getQuantity());
					Object resp = productService.saveProduct(searchProduct, "prd");

					ProductEntity prod = (ProductEntity) ((Map<String, Object>) resp).get("prd");
					i++;
					if (prod.getStock().equals(0l)) {
						productService.disableProduct(prod.getId());
					}
					if (((Map<String, Object>) resp).get("status").toString().equals("OK")
							&& i == sale.getProductsSale().size()) {
						SaleEntity saleResponse = repository.save(sale);
						response.put("status", "OK");
						response.put(message, saleResponse);
					}
				} else {
					response.put("status", "error");
					response.put("message", "error, el stock es insuficiente");
				}
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
