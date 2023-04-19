package com.narvee.usit.repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import com.narvee.usit.entity.SalesSubmission;

public interface ISalesSubmissionrepo  extends JpaRepository<SalesSubmission, Serializable> {
	//public SalesSubmission(long consultantid,
	//		long addedby,LocalDate createddate,Long subid,String  cname,String positiontitle,String  projectlocation,String endclient,String vendor, String subrate,String uname) {
//	String positiontitle,String  projectlocation,String endclient,String vendor, String subrate
	@Query("SELECT new com.narvee.usit.entity."
			+ "SalesSubmission(s.consultantid,s.addedby,s.createddate,s.subid,c.fullname,s.positiontitle,s.projectlocation,s.endclient,s.vendor,s.subrate,u.fullname) from SalesSubmission s, Users u, SalesConsultant c where s.consultantid = c.sid and s.addedby = u.userid")
	public List<SalesSubmission> findallSub();
	
//	@Query("SELECT new com.narvee.usit.entity."
//			+ "SalesSubmission(s.consultantid,s.addedby,s.createddate,s.subid,c.fullName, s.positiontitle,s.projectlocation,s.endclient,s.vendor,s.subrate,u.firstname) from SalesSubmission s, Users u,SalesConsultant c where u.userid=s.addedby and s.consultantid=c.sid")
//	public List<SalesSubmission> findall();
 //@Query("SELECT new com.narvee.usit.entity."
//				+ "SalesSubmission(c.sid,u.id,s.createddate,s.subid,c.fullName, s.positiontitle,s.projectlocation,s.endclient,s.vendor,s.subrate,u.firstname) from SalesSubmission s, User u,SalesConsultants c where u.id=s.addedby and s.consultantid=c.sid")
				//Page<SalesSubmission> findAlld(Pageable pageable);
 //
 //@Query("SELECT new com.narvee.usit.entity."
//			+ "SalesSubmission(c.sid,u.id,s.createddate,s.subid,c.fullName, s.positiontitle,s.projectlocation,s.endclient,s.vendor,s.subrate,u.firstname) from SalesSubmission s, User u,SalesConsultants c where u.id=s.addedby and "
//			+ "s.consultantid=c.sid AND CONCAT(c.fullName, s.positiontitle,s.projectlocation,s.endclient,"
//			+ "s.vendor,s.subrate,u.firstName) Like %?1% "
//		)
		//	Page<SalesSubmission> findAlldbykey(Pageable pageable,String keyword);
 
// @Query("SELECT new com.narvee.usit.entity."
//			+ "SalesSubmission(c.sid,u.id,s.createddate,s.subid,c.fullName, s.positiontitle,s.projectlocation,s.endclient,s.vendor,s.subrate,u.firstname) from SalesSubmission s, User u,SalesConsultants c where u.id=s.addedby and "
//			+ "s.consultantid=c.sid and s.createddate =:keyword"
//		)
		//	Page<SalesSubmission> findAlldbydateq(Pageable pageable,LocalDate keyword);
 
public List<SalesSubmission>  findByConsultantidAndProjectlocationAndEndclient(long id,String loc,String client);
}

