package com.narvee.usit.repository;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.narvee.usit.entity.SalesConsultant;
import com.narvee.usit.helper.SalesConsultantHelper;
public interface ISalesConsultantsRepository extends JpaRepository<SalesConsultant, Serializable> {

	@Query("SELECT s FROM SalesConsultant s WHERE s.id=:id ")
	public SalesConsultant getConsultantById(@Param("id") long id);

	@Modifying
	@Query("UPDATE SalesConsultant c SET c.status = :status,c.remarks = :rem  WHERE c.sid = :id")
	public int toggleStatus(@Param("status") String status, @Param("id") long id, @Param("rem") String rem);

	//@Query("select s.sid,s.fullname,s.technology,s.totexp  from SalesConsultant s ")
	@Query("select new com.narvee.usit.entity.SalesConsultant(s.sid,s.fullname,s.technology,s.totexp)  from SalesConsultant s ")
	List<SalesConsultant> findconsultantexp();
	//public SalesConsultant(long sid, String fullname, String totexp, String currentlocation, String relocate,
	//		String hourlyrate, String priority, String , String status, String remarks,
	//		LocalDate createddate, String techname, String visatype)
	@Query("SELECT new com.narvee.usit.entity.SalesConsultant(c.sid, c.fullname, c.totexp, c.currentlocation, c.relocate, c.hourlyrate, c.priority, c.relocateval,c.status, c.remarks, t.technologyarea, v.status) From SalesConsultant c, Technologies t, Visa v WHERE c.technology = t.id and c.visastatus=v.vid")
	public List<SalesConsultant> findAllConsultants();
	
	
}
