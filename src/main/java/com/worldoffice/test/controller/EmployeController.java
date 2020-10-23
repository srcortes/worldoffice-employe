package com.worldoffice.test.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.worldoffice.test.exception.ManagerApiException;
import com.worldoffice.test.json.EmployeHighSalaryRest;
import com.worldoffice.test.json.PooledSalaryRest;
import com.worldoffice.test.response.ManagerApiResponse;
import com.worldoffice.test.service.EmployeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
/**
 * 
 * @author srcortes
 *
 */
@RestController
@RequestMapping(produces = "application/json")
@Validated
@CrossOrigin(origins = "*")
public class EmployeController {
	@Autowired
	EmployeService employeService;
	@ApiOperation(notes = "Service is responsable business rule #1", value = "Bussiness Rule#1")
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = Integer.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ManagerApiException.class) })
	@PostMapping(value = "/createEmploye")
	public ManagerApiResponse<String> createEmploye() throws ManagerApiException{
		
		return new ManagerApiResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				employeService.createEmploye());
	}
	@ApiOperation(notes = "Service is responsable business rule #2", value = "Bussiness Rule#2")
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = EmployeHighSalaryRest.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ManagerApiException.class) })
	@GetMapping(value = "/getListSalaryHigh")
	public ManagerApiResponse<List<EmployeHighSalaryRest>> getlistSalary() throws ManagerApiException{
		
		return new ManagerApiResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				employeService.listSalaryHigh());
	}
	@ApiOperation(notes = "Service is responsable business rule #3", value = "Bussiness Rule#3")
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = PooledSalaryRest.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ManagerApiException.class) })
	@GetMapping(value = "/getSalaryByDepartment")
	public ManagerApiResponse<List<PooledSalaryRest>> getSalaryByDepartment() throws ManagerApiException{
		
		return new ManagerApiResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				employeService.listPooledSalary());
	}
}
