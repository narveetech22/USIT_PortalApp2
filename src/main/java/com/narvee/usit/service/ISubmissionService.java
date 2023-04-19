package com.narvee.usit.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.narvee.usit.entity.SalesSubmission;
public interface ISubmissionService {
	public Boolean saveSubmission(SalesSubmission con);
	public List<SalesSubmission> findAllSubmission();
	public Page<SalesSubmission> findPaginated(int pageNo,int pageSize,String search);	 
	public List<SalesSubmission> dupsubmission(long id, String loc, String client);
	public SalesSubmission getSubmissionByID(long sid);
	public boolean deleteSalesSubmission(long sid);
	public List<SalesSubmission> findallsubmission();

}
