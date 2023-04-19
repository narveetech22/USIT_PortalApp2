package com.narvee.usit.serviceimpl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.narvee.usit.serviceimpl.EmailService;
import com.narvee.usit.entity.TechAndSupport;
import com.narvee.usit.entity.Users;
import com.narvee.usit.repository.IUsersRepository;
import com.narvee.usit.service.IUserService;

@Transactional
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUsersRepository iUsersRepo;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public int changeStatus(String status, Long id,String rem) {
		return iUsersRepo.toggleStatus(status, id,rem);
	}

	public Users saveUser(Users users) {
		
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		         + "0123456789"
		         + "abcdefghijklmnopqrstuvxyz";
		 StringBuilder sb = new StringBuilder(8);
		 for (int i = 0; i < 8; i++) {
			   int index
			    = (int)(AlphaNumericString.length()
			      * Math.random());
			   sb.append(AlphaNumericString
			      .charAt(index));
			  }
		 System.out.println(sb.toString()+" your password is");
		 String generatedpsw = sb.toString(); 
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		m.update(sb.toString().getBytes());
		// commented for mail error
		emailService.sendMailWithInlineResources(users.getEmail(),"Your Narvee Portal Password" , generatedpsw);
		
		byte[] bytes = m.digest();
		
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		String encryptedpassword = s.toString();
		//save encoded password into database
		users.setPassword(encryptedpassword);		
		users.setFirstname(users.getFullname());
		Users saveUser = iUsersRepo.save(users);
		return saveUser;
	}

	@Override
	public Users findUserByEmail(String email) {
		return iUsersRepo.findByEmail(email);
	}

	@Override
	public Users findUserByEmailandUid(String email, Long id) {
		return iUsersRepo.findByEmailAndUseridNot(email, id);
	}

	@Override
	public List<Users> getAllUsers() {
		return iUsersRepo.findAll();
	}

	@Override
	public Users finduserById(Long id) {
		return iUsersRepo.findById(id).get();
	}

	@Override
	public boolean deleteUsers(Long id) {
		 iUsersRepo.deleteById(id);
		 return true;
	}

	@Override
	public List<Users> filterEmployee(String search) {
		List<Users> findAlln=new ArrayList();
		System.out.println(search);
		if (!search.equals("dummysearch")) {
			findAlln = iUsersRepo.getAll(search.trim());
		}
		else if(search.equals("dummysearch"))
		{
			findAlln =  iUsersRepo.findAll();
		}
		else {
			findAlln =  iUsersRepo.findAll();
		}
		return findAlln;
	};

}
