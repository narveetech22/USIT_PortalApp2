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
@Table(name="privileges")
public class Previleges {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long previd;
	
	private String previlegename;
	
	private String url;
	
	private Long roleid;
	
}
