package com.narvee.usit.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.entity.SalesConsultant;
import com.narvee.usit.entity.SalesSubmission;
import com.narvee.usit.entity.Vms;
import com.narvee.usit.service.ISalesConsultantService;
import com.narvee.usit.service.ISubmissionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usit/sales")
@CrossOrigin//(origins = "http://localhost:4200")
public class UsitSalesSubmissionControllerRest {

	@Autowired 
	private ISubmissionService service;
	
	@Autowired
	private ISalesConsultantService iConService;
	 
	@ApiOperation("To Fetch List Of Submissions")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched"),
			@ApiResponse(code = 404, message = "entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/submission-list", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllSubmission() {
		//updateVendor
		//System.out.println("kkk "+service.findAllVms());findallsubmission
		return new ResponseEntity<>(new RestAPIResponse("Success", "Submission Details Fetched Successfully", service.findallsubmission()),HttpStatus.OK);
	}
	
	//to do 
//	@RequestMapping(value = "/dupcheck", method = RequestMethod.GET)
//    public ResponseEntity<RestAPIResponse> getTime(@RequestParam String id,@RequestParam String location,@RequestParam String endclient) {
//		List<SalesSubmission> sales = service.dupsubmission(id, location, endclient);
//		String result;
//		if(sales.isEmpty()) {
//			
//			result = "NotAvailable";
//			return new ResponseEntity<>(new RestAPIResponse("success", "List of Submissions-PageNo: ", result),HttpStatus.OK);
//
//		}
//		else
//		{
//			 result = "Available";
//			 return new ResponseEntity<>(new RestAPIResponse("success", "List of Submissions-PageNo: ", result),HttpStatus.OK);
//
//			 
//		}
		
//		return new ResponseEntity<>(new RestAPIResponse("success", "List of Submissions-PageNo: ", findAllConsultants),HttpStatus.OK);

//	 return new ResponseEntity<>(result, HttpStatus.OK);
//    }
	
	  
	//list of Consultant Sales Submission & search filter  
	@RequestMapping(value="/sub/page/{pageNo}",method = RequestMethod.GET)
	public ResponseEntity<RestAPIResponse> salesSubPaginatedList(
			@PathVariable("pageNo") int pageNo,
			@Param("search") String search 
			) {
		int pageSize = 2; 		
		Page<SalesSubmission> findPaginated =service.findPaginated(pageNo, pageSize,search);
		List<SalesSubmission> findAllConsultants = findPaginated.getContent();  
		return new ResponseEntity<>(new RestAPIResponse("success", "List of Submissions-PageNo: "+ pageNo, findAllConsultants),HttpStatus.OK);
	}    
	
	@RequestMapping(value="/getConsultant",method = RequestMethod.GET)
	public ResponseEntity<RestAPIResponse> salesSubPaginatedListby() {
		 List<SalesConsultant> findskilset = iConService.findskilset();
		return new ResponseEntity<>(new RestAPIResponse("success", "List of Submissions-PageNo: ", findskilset),HttpStatus.OK);
	}  
	
	
	
		  //edit 
		@RequestMapping(value = "editSalesSubmission/{id}",method = RequestMethod.GET,produces = "application/json")
		public ResponseEntity<RestAPIResponse>  editSubmission(@PathVariable("id") long sid){		 
	//	List<Object[]> skilset = iConService.findskilset();		 
		SalesSubmission submission = service.getSubmissionByID(sid); 
		
		if(submission!=null)
			return new ResponseEntity<>(new RestAPIResponse("success","Edit the Submission",submission),HttpStatus.OK);
			else {
				return new ResponseEntity<>(new RestAPIResponse("Fail","Submission id not found",submission),HttpStatus.OK);
				}
	}	

	//add submissions
	@RequestMapping(value="/Addsubmission",method=RequestMethod.POST)
	public ResponseEntity<RestAPIResponse> addSalesSub(@RequestBody SalesSubmission sales){
		Boolean saveSubmission = service.saveSubmission(sales);
		if(saveSubmission)
		return new ResponseEntity<>(new RestAPIResponse("Success","Saved Successfully",true),HttpStatus.OK);
		else
			return new ResponseEntity<>(new RestAPIResponse("Fail","Failed to Save",false),HttpStatus.OK);	
			 
	} 
	
	//delete Record based on id
	@RequestMapping(value="deletesubmission/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<RestAPIResponse> deleteSub(@PathVariable("id") long id){
		boolean deleteSubmission = service.deleteSalesSubmission(id);
		if(deleteSubmission)
		return new ResponseEntity<>(new RestAPIResponse("Success","Deleted Successfully",null),HttpStatus.OK);
		else
			return new ResponseEntity<>(new RestAPIResponse("Fail","Failed to delete",deleteSubmission),HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/dupcheck", method = RequestMethod.GET)
    public ResponseEntity<String> getTime(@RequestParam long id,@RequestParam String location,@RequestParam String client) {
		List<SalesSubmission> sales = service.dupsubmission(id, location, client);
		String result;
		if(sales.isEmpty()) {
			result = "NotAvailable";
		}
		else
		{
			 result = "Available";
		}
	 return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	@ApiOperation("Duplicate Submission Check")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched Entity"),
			@ApiResponse(code = 404, message = "entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/dupcheck", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> findPaginated(@RequestParam long id,@RequestParam String location,@RequestParam String client) {
		List<SalesSubmission> sales = service.dupsubmission(id, location, client);
		String result;
		if(sales.isEmpty()) {
			result = "NotAvailable";
		}
		else
		{
			 result = "Available";
		}
		return new ResponseEntity<>(new RestAPIResponse("Success","Checked Successfully",result),HttpStatus.OK);
	}
	
}
