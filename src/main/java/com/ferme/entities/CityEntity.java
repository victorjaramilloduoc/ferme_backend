package com.ferme.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="CIUDAD")
@Data
public class CityEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6350980852155943997L;
	
	@Id
	@Column(name="ID_CIUDAD")
	@JsonIgnore
	private Long id;
	
	@Column(name="CIUDAD")
	private String cityName;
	
	@ManyToOne
	@JoinColumn(name ="ID_REGION", nullable = false)
	private RegionEntity region;

}
