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
@Table(name = "tb_visa")
public class Visa {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Visa")
	@SequenceGenerator(name = "Visa", sequenceName = "Visa_seq")
	private Long vid;

	@Column(name = "visa_status")
	private String status;

	@Column(name = "visa_description")
	private String description;
}
