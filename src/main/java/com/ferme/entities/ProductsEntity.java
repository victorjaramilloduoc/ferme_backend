package com.ferme.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="PRODUCTOS")
@Data
public class ProductsEntity implements Serializable {

	/**
	 * Serializado de la clase
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_PRODUCTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="TITULO")
	private String title;
	
	@Column(name="DESCRIPCION")
	private String description;

}
