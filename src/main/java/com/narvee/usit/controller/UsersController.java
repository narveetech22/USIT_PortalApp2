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
import com.narvee.usit.entity.Users;
import com.narvee.usit.service.IUserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/usit/users")
@CrossOrigin//(origins = "http://localhost:4200")
public class UsersController {

	@Autowired
	private IUserService iUserService;

	@ApiOperation("To save Users")
	@ApiResponses({ @ApiResponse(code = 200, message = "User saved"),
			@ApiResponse(code = 404, message = " entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> addUsers(@RequestBody Users users) {
		Users email = iUserService.findUserByEmail(users.getEmail());
		if (email == null) {
			Users saveUser = iUserService.saveUser(users);
			return new ResponseEntity<>(new RestAPIResponse("Success", "User Saved", saveUser), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(new RestAPIResponse("Fail", "User Already Exist", "User Email Already Exist"),
					HttpStatus.OK);
		}
	}

	@ApiOperation("To Update Users")
	@ApiResponses({ @ApiResponse(code = 200, message = "User saved"),//c3roqYbE
			@ApiResponse(code = 404, message = " entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> updateUsers(@RequestBody Users users) {
		Users email = iUserService.findUserByEmailandUid(users.getEmail(), users.getUserid());
		if (email == null) {
			Users saveUser = iUserService.saveUser(users);
			return new ResponseEntity<>(new RestAPIResponse("Success", "User Updated", saveUser), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(new RestAPIResponse("Fail", "User Already Exist", "User Email Already Exist"),
					HttpStatus.OK);
		}
	}
	
	@ApiOperation("To Fetch All Users")
	@ApiResponses({ @ApiResponse(code = 200, message = "Users Fetched"),
			@ApiResponse(code = 404, message = " Users not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/users-list", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getUsers() {
		List<Users> listall = iUserService.getAllUsers();
		return new ResponseEntity<>(new RestAPIResponse("Fail", "User Already Exist", listall),
				HttpStatus.OK);
	}
	
	@ApiOperation("To Fetch user By Id")
	@ApiResponses({ @ApiResponse(code = 200, message = "Users Fetched"),
			@ApiResponse(code = 404, message = " Users not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/userbyId/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getUserByid(@PathVariable Long id) {
		return new ResponseEntity<>(new RestAPIResponse("Fail", "User Details", iUserService.finduserById(id)),
				HttpStatus.OK);
	}
	
	// to check authorization
	@ApiOperation("To delete Users")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS saved"),
			@ApiResponse(code = 404, message = " entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> editUser(@PathVariable Long id) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "User Deleted", iUserService.deleteUsers(id)),
				HttpStatus.OK);
	}

	@ApiOperation("Checking ")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS saved"),
			@ApiResponse(code = 404, message = " entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteUser() {
		System.out.println("Inside Delete User");
		return null;
	}
	
	
	@ApiOperation("Vms Status Change ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Change VMS Status"),
			@ApiResponse(code = 404, message = "Status not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value="/status",method = RequestMethod.PATCH, produces = "application/json")
	public ResponseEntity<RestAPIResponse> changeStatus(@RequestBody Users users){
		System.out.println(users);
		Long id = users.getUserid();
		 String status =users.getStatus();
		 String remarks = users.getRemarks();
		 
			int changestat= 0;
			String result;
			if(status.equals("Active")) 
				result = "InActive";
			else 
				result = "Active";
			changestat = iUserService.changeStatus(result, id,remarks);
			if(changestat!=0) {
			System.out.println("status Successfully");
			}
			else
			{
				System.out.println("Not Chnaged");
			}
			iUserService.changeStatus(result, id, remarks);
			return new ResponseEntity<>(new RestAPIResponse("Success", "Status Change Successfully","Done" ),HttpStatus.OK);
	}
	
	@ApiOperation("To Fetch Employee By filter")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Fetched Records"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/search/{keyword}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> filterVMS(@PathVariable String keyword) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Employee Fetched Records", iUserService.filterEmployee(keyword)), HttpStatus.OK);
	}

}
