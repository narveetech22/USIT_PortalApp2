package com.narvee.usit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.narvee.usit.entity.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Serializable> {
//	public AddNewEmployees findByEmail(String email);
//	@Query("SELECT e FROM AddNewEmployees e, User u WHERE e.email=u.email AND u.id=:id ")
//	public AddNewEmployees findByFullName(@Param("id") long id);
	
	
	 @Modifying
	 @Query("UPDATE Interview c SET c.status = :status,c.remarks = :rem  WHERE c.intrid = :id")
	 public int toggleStatus(@Param("status") String status, @Param("id") Long id,@Param("rem") String rem);


	@Query(value = "select s.subid,c.full_name,s.vendor,s.position_title,s.project_location,s.end_client from sales_submission  s,\r\n"
			+ "tbl_sales_consultant c where c.id=s.consultantid ", nativeQuery = true)
	public List<Object[]> submissiondetails();

	/* created By Swamy */

	@Query("SELECT i FROM Interview i WHERE CONCAT(i.intrid,i.interviewDate, i.timezone,i.round,i.modeofintr, i.interviewstatus,i.addedby, i.updatedby, i.createddate) LIKE %?1%")
	public List<Interview> getInterviewFilter(String keyword);

	@Query(value = "SELECT new com.narvee.usit.entity.Interview(i.addedby,i.intrid,c.sid,i.submissionid,c.fullname,  i.interviewDate,i.timezone,  i.round,\r\n"
			+ "i.modeofintr,  s.vendor,  s.createddate,\r\n"
			+ "u.fullname,i.interviewstatus,i.status)  from Interview i,SalesSubmission s,Users u, SalesConsultant c where i.submissionid= s.subid AND s.consultantid = c.sid AND s.addedby = u.userid")
	public List<Interview> getAllDetailsInt();
	
	@Query(value = "SELECT new com.narvee.usit.entity.Interview(i.addedby,i.intrid,c.sid,i.submissionid,c.fullname,  i.interviewDate,i.timezone,  i.round,\r\n"
			+ "i.modeofintr,  s.vendor,  s.createddate,\r\n"
			+ "u.fullname, i.interviewstatus,i.status)  from Interview i,SalesSubmission s,Users u, SalesConsultant c where i.submissionid= s.subid AND s.consultantid = c.sid AND s.addedby = u.userid")
	public Page<List<Interview>> getAllInterviewDetailsByPageNo(Pageable pageable);

}
