package com.narvee.usit.entity;



import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Table(name="salessubmission")
@AllArgsConstructor
public class SalesSubmission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "Salesubmission")
	@SequenceGenerator(name="Salesubmission",sequenceName = "salesConuSeq")
	@Column(name="subid")
	private Long subid;
	private Long  consultantid;
	@Transient
	private String cname;
	
	private String positiontitle;
	private String projectlocation;
	private String subrate;
	private String endclient;
	private String partner;
	private String vendor;
	private String ratetype;
	private String contactperson;
	private String contactnumber;
	private String contactemail;
	@Column(name= "addedby", nullable = false, updatable = false)
	private long addedby=1;
	@Transient
	private String uname;
	
	@Column(name="updatedby")
	private String updatedby;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name= "createddate", nullable = false, updatable = false)
	@CreationTimestamp()
    private LocalDate createddate;
	@Column(name="status")
	private String status;
	
	public SalesSubmission() {
		
	}
	
	public SalesSubmission(long consultantid,long addedby,LocalDate createddate,Long subid,String  cname,String positiontitle,String  projectlocation,String endclient,String vendor, String subrate,String uname) {
		this.consultantid = consultantid;
		this.addedby=addedby;
		this.createddate = createddate;
		this.subid = subid;
     	this.cname=cname;
		this.subid = subid;
		this.projectlocation = projectlocation;
		this.positiontitle=positiontitle;
		this.endclient = endclient;
		this.vendor = vendor;
		this.subrate = subrate;
		this.uname=uname;
	}
	
//	public SalesSubmission(long consultantid,long addedby,LocalDate createddate,Long subid,String  cname,String positiontitle,String  projectlocation,String endclient,String vendor, String subrate,String uname) {
//		this.createddate = createddate;
//		this.cname=cname;
//		this.addedby=addedby;
//		this.subid = subid;
//		this.projectlocation = projectlocation;
//		this.consultantid = consultantid;
//		this.positiontitle=positiontitle;
//		this.endclient = endclient;
//		this.vendor = vendor;
//		this.subrate = subrate;
//		this.addedby = addedby;
//		this.uname=uname;
//	}
	
}
