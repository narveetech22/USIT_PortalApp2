package com.narvee.usit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "user")
	@SequenceGenerator(name="user", sequenceName = "user_seq")
	private long userid;
	
	private String firstname;
	
	private String lastname;
	
	private String fullname;
	
	private String pseudoname;
	
	private String personalcontactnumber;
	
	private String companycontactnumber;
	
	private String email;
	
	private int isactive;
	
	private String password;
	
	private String designation;
	
	private String status="Active";
	
	private Long roleid;
	
	@Column(name="remarks", length=400)
	private String remarks;
	
}
