package com.narvee.usit.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.Technologies;
import com.narvee.usit.entity.Vms;
import com.narvee.usit.repository.TechnologyRepository;
import com.narvee.usit.service.ITechnologyService;
import static org.springframework.data.domain.PageRequest.of;
@Service
@Transactional
public class TechnologyServiceImpl implements ITechnologyService{
	
	@Autowired
	public TechnologyRepository repository;
	
	@Override
	public boolean saveTechnologies(Technologies technologies) {
		Technologies tech = repository.save(technologies);
		if(tech != null) 
			return true;
		else 
			return false;	
	}

	@Override
	public List<Technologies> getAllTechnologies() {
		return repository.findAll();
	}

	@Override
	public Technologies getTechnologyByID(int techID) {
		return repository.findById(techID).get();
	}

	@Override
	public boolean deleteTechnologiesById(int id) {
		repository.deleteById(id);
		return true;
	}

	@Override
	public List<Object[]> getAllTechnologies2() {
		return repository.findAlls();
	}

	@Override
	public Page<Technologies> getUsers(String name, int page, int size) {
		return repository.findByTechnologyareaContaining(name, of(page, size));
		//import static org.springframework.data.domain.PageRequest.of;
	}
	
	@Override
	public Page<List<Technologies>> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<List<Technologies>> findAll = repository.getalltech(pageable);
		return findAll;
	}

	@Override
	public int changeStatus(String status, int id, String remarks) {
		return repository.toggleStatus(status, id,remarks);
	}

}

