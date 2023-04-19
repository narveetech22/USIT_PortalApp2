package com.narvee.usit.repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.narvee.usit.entity.Vms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IVmsRepository extends JpaRepository<Vms,Serializable> {
   public List<Vms> findByCpemail(String name);
	/*
	 * @Modifying
	 * 
	 * @Query("UPDATE Vms c SET c.status = :status WHERE c.id = :id") public int
	 * toggleStatus(@Param("status") String status, @Param("id") int id);
	 */
   @Modifying
   @Query("UPDATE Vms c SET c.status = :status,c.remarks = :rem  WHERE c.id = :id")
   public int toggleStatus(@Param("status") int status, @Param("id") Long id,@Param("rem") String rem);
	
	
   @Query("SELECT c FROM Vms c  WHERE c.addedby = :id")
   public List<Vms> searchbyid(@Param("id") long id);
   
   @Query("SELECT new com.narvee.usit.entity."
   		+ "Vms(v.id,v.company_name,v.recruiter_name,v.cp_mobile,v.cpemail,v.headQuarters,v.vendortype,u.firstname,v.createddate,v.status) from Vms v,Users u where v.addedby=u.userid")
   public List<Vms> getallvms();
   
   @Query("SELECT new com.narvee.usit.entity."
	   		+ "Vms(v.id,v.company_name,v.recruiter_name,v.cp_mobile,v.cpemail,v.headQuarters,v.vendortype,u.firstname,v.createddate,v.status) from Vms v,Users u where v.addedby=u.userid and v.createddate=:date")
	   public List<Vms> getallvmsbydate(LocalDate date);
   
   @Query("SELECT new com.narvee.usit.entity."
	   		+ "Vms(v.id,v.company_name,v.recruiter_name,v.cp_mobile,v.cpemail,v.headQuarters,v.vendortype,u.firstname,v.createddate,v.status) from Vms v,Users u where v.addedby=u.userid")
	   Page<Vms> getallvms(Pageable pageable);
   
   
   @Query("SELECT new com.narvee.usit.entity."
	   		+ "Vms(v.id,v.company_name,v.recruiter_name,v.cp_mobile,v.cpemail,v.headQuarters,v.vendortype,u.firstname,v.createddate,v.status) from Vms v,Users u where v.addedby=u.userid and CONCAT(v.company_name,  v.recruiter_name,v.cp_mobile, v.cpemail,v.headQuarters, v.vendortype, v.status,  v.addedby,  v.createddate,u.firstname) LIKE %?1%") 
	public List<Vms> getVMSFilter(String keyword);
   

}
