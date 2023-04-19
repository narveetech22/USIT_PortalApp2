package com.narvee.usit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.narvee.usit.entity.TechAndSupport;
import com.narvee.usit.entity.Users;

public interface IUsersRepository extends JpaRepository<Users, Serializable> {
	
	public Users findByEmail(String email);
	
	public Users findByEmailAndUseridNot(String email, Long id);
	
   @Modifying
   @Query("UPDATE Users c SET c.status = :status,c.remarks = :rem  WHERE c.userid = :id")
   public int toggleStatus(@Param("status") String status, @Param("id") Long id,@Param("rem") String rem);
	
	@Query("SELECT u FROM Users u WHERE u.email=:email AND u.password=:password")
	public  Users finByEmailAndPassword(@Param("email") String email,@Param("password") String password);
	
	@Query(" from  Users t where CONCAT(t.fullname, t.email, t.personalcontactnumber, t.designation,  t.status) like %?1%")
	public List<Users> getAll(String search);

}
