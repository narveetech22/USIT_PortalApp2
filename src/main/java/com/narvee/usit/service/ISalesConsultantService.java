package com.narvee.usit.service;
import java.io.FileNotFoundException;
import java.util.List;
import org.springframework.data.domain.Page;
import com.narvee.usit.entity.SalesConsultant;
import com.narvee.usit.helper.SalesConsultantHelper;
public interface ISalesConsultantService {

	public boolean saveConsultantss(SalesConsultant salesConsultants);
	public List<SalesConsultant> findAllConsultants();
	public SalesConsultant getbyId(long id);
	public boolean deleteById(long id);
	public SalesConsultant getConsultantByid(long id);
	public List<SalesConsultant> findskilset();
	public int changeStatus(String status, long id,String remarks);
	public SalesConsultant getFile(long fileId) throws FileNotFoundException, FileNotFoundException;
	public List<SalesConsultant> findSalesConsultants(); 
	public Page<SalesConsultant> findPaginated(int pageNo,int pageSize);	
	public List<SalesConsultant> findAllSalesConsultants(); 
}
