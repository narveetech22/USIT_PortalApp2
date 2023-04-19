package com.narvee.usit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.narvee.usit.entity.Roles;


public interface IRolesRepository extends JpaRepository<Roles, Serializable> {

	@Query("SELECT r.rolename FROM Roles r WHERE r.rolename=:roleName")
	public List<String> findRolByRolName(@Param("roleName") String roleName );
	
	//@Query("SELECT r.rolename FROM Roles r WHERE r.rolename=:roleName")
	//public List<String> findRolByRolName(@Param("roleName") String roleName );
	
	public Roles findByRolenameAndRoleidNot(String rolename, Long id);

}
