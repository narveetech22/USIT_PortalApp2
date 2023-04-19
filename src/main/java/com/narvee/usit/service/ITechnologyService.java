package com.narvee.usit.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.narvee.usit.entity.Technologies;

public interface ITechnologyService {

	public boolean saveTechnologies(Technologies technologies);
	public List<Technologies> getAllTechnologies();
	public List<Object[]> getAllTechnologies2();
	public Technologies getTechnologyByID(int techID);
	public boolean deleteTechnologiesById(int id);
	Page<Technologies> getUsers(String name, int page, int size);
	Page<List<Technologies>> findPaginated(int pageNo, int pageSize);
	public int changeStatus(String status, int id,String remarks);
}
