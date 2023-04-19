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

import lombok.Data;

@Entity
@Data
@Table(name="salesconsultant")

public class SalesConsultant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "SalesConsultants")
	@SequenceGenerator(name="SalesConsultants",sequenceName = "salesConuSeq")
	@Column(name="id")
	private long sid; 
	
	@Column(name="fullname")
	private String fullname;			

	@Column(name="email",length = 50)
	private String email;
	
	@Column(name="uscontactumber")
	private String uscontactnumber;
	
	@Column(name="visastatus")
	private long visastatus;
	
	@Column(name="totexp")
	private String totexp;
	
	@Column(name="Currentlocation")
	private String currentlocation;
	
	@Column(name="relocate")
	private String relocate;
	
	@Column(name="Technology")
	private long technology;
	
	@Column(name="additionalskills", length=3500)
	private String additionalskills;		
	
	@Column(name="ratetype")
	private String ratetype;
	
	@Column(name="hourlyrate")
	private String hourlyrate;		
	 		
	@Column(name="summary",length=5000)
	private String summary;
	
	@Column(name="resume")
	private String resumepath;
	 				
	@Column(name="h1bcopy")
	private String h1bcopypath;
					
	@Column(name="drivinglicencecopy")
	private String drivinglicencecopypath;	
	
	private String resumeFileName;
	private String dlFileName;
	private String h1bFileName;
	 
	@Column(name="priority")
	private String priority;
	
	@Column(name="relocateval")
	private String relocateval;
	
	@Column(name="anyrestriction")
	private String anyrestriction;
	
	@Column(name="status")
	private String status="Active";
	 

	@Column(name= "addedby", nullable = false, updatable = false)
	private long addedby = 1;
	
	@Column(name="remarks")
	private String remarks;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name= "createddate", nullable = false, updatable = false)
	@CreationTimestamp
    private LocalDate createddate;
	
	@Column(name="updateddate") 
	@UpdateTimestamp
	private LocalDateTime updateddate;
	
	@Column(name="updatedby")
	private long updatedby = 1;
	
	@Transient
	private String techname;
	
	@Transient
	private String visatype;

	public SalesConsultant() {
		
	}
	public SalesConsultant(long sid, String fullname, String totexp, String currentlocation, String relocate,
			String hourlyrate, String priority, String relocateval, String status, String remarks,
			 String techname, String visatype) {
		this.sid = sid;
		this.fullname = fullname;
		this.totexp = totexp;
		this.currentlocation = currentlocation;
		this.relocate = relocate;
		this.hourlyrate = hourlyrate;
		this.priority = priority;
		this.relocateval = relocateval;
		this.status = status;
		this.remarks = remarks;
		this.techname = techname;
		this.visatype = visatype;
	}
	
	public SalesConsultant(long sid, String fullname, long technology, String totexp) {
		this.sid = sid;
		this.fullname = fullname;
		this.totexp = totexp;
		this.technology = technology;
	}
	
}
