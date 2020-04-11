package com.ferme.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Entity
@Table(name="REGIÓN")
@Data
public class RegionEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2868380745983370952L;
	
	@Id
	@Column(name="ID_REGIÓN") 
	@JsonIgnore
	private Long id;
	
	@Column(name="REGION")
	@SerializedName("region_name")
	private String regionName;
	
}
