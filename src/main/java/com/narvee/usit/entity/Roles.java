package com.narvee.usit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="roles")
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long roleid;
	
	private String rolename;
	
	private String Description;
	
}
