package com.narvee.usit.serviceimpl;
import java.io.FileNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.narvee.usit.entity.SalesConsultant;
import com.narvee.usit.helper.SalesConsultantHelper;
import com.narvee.usit.repository.ISalesConsultantsRepository;
import com.narvee.usit.service.ISalesConsultantService;
@Service
@Transactional
public class SalesConsultantsServiceImpl implements ISalesConsultantService {
	
	@Autowired
	private ISalesConsultantsRepository iConsultantsRepo;
	
	@Override
	public boolean saveConsultantss(SalesConsultant con) {		 
		SalesConsultant sc= iConsultantsRepo.save(con);
		if(sc!=null)
		return true;
		else
		return false; 
	}

	@Override
	public List<SalesConsultant> findAllConsultants() {		 
		  List<SalesConsultant> findAllReq = iConsultantsRepo.findAll();
		  System.out.println("Con: "+findAllReq);
		return findAllReq;
	}

	@Override
	public SalesConsultant getbyId(long id) {
		return iConsultantsRepo.findById(id).get();
	}

	@Override
	public boolean deleteById(long id) {
				iConsultantsRepo.deleteById(id);
				return true;
	}
	
	@Override
	public int changeStatus(String status, long id,String rem) {
		return iConsultantsRepo.toggleStatus(status, id,rem);
	}

	@Override
	public SalesConsultant getConsultantByid(long id) {
		SalesConsultant findById = iConsultantsRepo.getConsultantById(id);
		return findById;
	}

	@Override
	public SalesConsultant getFile(long fileId) throws FileNotFoundException {
        return iConsultantsRepo.findById(fileId).orElseThrow(() -> new FileNotFoundException("File does not exist" + fileId));
    }	

	@Override
	public List<SalesConsultant> findSalesConsultants() {		 
				return iConsultantsRepo.findAll();
			
	}
	
	@Override
	public Page<SalesConsultant> findPaginated(int pageNo,int pageSize) {
	Pageable pageable = PageRequest.of(pageNo-1, pageSize);	
	Page<SalesConsultant> findAll = iConsultantsRepo.findAll(pageable);		 
    return findAll;
	}

	@Override
	public List<SalesConsultant> findskilset() {
		return iConsultantsRepo.findconsultantexp();
	}

	@Override
	public List<SalesConsultant> findAllSalesConsultants() {
		return iConsultantsRepo.findAllConsultants();
	}	 

}
