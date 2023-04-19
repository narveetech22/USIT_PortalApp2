package com.narvee.usit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.entity.Visa;
import com.narvee.usit.service.IVisaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usit/visa")
@CrossOrigin//(origins = "http://localhost:4200")
public class VisaController {
	
	@Autowired
	public IVisaService service;
	
	@ApiOperation("To save Visa")
	@ApiResponses({ @ApiResponse(code = 200, message = "Visa saved"),
			@ApiResponse(code = 404, message = "Visa entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/visa", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveVisa(@RequestBody Visa visa) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Visa Added Successfully", service.saveVisa(visa)),
				HttpStatus.CREATED);
	}
	
	@ApiOperation("To fetch Visa")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetch All Visa"),
			@ApiResponse(code = 404, message = " Visa not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/visa", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllVisa() {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Visa Fetched Successfully", service.getAllVisa()),HttpStatus.OK);
	}
	
	@ApiOperation("To fetch Visa By VisaID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetch Visa By ID"),
			@ApiResponse(code = 404, message = " Visa ID not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/visa/{visaID}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getVisaByID(@PathVariable long visaID) {
		return new ResponseEntity<>(new RestAPIResponse("Success","Visa Fetched Successfully", service.getVisaById(visaID)),HttpStatus.OK);
	}
	
	@ApiOperation("To Edit Visa By VisaID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Edit Visa By ID"),
			@ApiResponse(code = 404, message = " Visa ID not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/vis", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> updateVisaByID(@RequestBody Visa visa) {
		return new ResponseEntity<>(new RestAPIResponse("Success","Update Visa Successfully", service.saveVisa(visa)),HttpStatus.ACCEPTED);
	}
	
	@ApiOperation("To Delete Visa By VisaID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Delete Visa By ID"),
			@ApiResponse(code = 404, message = " Visa ID not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/visa/{visaID}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteVisaByID(@PathVariable long visaID){
		return new ResponseEntity<>(new RestAPIResponse("Success", "Deleted Visa Sucessfully", service.deleteVisaStatus(visaID)),HttpStatus.OK);
	}
	
	@ApiOperation("To fetch Visa By PageNo ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched Visa"),
			@ApiResponse(code = 404, message = "Visa entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/vis/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> findPaginated(@PathVariable("pageNo") int pageNo) {
		int pageSize = 2;
		Page<Visa> findPaginated = service.findPaginated(pageNo, pageSize);
		List<Visa> findAlltech = findPaginated.getContent();
	/*	int totalpages = findPaginated.getTotalPages();
		List<Technologies> findAllTechnologies = service.findAllTechnologies();
		int size= findAllTechnologies.size(); 	*/
		return new ResponseEntity<>(new RestAPIResponse("success","fetching Visa By Page No",findAlltech),HttpStatus.OK);
	}
}
