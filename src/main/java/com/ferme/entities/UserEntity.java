package com.ferme.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="USUARIOS")
@Data
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3492697663152327734L;
	
	@Id
	@Column(name="ID_USUARIO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="NOMBRE_USUARIO")
	private String userName;
	
	@Column(name="APELLIDO_USUARIO")
	private String lastName;

	@Column(name="RUT_USUARIO")
	private Long rut;
	
	@Column(name="DV_USUARIO")
	private char dv;
	
	@Column(name="SEXO_USUARIO")
	private char genere;
	
	@Column(name="FECHA_NACIMIENTO_USUARIO")
	private Date birthDate;
	
	@Column(name="PASS_USUARIO")
	private String password;
	
	@Column(name="IS_ADMIN")
	private boolean isAdmin;

}
