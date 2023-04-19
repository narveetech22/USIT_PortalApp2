package com.narvee.usit.serviceimpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.narvee.usit.entity.Vms;
import com.narvee.usit.repository.IVmsRepository;
import com.narvee.usit.service.IVmsService;
@Service
@Transactional
public class VmsServiceImpl implements IVmsService {
	@Autowired
	private IVmsRepository iVmsRepo;

	@Override
	public boolean saveVms(Vms portalVms) {
		Vms save = iVmsRepo.save(portalVms);
		if(save!=null)
			return true;
		else
			return false;
	}

	@Override
	public List<Vms> findAllVms() {
		return iVmsRepo.getallvms();
	}

	@Override
	public Vms getbyId(long id) {
		return iVmsRepo.findById(id).get();
	}

	@Override
	public boolean deleteById(long id) {
		iVmsRepo.deleteById(id);
		return true;	
	}

	@Override
	public List<Vms> dupvendor(String email) {
		List<Vms> vms = iVmsRepo.findByCpemail(email);
		return vms;
	}

//	@Override
	/*
	 * public int changeStatus(String status, int id) { return
	 * iVmsRepo.toggleStatus(status, id); }
	 */

	@Override
	public int changeStatus(int status, Long id,String rem) {
		return iVmsRepo.toggleStatus(status, id,rem);
	}

	@Override
	public List<Vms> findVmsbyid(long id) {
		return iVmsRepo.searchbyid(id);
	}
	
	public static boolean isValidDate(String inDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		try {
			System.out.println("threeeeee");
			dateFormat.parse(inDate.trim());
			System.out.println("fourrr");
		} catch (ParseException pe) {
			System.out.println("five");
			return false;
		}
		return true;
	}
	
	@Override
	public List<Vms> filterVms(String search) {
		List<Vms> findAll=new ArrayList();
		if (search != null || !search.equals("dummysearch")) {
			boolean flg = isValidDate(search);
			if (flg == true) {
				try {
					LocalDate today = LocalDate.parse(search.trim());
					findAll = iVmsRepo.getallvmsbydate(today);
				} catch (DateTimeParseException pe) {
					findAll = iVmsRepo.getallvms();
				}
			} else {
				System.out.println("ooooooooooooooooooo");
				findAll = iVmsRepo.getVMSFilter(search.trim());
			}
		} else {
			findAll =  iVmsRepo.getallvms();
		}
		return findAll;
	}
	
	@Override
	public Page<Vms> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<Vms> findAll = iVmsRepo.getallvms(pageable);
		return findAll;
	}

	@Override
	public void save(MultipartFile file) {
	}

}
