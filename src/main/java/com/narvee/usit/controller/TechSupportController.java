package com.narvee.usit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.entity.TechAndSupport;
import com.narvee.usit.service.ITechSupportService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/usit/techsupp")
@CrossOrigin//(origins = "http://localhost:4200")
public class TechSupportController {
	
	@Autowired
	private ITechSupportService Service;

	@ApiOperation("To save Entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Entity saved"),
			@ApiResponse(code = 404, message = "Entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> AddRoles(@RequestBody TechAndSupport techandsupport) {
	  return new ResponseEntity<>(new RestAPIResponse("Success", "Role Already Exist", Service.saveTechSupp(techandsupport)),HttpStatus.CREATED);
	}
	
	@ApiOperation("To get all Entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched all Entity Records"),
			@ApiResponse(code = 404, message = "Entities not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/search/{keyword}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAll(@PathVariable String keyword) {
		List<TechAndSupport> all = Service.getAll(keyword);
		System.out.println(all);
		return new ResponseEntity<>(new RestAPIResponse("Success", "All Roles Fetched", all), HttpStatus.OK);

	}
	
	//@CrossOrigin(origins = "http://localhost:4200")
	@ApiOperation("To Delete Emtity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Entity Deleted"),
			@ApiResponse(code = 404, message = "Entity entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteVMSByID(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Record Deleted Successfully", Service.deleteSupp(id)), HttpStatus.OK);
	}
	
	@ApiOperation("To get Entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Entity Deleted"),
			@ApiResponse(code = 404, message = "Entity entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getTechsupoort(@PathVariable long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Record fetched Successfully", Service.getTechSupp(id)), HttpStatus.OK);
	}
	
	@ApiOperation("To Update Emtity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Entity Deleted"),
			@ApiResponse(code = 404, message = "Entity entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> updateTechsupoort(@RequestBody TechAndSupport techandsupport) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Record Updated Successfully", Service.saveTechSupp(techandsupport)), HttpStatus.OK);
	}
	
	@ApiOperation("Status Change ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Change VMS Status"),
			@ApiResponse(code = 404, message = "Status not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value="/status",method = RequestMethod.PATCH, produces = "application/json")
	public ResponseEntity<RestAPIResponse> changeStatus(@RequestBody TechAndSupport techandsupport){
		Long id = techandsupport.getId();
		 String status =techandsupport.getStatus();
		 String remarks = techandsupport.getRemarks();
		 
			int changestat= 0;
			String result;
			if(status.equals("Active")) 
				result = "InActive";
			else 
				result = "Active";
			changestat = Service.changeStatus(result, id,remarks);
			if(changestat!=0) {
			System.out.println("status Successfully");
			}
			else
			{
				System.out.println("Not Chnaged");
			}
			Service.changeStatus(result, id, remarks);
			return new ResponseEntity<>(new RestAPIResponse("Success", "Status Change Successfully","Done" ),HttpStatus.OK);
	}
	
	

}
