package com.narvee.usit.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.narvee.usit.UsitPortalApp2Application;
import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.entity.Roles;
import com.narvee.usit.service.IRoleService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usit/roles")
@CrossOrigin//(origins = "http://localhost:4200")
public class RolesController {
	public static final Logger logger = LoggerFactory.getLogger(RolesController.class);
	@Autowired
	private IRoleService Service;

	@ApiOperation("To save Roles")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS saved"),
			@ApiResponse(code = 404, message = "entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/saveRoles", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> AddRoles(@RequestBody Roles roles) {
		logger.info("inside RolesController.AddRoles()");
		List<String> finaAllRolByRolName = Service.finaAllRolByRolName(roles.getRolename().toLowerCase());
		if (finaAllRolByRolName == null || finaAllRolByRolName.isEmpty()) {
			logger.info("Role saved after checking duplicate records available or not");
			Roles saveroles = Service.saveRole(roles);
			return new ResponseEntity<>(new RestAPIResponse("Success", "Role Saved", saveroles), HttpStatus.CREATED);
		} else {
			logger.info("Role not saved => Role Already Exist");
			return new ResponseEntity<>(new RestAPIResponse("Fail", "Role Already Exist", "Data not Saved"),
					HttpStatus.FORBIDDEN);
		}
	}

	@ApiOperation("To Update Roles")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS saved"),
			@ApiResponse(code = 404, message = "entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/updaterole", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> UpdateRoles(@RequestBody Roles roles) {
		logger.info("inside RolesController.UpdateRoles()");
		Roles finaAllRolByRolName = Service.findbyrolenameandid(roles.getRolename(), roles.getRoleid());
		if (finaAllRolByRolName == null) {
			logger.info("Role Updated after checking duplicate records available or not");
			Roles saveroles = Service.saveRole(roles);
			return new ResponseEntity<>(new RestAPIResponse("Success", "Role Updated", saveroles), HttpStatus.CREATED);
		} else {
			logger.info("Role not Updated => Role Already Exist");
			return new ResponseEntity<>(new RestAPIResponse("Fail", "Role Already Exist", "Data not Saved"),
					HttpStatus.FORBIDDEN);
		}
	}

	@ApiOperation("To get all Roles")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched all roles"),
			@ApiResponse(code = 404, message = "Entities not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/getallrole", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllRoles() {
		logger.info("inside RolesController.getAllRoles()=> fetching all roles");
		List<Roles> saveroles = Service.getAllRoles();
		return new ResponseEntity<>(new RestAPIResponse("Success", "All Roles Fetched", saveroles), HttpStatus.OK);

	}

	@ApiOperation("To get single role")
	@ApiResponses({ @ApiResponse(code = 200, message = "fetched single role"),
			@ApiResponse(code = 404, message = "entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/getrole/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getRole(@PathVariable Long id) {
		logger.info("inside RolesController.getRole()=> fetching single role by id");
		Roles saveroles = Service.getRole(id);
		return new ResponseEntity<>(new RestAPIResponse("Success", "Role Feteched By ID", saveroles), HttpStatus.OK);

	}

	@ApiOperation("To Delete Roles")
	@ApiResponses({ @ApiResponse(code = 200, message = "Role Deleted"),
			@ApiResponse(code = 404, message = "entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/deleterole/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteRole(@PathVariable Long id) {
		logger.info("inside RolesController.deleteRole()=> delete single role by id");
		return new ResponseEntity<>(new RestAPIResponse("Success", "Role Deleted", Service.deleteRole(id)),
				HttpStatus.OK);

	}

}
