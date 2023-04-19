package com.narvee.usit.service;


import java.util.List;

import com.narvee.usit.entity.Roles;

public interface IRoleService {
	public Roles saveRole(Roles roles);
	public List<String> finaAllRolByRolName(String rolename);
	public Roles findbyrolenameandid(String name, Long id);
    public List<Roles> getAllRoles();
	public Roles getRole(Long id);
	public boolean deleteRole(Long id);
}
