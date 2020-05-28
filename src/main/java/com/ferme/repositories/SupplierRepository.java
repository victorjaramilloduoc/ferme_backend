package com.ferme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.portafolio.util.entities.SupplierEntity;

public interface SupplierRepository extends CrudRepository<SupplierEntity, Long>{
	
	@Query("SELECT s "
			+ "FROM "
			+ "SupplierEntity s "
			+ "JOIN ProductEntity p ON s.id = p.supplier.id "
			+ "WHERE UPPER(p.name) LIKE %:productName% ")
	List<SupplierEntity> getSuppliersByProductName(@Param("productName")String productName);

}
