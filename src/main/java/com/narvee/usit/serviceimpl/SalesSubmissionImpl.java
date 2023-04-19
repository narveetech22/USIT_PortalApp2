package com.narvee.usit.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.narvee.usit.entity.SalesSubmission;
import com.narvee.usit.repository.ISalesSubmissionrepo;
import com.narvee.usit.service.ISubmissionService;

@Service
public class SalesSubmissionImpl implements ISubmissionService{
	
		@Autowired
		private ISalesSubmissionrepo repo;
		
		@Override
		public Boolean saveSubmission(SalesSubmission con) {			 
			 repo.save(con);
			return true;
		}

		@Override
		public List<SalesSubmission> findAllSubmission() {		 
			  List<SalesSubmission> findAllReq = repo.findAll();
			  System.out.println("Con: "+findAllReq);
			return findAllReq;
		
		}
		
		   public static boolean isValidDate(String inDate) {
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        System.out.println(dateFormat+" kkkk");
		        dateFormat.setLenient(false);
		        try {
		            dateFormat.parse(inDate.trim());
		            System.out.println(dateFormat.parse(inDate.trim())+" kiran");
		            
		        } catch (ParseException pe) {
		            return false;
		        }
		        return true;
		    }
		
		@Override
		public Page<SalesSubmission> findPaginated(int pageNo, int pageSize, String search ) { 
			Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
			Page<SalesSubmission> findAll = null;
			if (search != null) {
				boolean flg = isValidDate(search);
				if (flg == true) {
					try {
						LocalDate dateSearch = LocalDate.parse(search.trim());
						//findAll = repo.findAlldbydateq(pageable, dateSearch);
					} catch (DateTimeParseException pe) {
					//	findAll = repo.findAlld(pageable);
					}
				} else {
					//findAll = repo.findAlldbykey(pageable, search.trim());
				}
			} else {
				//findAll = repo.findAlld(pageable);
			}
			return null;
		}

		@Override
		public List<SalesSubmission> dupsubmission(long id, String loc, String client) {
			return repo.findByConsultantidAndProjectlocationAndEndclient(id, loc, client);
		}
		
		@Override
		public SalesSubmission getSubmissionByID(long sid) {
			return repo.findById(sid).get();
		}

		@Override
		public boolean deleteSalesSubmission(long sid) {
			repo.deleteById(sid);
			return true;
		}

		@Override
		public List<SalesSubmission> findallsubmission() {
			// TODO Auto-generated method stub
			return repo.findallSub();
		}

}
