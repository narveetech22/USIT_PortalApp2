package com.narvee.usit.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.narvee.usit.commons.PermissionDTO;
import com.narvee.usit.entity.Previleges;
import com.narvee.usit.entity.Roles;
import com.narvee.usit.repository.IPreviligesRepository;
import com.narvee.usit.repository.IRolesRepository;
import com.narvee.usit.service.IPreviligesService;
import com.narvee.usit.service.IRoleService;

@Service
public class PrevilegesServiceImpl implements IPreviligesService {

	@Autowired
	private IPreviligesRepository iPreRepo;

	@Override
	public Previleges savePrevileges(Previleges prev) {
		return iPreRepo.save(prev);
	}

	@Override
	public List<PermissionDTO> findAllperv() {
		return iPreRepo.listAll();
	}

	@Override
	public List<PermissionDTO> findAllbyuid(Long uid) {
		// TODO Auto-generated method stub
		return iPreRepo.listAllbyuid(uid);
	}

	@Override
	public List<PermissionDTO> findAllbyroleid(Long roleid) {
		// TODO Auto-generated method stub
		return iPreRepo.listAllbyroleid(roleid);
	}

	@Override
	public List<PermissionDTO> findAllbyroleid(Long roleid, String url) {
		// TODO Auto-generated method stub
		return iPreRepo.listAllbyroleid(roleid, url);
	}

	 

	 

}

