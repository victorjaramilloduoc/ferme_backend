package com.ferme.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Entity
@Table(name="USUARIO")
@Data
public class UserEntity implements Serializable {

	/**
	 * Serializado de la clase
	 */
	private static final long serialVersionUID = -3492697663152327734L;
	
	//Este Tag hace referencia al id de la tabla en la base de datos.
	@Id
	// Este Tag hace referencia a la columna de la tabla en base de datos.
	@Column(name="ID_USUARIO")
	// Este tag nos ayudará en el caso que se agreguen nuevos usuarios, este tag creará automáticamente un id secuencial.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOMBRE")
	private String name;

	@Column(name="APELLIDO")
	private String lastName;
	
	@Column(name ="NOMBRE_USUARIO")
	private String userName;

	@Column(name="RUT_USUARIO")
	private Long rut;
	
	@Column(name="DV_USUARIO")
	private char dv;
	
	@Column(name="SEXO_USUARIO")
	private char genere;
	
	@Column(name="FECHA_NACIMIENTO_USUARIO")
	@SerializedName("birth_date")
	private Date birthDate;
	
	@Column(name="PASS_USUARIO")
	private String password;
	
	@Column(name="DIRECCION")
	private String address;
	
	@Column(name = "ID_COMUNA")
	private Long location;

}
