package com.narvee.usit.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.Visa;
import com.narvee.usit.repository.IVisaRepository;
import com.narvee.usit.service.IVisaService;

@Service
public class VisaServiceImpl implements IVisaService {

	@Autowired
	private IVisaRepository repository;

	@Override
	public boolean saveVisa(Visa visa) {
		Visa visa2 = repository.save(visa);
		if(visa2 != null) {
			return true;
		}else
			return false;	
	}

	@Override
	public Visa getVisaById(long visaId) {
		return repository.findById(visaId).get();
	}

	@Override
	public List<Visa> getAllVisa() {
		return repository.findAll();
	}

	@Override
	public boolean deleteVisaStatus(long visaId) {
		Visa visa = getVisaById(visaId);
		if (visa != null) {
			repository.delete(visa);
			return true;
		}
		return false;
	}
	
	@Override
	public Page<Visa> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<Visa> findAll = repository.findAll(pageable);
		return findAll;
	}

}
