package com.ferme.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Entity
@Table(name="PRODUCTO")
@Data
public class ProductEntity implements Serializable {

	/**
	 * Serializado de la clase
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_PRODUCTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "CODIGO_PRODUCTO")
	@SerializedName("product_code")
	private Long productCode;
	
	@Column(name ="NOMBRE")
	private String name;
	
	@Column(name="DESCRIPCION")
	private String description;
	
	@Column(name = "PRECIO")
	private Long price;
	
	@Column(name = "STOCK")
	private Long stock;
	
	@Column(name = "ID_PROVEEDOR")
	@SerializedName("supplier_id")
	private Long supplierId;

}
