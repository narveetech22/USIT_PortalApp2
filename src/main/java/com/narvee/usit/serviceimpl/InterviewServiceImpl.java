package com.narvee.usit.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.Interview;
import com.narvee.usit.entity.SalesConsultant;
import com.narvee.usit.repository.ISalesConsultantsRepository;
import com.narvee.usit.repository.InterviewRepository;
import com.narvee.usit.service.IinterviewService;

@Transactional

@Service
public class InterviewServiceImpl implements IinterviewService{
	
	@Autowired
	private InterviewRepository repo;
	
	@Autowired
	private ISalesConsultantsRepository consRepo;;
	
	@Override
	public int changeStatus(String status, Long id,String rem) {
		return repo.toggleStatus(status, id,rem);
	}
	
	@Override
	public boolean saveSubmission(Interview con) {
		//SalesSubmission submission = subRepo.findById(subID).get();
		Interview interview = repo.save(con);
		if(interview != null)
			return true;
		else
			return false;
	}

	@Override
	public List<Object[]> getsubdet() {
		// TODO Auto-generated method stub
		return repo.submissiondetails();
	}

	@Override
	public List<Interview> getAllInterviews() {
		return repo.findAll();
	}

	@Override
	public Interview getInterviewByID(long id) {
		return repo.findById(id).get();
	}

	@Override
	public boolean deleteInterviewById(long id) {
		 repo.deleteById(id);
		 return true;
	}
	
	/* created By Swamy			*/
	
	@Override
	public Page<List<Interview>> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<List<Interview>> findAll = repo.getAllInterviewDetailsByPageNo(pageable);
		return findAll;
	}

	@Override
	public List<Interview> getAllInterviewBasedOnFilter(String keyword) {
		if(keyword != null) {
			System.out.println("hsdffhdsbfhdbsghfgdgf");
		return repo.getInterviewFilter(keyword);
		}
		return repo.findAll();
	}


	@Override
	public List<Interview> getAllDetailsInterview() {
		return repo.getAllDetailsInt();
	}

	@Override
	public SalesConsultant getSalesConsById(long conid) {
		return consRepo.findById(conid).get();
	}

}
