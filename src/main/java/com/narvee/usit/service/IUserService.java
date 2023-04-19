package com.narvee.usit.service;

import java.util.List;

import com.narvee.usit.entity.Users;

public interface IUserService {
	
	public Users saveUser(Users users);
	
	public Users findUserByEmail(String email);
	
	public Users findUserByEmailandUid(String email, Long id);
	
	public List<Users> getAllUsers();
	
	public Users finduserById(Long id);
	
	public boolean deleteUsers(Long id);
	
	public int changeStatus(String status, Long id,String remarks);
	
	public List<Users> filterEmployee(String keyword);
	
	

}
