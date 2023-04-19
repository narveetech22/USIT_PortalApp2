package com.narvee.usit.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.narvee.usit.entity.Interview;
import com.narvee.usit.entity.SalesConsultant;
public interface IinterviewService {
	
	public boolean saveSubmission(Interview con);
	
	public List<Object[]> getsubdet();
	
	public List<Interview> getAllDetailsInterview();
	
	public List<Interview> getAllInterviews();
	
	public Interview getInterviewByID(long id);
	
	public boolean deleteInterviewById(long id);

	public Page<List<Interview>> findPaginated(int pageNo, int pageSize);

	public List<Interview> getAllInterviewBasedOnFilter(String keyword);
	
	public SalesConsultant getSalesConsById(long conid);

	public int changeStatus(String status, Long id,String remarks);
	
}
