package com.narvee.usit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.narvee.usit.commons.PermissionDTO;
import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.service.IPreviligesService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping(value="/usit/permissions")
@CrossOrigin//(origins = "http://localhost:4200")
public class PermissionsController {
	
	@Autowired
	private IPreviligesService iprevSer;
	 
	@ApiOperation("To Fetch All permissions")
	@ApiResponses({ @ApiResponse(code = 200, message = "Permitions Controllers"),
	@ApiResponse(code = 404, message = " entity not found"),
	@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/permisions", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse>  actions() {
		List<PermissionDTO> findAll =  iprevSer.findAllperv();
		return new ResponseEntity<>(new RestAPIResponse("Success", "Fetch All Permissions", findAll),HttpStatus.OK);
	}
	
	@ApiOperation("Permissions By Uid")
	@ApiResponses({ @ApiResponse(code = 200, message = "Permissions by UId"),
	@ApiResponse(code = 404, message = "entity not found"),
	@ApiResponse(code = 500, message = "Internal Server error")})
	@RequestMapping(value = "/permitionbyuid/{uid}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse>  byuid(@PathVariable Long uid) {
		List<PermissionDTO> findAll =  iprevSer.findAllbyuid(uid);
		return new ResponseEntity<>(new RestAPIResponse("Success", "Permission By Id", findAll),HttpStatus.OK);
	}
	
	@ApiOperation("Permissions By Roleid")
	@ApiResponses({ @ApiResponse(code = 200, message = "Permission By Roleid"),
	@ApiResponse(code = 404, message = " entity not found"),
	@ApiResponse(code = 500, message = "Internal Server error")})
	@RequestMapping(value = "/permitionsbyrid/{rid}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse>  byroleid(@PathVariable Long rid) {
		List<PermissionDTO> findAll =  iprevSer.findAllbyroleid(rid);
		return new ResponseEntity<>(new RestAPIResponse("Success", "Permission By ROleId", findAll),HttpStatus.OK);
	}
	
	
	@ApiOperation("Permissions check")
	@ApiResponses({ @ApiResponse(code = 200, message = "Permission By Roleid"),
	@ApiResponse(code = 404, message = " entity not found"),
	@ApiResponse(code = 500, message = "Internal Server error")})
	@RequestMapping(value = "/permitionsbyrid", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse>  byroleidcheck(@RequestParam Long rid, @RequestParam String url) {
		List<PermissionDTO> findAll =  iprevSer.findAllbyroleid(rid,url);
		if(findAll==null || findAll.isEmpty()) {
			return new ResponseEntity<>(new RestAPIResponse("Faile", "Not Have Permission to Acces", "Not Authorized"),HttpStatus.UNAUTHORIZED);
			}
		else {
			return new ResponseEntity<>(new RestAPIResponse("Success", "ALlowed User", findAll),HttpStatus.OK);

		}
		
	}

}
