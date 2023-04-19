package com.narvee.usit.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "technologies")
@NoArgsConstructor
@AllArgsConstructor
public class Technologies {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Technologies")
	@SequenceGenerator(name = "Technologies", sequenceName = "Technologies_seq")
	private int id;
	private String technologyarea;
	private String listofkeyword;
	@Column(name = "comments", length = 255)
	private String comments;
	private String status = "Active";
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name= "createddate", nullable = false, updatable = false)
	@CreationTimestamp()
    private LocalDate createddate;
	@Column(name= "addedby", nullable = false, updatable = false)
	private long addedby=1;
	
	@Column(name = "remarks", length = 255)
	private String remarks;
	
	
	
}
