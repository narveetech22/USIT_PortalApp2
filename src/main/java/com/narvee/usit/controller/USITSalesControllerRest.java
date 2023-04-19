package com.narvee.usit.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
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
import com.narvee.usit.entity.SalesConsultant;
import com.narvee.usit.service.ISalesConsultantService;
import com.narvee.usit.service.ITechnologyService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping("/usit/sales")
@CrossOrigin//(origins = "http://localhost:4200")
public class USITSalesControllerRest {
	@Autowired
	private ISalesConsultantService iConService;

	@Autowired
	public ITechnologyService service;
	
	
	@ApiOperation("To Fetch List Of consultants")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Fetched"),
			@ApiResponse(code = 404, message = "entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllVMSList() {
		System.out.println(iConService.findAllSalesConsultants());
		return new ResponseEntity<>(new RestAPIResponse("Success", "Consultant Fetched Successfully", iConService.findAllSalesConsultants()),HttpStatus.OK);
	}
	
	
	//save the sales consultants
	@ApiOperation("save the sales consultants")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Fetched"),
			@ApiResponse(code = 404, message = "entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "saveConsultant",method = RequestMethod.POST,produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveCons(@RequestBody SalesConsultant salesConsultants){
		iConService.saveConsultantss(salesConsultants);
		System.out.println(salesConsultants);//getbyId
		return new ResponseEntity<>(new RestAPIResponse("success", "Save Sales Con",iConService.saveConsultantss(salesConsultants)),HttpStatus.OK);
	} 
	
	
	//save the sales consultants
	@ApiOperation("Update the sales consultants")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Fetched"),
			@ApiResponse(code = 404, message = "entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "updateConsultant",method = RequestMethod.POST,produces = "application/json")
	public ResponseEntity<RestAPIResponse> updateCons(@RequestBody SalesConsultant salesConsultants){
		iConService.saveConsultantss(salesConsultants);
		System.out.println(salesConsultants);//getbyId
		return new ResponseEntity<>(new RestAPIResponse("success", "Save Sales Con",iConService.saveConsultantss(salesConsultants)),HttpStatus.OK);
	} 
	
	@ApiOperation("To Fetch Consultant By ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Fetched"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/entitybyid/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getVMSByID(@PathVariable long id) {
		//SalesConsultant salesConsultants = iConService.
		return new ResponseEntity<>(new RestAPIResponse("Success", "VMS Fetched By ID Successfully ", iConService.getbyId(id)),HttpStatus.OK);
	}

	// Get all sales Consultant list
	@RequestMapping(value="/page/{pageNo}",method = RequestMethod.GET,produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllSalesList(@PathVariable("pageNo") int pageNo){
		int pageSize = 4;
		Page<SalesConsultant> findPaginated = iConService.findPaginated(pageNo, pageSize);
		List<SalesConsultant> findAllConsultants = findPaginated.getContent();
		int totalPages = findPaginated.getTotalPages();
		List<SalesConsultant> findAllSalesCon = iConService.findAllConsultants();
		int size = findAllSalesCon.size(); 		
		System.out.println(findAllConsultants);		
		return new ResponseEntity<>(new RestAPIResponse("success", "List of Sales Consultants",findAllConsultants),HttpStatus.OK);
	}
	//delete sales consultants
	@RequestMapping(value="/deletSaleConsultant/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<RestAPIResponse> deleteConsulById(@PathVariable("id") long id){
		boolean deleteById = iConService.deleteById(id);
		if (deleteById) {
			System.out.println("Deleted Successfully");
			return new ResponseEntity<>(new RestAPIResponse("success","Deleted the Record Successfully",deleteById),HttpStatus.OK);
		} else {
			System.out.println("Not Deleted");
			return new ResponseEntity<>(new RestAPIResponse("Fail","Record Not Available ,Failed to delete",deleteById),HttpStatus.OK);
		} 
	}
	//search the data/records based on the field name
	@RequestMapping(value="/searchRecord",method = RequestMethod.GET)
	public ResponseEntity<RestAPIResponse> searchConsultant(HttpServletRequest req){
		String id = req.getParameter("search");		 		
		List<SalesConsultant> filtered = iConService.findAllConsultants();
		System.out.println("All Records: "+ filtered );		
		List<SalesConsultant> findAllSales = filtered.stream()
				.filter(entity -> entity.getFullname().equalsIgnoreCase(id)
						|| entity.getCurrentlocation().equalsIgnoreCase(id) || entity.getPriority().equalsIgnoreCase(id)
						|| entity.getRelocate().equalsIgnoreCase(id) || entity.getTotexp().equalsIgnoreCase(id)
						|| entity.getRelocateval().equalsIgnoreCase(id) || entity.getHourlyrate().equalsIgnoreCase(id)
						|| entity.getStatus().equalsIgnoreCase(id))
				.collect(Collectors.toList()); 
		System.out.println(id + " All Filtered findAllSales" + findAllSales); 
		return new ResponseEntity<>(new RestAPIResponse("Success","Consultant Record Based on Search",findAllSales),HttpStatus.OK);
	}

	@Autowired
	private ServletContext sc; 

	//	to test in postman use below 
	//	http://localhost:8088/usit/sales/download/1052/resume -->Downloads Resume
	//	http://localhost:8088/usit/sales/download/1052/h1b	  -->Downloads H1B	
	//	http://localhost:8088/usit/sales/download/1052/dl	  -->Downloads Driving License
	//  download the file based on id & file type
	@RequestMapping(value="/download/{id}/{filetype}",method = RequestMethod.GET)
	public ResponseEntity<RestAPIResponse> downloadFile(
			HttpServletResponse res, 
			@PathVariable long id, 
			@PathVariable("filetype") String type)
					throws Exception {
		System.out.println(id + " idd " + type);
		String filePath = null;
		SalesConsultant model = iConService.getFile(id);
		if (type.equalsIgnoreCase("resume")) {
			filePath = model.getResumepath();
		}
		if (type.equalsIgnoreCase("h1b")) {
			filePath = model.getH1bcopypath();
		}

		if (type.equalsIgnoreCase("dl")) {
			filePath = model.getDrivinglicencecopypath();
		}
		File file = new File(filePath);
		
		// get the length of the file and make it as the response content length
		res.setContentLengthLong(file.length());
		// get MIME of the file and make it as the respose content type
		String mimeType = sc.getMimeType(filePath);
		mimeType = mimeType == null ? "application/octet-stream" : mimeType;
		res.setContentType(mimeType);
		// create InputStream pointing to the file
		InputStream is = new FileInputStream(file);
		// create OutputStream pointing to response obj
		OutputStream os = res.getOutputStream();
		// instruct the browser to give file content as downloadble file
		res.setHeader("Content-Disposition", "attachment;fileName=" + file.getName());
		// write file content to response obj
		IOUtils.copy(is, os);
		// close streams
		is.close();
		os.close();
		return new ResponseEntity<>(new RestAPIResponse("success","Downloaded file Successfully",os),HttpStatus.OK);
	}
	//Edit status 
	//to test on post man 
	//http://localhost:8088/usit/sales/editstatus?vid=1052&statflg=Active&remarks=Job Done
	//vid= consultant id
	//statflg=give Active  to make Inactive
	//remarks=reason to make active / Inactive---  give the reason to change status

	   @ApiOperation("Status Change ")
		@ApiResponses({ @ApiResponse(code = 200, message = "Change VMS Status"),
				@ApiResponse(code = 404, message = "Status not found"),
				@ApiResponse(code = 500, message = "Internal Server error") })
		@RequestMapping(value="/status",method = RequestMethod.PATCH, produces = "application/json")
		public ResponseEntity<RestAPIResponse> changeStatus(@RequestBody SalesConsultant sales){
			 Long id = sales.getSid();
			 String status = sales.getStatus();
			 String remarks = sales.getRemarks();
			 
				int changestat= 0;
				String result;
				if(status.equals("Active")) 
					result = "InActive";
				else 
					result = "Active";
				changestat = iConService.changeStatus(result, id,remarks);
				if(changestat!=0) {
				  //System.out.println("status Successfully");
				}
				else
				{
				//System.out.println("Not Chnaged");
				}
				iConService.changeStatus(result, id, remarks);
				return new ResponseEntity<>(new RestAPIResponse("Success", "Status Change Successfully","Done" ),HttpStatus.OK);
		}
}
