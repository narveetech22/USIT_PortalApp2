package com.narvee.usit.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;



@Entity
@Data
@Table(name="tbl_vms")
public class Vms {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Vms")
	@SequenceGenerator(name="Vms", sequenceName = "Vms_seq")
	private Long id; 
	
	@Column(name="company_name",length = 255) 
	private String company_name;
	
	@Column(name="recruiter_name",length = 100) 
	private String recruiter_name;
	
	@Column(name="cp_mobile",length = 50) 
	private String cp_mobile;
	
	@Column(name="cp_email",length = 50) 
	private String cpemail;

	@Column(name="headquaters",length = 100) 
	private String headQuarters;
	
	@Column(name="category",length = 150) 
	private String vendortype;
	
	@Column(name="details",length = 3000) 
	private String details;
	
	@Column(name="status") 
	private int status = 1;
	
	@Column(name="updated_date") 
	@UpdateTimestamp
	private LocalDateTime updateddate;
	
	@Column(name= "added_by", nullable = false, updatable = false)
	private long addedby=1;
	
	@Transient
	private String addedbyname;
	
	@Column(name="updated_by") 
	private String updatedby;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name= "created_date", nullable = false, updatable = false)
	@CreationTimestamp
    private LocalDate createddate;
	
	@Column(length = 3000) 
	private String  remarks;
	
	public Vms(){
		
	}
	public Vms(Long id,String company_name,String recruiter_name,String cp_mobile,String cpemail,String headQuarters,String vendortype,String addedbyname,LocalDate createddate,int status) {
		this.id=id;
		this.company_name=company_name;
		this.recruiter_name=recruiter_name;
		this.cp_mobile=cp_mobile;
		this.cpemail=cpemail;
		this.headQuarters=headQuarters;
		this.vendortype=vendortype;
		this.addedbyname=addedbyname;
		this.createddate=createddate;
		this.status=status;
	}

}

