package com.narvee.usit.serviceimpl;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.Roles;
import com.narvee.usit.entity.TechAndSupport;
import com.narvee.usit.entity.Vms;
import com.narvee.usit.repository.ITechSupportRepository;
import com.narvee.usit.service.ITechSupportService;

@Transactional
@Service
public class TechAndSupportImpl implements ITechSupportService {

	@Autowired
	private ITechSupportRepository repository;
	
	@Override
	public TechAndSupport saveTechSupp(TechAndSupport entity) {
		return repository.save(entity);
	}

	@Override
	public List<TechAndSupport> getAll(String search) {
		List<TechAndSupport> findAlln=new ArrayList();
		System.out.println(search);
		if (!search.equals("dummysearch")) {
			System.out.println("oooooooooooooooooooo");
			findAlln = repository.getAll(search.trim());
		}
		else if(search.equals("dummysearch"))
		{
			System.out.println("yyyyyyyyyyyyyyyyyy");
			findAlln =  repository.findAll();
		}
		else {
			findAlln =  repository.findAll();
		}
		return findAlln;
	}

	@Override
	public TechAndSupport getTechSupp(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public boolean deleteSupp(Long id) {
		repository.deleteById(id);
		return true;
	}
	
	@Override
	public int changeStatus(String status, Long id,String rem) {
		return repository.toggleStatus(status, id,rem);
	}

}
