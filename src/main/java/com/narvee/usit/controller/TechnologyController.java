package com.narvee.usit.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.narvee.usit.commons.HttpResponse;
import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.commons.RestAPIResponse2;
import com.narvee.usit.entity.Technologies;
import com.narvee.usit.entity.Vms;
import com.narvee.usit.service.ITechnologyService;
import static java.util.Map.of;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;
@RestController
@RequestMapping("/usit/tech")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin
public class TechnologyController {

	@Autowired
	public ITechnologyService service;

	@ApiOperation("To save technology")
	@ApiResponses({ @ApiResponse(code = 200, message = "Technlogy saved"),
			@ApiResponse(code = 404, message = "Technology entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/technologies", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveTechnology(@RequestBody Technologies technologies) {
		System.out.println(technologies);
		return new ResponseEntity<>(
				new RestAPIResponse("success", "Technology Saved", service.saveTechnologies(technologies)),
				HttpStatus.OK);
	}

	@ApiOperation("To fetch technology")
	@ApiResponses({ @ApiResponse(code = 200, message = "Technlogy fetched"),
			@ApiResponse(code = 404, message = "Technology entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")

	public ResponseEntity<RestAPIResponse> getall() {
		List<Technologies> allTechnologies = service.getAllTechnologies();
		//List<Object[]> allTechnologies2 = service.getAllTechnologies2();;
		return new ResponseEntity<>(new RestAPIResponse("success", "Technology Saved", allTechnologies),
				HttpStatus.OK);
	}
	
	@ApiOperation("To update technology")
	@ApiResponses({ @ApiResponse(code = 200, message = "Technlogy saved"),
			@ApiResponse(code = 404, message = "Technology entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/technologies", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestAPIResponse> updateTechnology(@RequestBody Technologies technologies) {
		System.out.println(technologies);
		return new ResponseEntity<>(
				new RestAPIResponse("success", "Technology Saved", service.saveTechnologies(technologies)),
				HttpStatus.OK);
	}

	@ApiOperation("To fetch Technology By ID ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Technlogy fetched"),
			@ApiResponse(code = 404, message = "Technology entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/technologies/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getTechnologyByID(@PathVariable int id) {
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Get Technology By ID", service.getTechnologyByID(id)), HttpStatus.OK);
	}

	@ApiOperation("To Edit Technology By ID ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Technlogy updated"),
			@ApiResponse(code = 404, message = "Technology entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/updatetech", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> updateTechnologyByID(@RequestBody Technologies technologies) {
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "updated Success", service.saveTechnologies(technologies)),
				HttpStatus.OK);
	}

	@ApiOperation("To delete Technology By ID ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Technlogy Deleted"),
			@ApiResponse(code = 404, message = "Technology entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteTechnologyByID(@PathVariable int id) {
		return new ResponseEntity<>(
				new RestAPIResponse("success", "deleted Successfully", service.deleteTechnologiesById(id)),
				HttpStatus.OK);
	}
	
	@ApiOperation("To fetch VMS By PageNo ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched VMS"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/vms1/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse2> findPaginated(@PathVariable("pageNo") int pageNo) {
		int pageSize = 5;
		
		Page<List<Technologies>> findPaginated = service.findPaginated(pageNo, pageSize);
		List<List<Technologies>> findAlltech = findPaginated.getContent();
		int totalPages = findPaginated.getTotalPages();
		return new ResponseEntity<>(new RestAPIResponse2("success","fetching VMS By Page No",totalPages,findAlltech),HttpStatus.OK);
	}
	
	   @GetMapping("/all/{pageno}")
	    public ResponseEntity<HttpResponse>getUsers(@PathVariable("pageno") int pageNo) throws InterruptedException {
	        TimeUnit.SECONDS.sleep(3);
	        int pageSize = 5;
	        //throw new RuntimeException("Forced exception for testing");
	        Page<List<Technologies>> findPaginated = service.findPaginated(pageNo, pageSize);
			List<List<Technologies>> findAlltech = findPaginated.getContent();
			
	        return ResponseEntity.ok().body(
	                HttpResponse.builder()
	                        .timeStamp(now().toString())
	                        .data(of("page",findAlltech))
	                        .message("Users Retrieved")
	                        .status(OK)
	                        .statusCode(OK.value())
	                        .build());

	    }
	   
	   @ApiOperation("Status Change ")
		@ApiResponses({ @ApiResponse(code = 200, message = "Change VMS Status"),
				@ApiResponse(code = 404, message = "Status not found"),
				@ApiResponse(code = 500, message = "Internal Server error") })
		@RequestMapping(value="/status",method = RequestMethod.PATCH, produces = "application/json")
		public ResponseEntity<RestAPIResponse> changeStatus(@RequestBody Technologies technologies){
			int id = technologies.getId();
			 String status =technologies.getStatus();
			 String remarks = technologies.getRemarks();
			 
				int changestat= 0;
				String result;
				if(status.equals("Active")) 
					result = "InActive";
				else 
					result = "Active";
				changestat = service.changeStatus(result, id,remarks);
				if(changestat!=0) {
				System.out.println("status Successfully");
				}
				else
				{
					System.out.println("Not Chnaged");
				}
				  service.changeStatus(result, id, remarks);
				return new ResponseEntity<>(new RestAPIResponse("Success", "Status Change Successfully","Done" ),HttpStatus.OK);
		}
	
}
