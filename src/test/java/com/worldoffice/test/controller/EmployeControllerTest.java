package com.worldoffice.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.worldoffice.test.exception.ManagerApiException;
import com.worldoffice.test.json.EmployeHighSalaryRest;
import com.worldoffice.test.json.PooledSalaryRest;
import com.worldoffice.test.response.ManagerApiResponse;
import com.worldoffice.test.service.EmployeService;

public class EmployeControllerTest {
	private static final String MSN_INSERT_VALUE = "INSERTED RECORDS -> 7454";
	private static final EmployeHighSalaryRest EMPLOYE_SALARY_REST = new EmployeHighSalaryRest();
	private static final PooledSalaryRest POOLED_SALARY_REST = new PooledSalaryRest();
	private List<EmployeHighSalaryRest> LIST_EMPLOYE_SALARY_REST = new ArrayList<>();
	private List<PooledSalaryRest> LIST_POOLED_SALARY_REST = new ArrayList<>();
	private static final String SUCCES_STATUS = "Succes";
	private static final String SUCCES_CODE = "200 OK";
	private static final String SUCCES_MESSAGE = "OK";
	@Mock
	private EmployeService employeService;
	@InjectMocks
	private EmployeController employeController;
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		EMPLOYE_SALARY_REST.setIdEmploye(1);
		EMPLOYE_SALARY_REST.setFullName("Jones Marcus D.");
		EMPLOYE_SALARY_REST.setPosition("City Manager");
		EMPLOYE_SALARY_REST.setSalary("318000.0000");
		LIST_EMPLOYE_SALARY_REST.add(EMPLOYE_SALARY_REST);	
		POOLED_SALARY_REST.setSalary(972102.77D);
		POOLED_SALARY_REST.setNameDepartment("Admin - Administrative Service");
		LIST_POOLED_SALARY_REST.add(POOLED_SALARY_REST);
	}
	@Test
	public void createEmployeTest() throws ManagerApiException {
		Mockito.when(employeService.createEmploye()).thenReturn(MSN_INSERT_VALUE);
		ManagerApiResponse<String> response = employeController.createEmploye();
		assertEquals(SUCCES_STATUS, response.getStatus());
		assertEquals(SUCCES_CODE, response.getCode());
		assertEquals(SUCCES_MESSAGE,response.getMessage());
		assertEquals(MSN_INSERT_VALUE, response.getDataInformation());
		assertNotNull(response);	
		
	}
	@Test
	public void listSalaryTest() throws ManagerApiException{
		Mockito.when(employeService.listSalaryHigh()).thenReturn(LIST_EMPLOYE_SALARY_REST);
		ManagerApiResponse<List<EmployeHighSalaryRest>> response = employeController.getlistSalary();
		assertEquals(SUCCES_STATUS, response.getStatus());
		assertEquals(SUCCES_CODE, response.getCode());
		assertEquals(SUCCES_MESSAGE,response.getMessage());
		assertFalse(response.getDataInformation().isEmpty());
		assertEquals(response.getDataInformation().size(), 1);
	}
	@Test
	public void getSalaryByDepartmentTest() throws ManagerApiException{
		Mockito.when(employeService.listPooledSalary()).thenReturn(LIST_POOLED_SALARY_REST);
		ManagerApiResponse<List<PooledSalaryRest>> response = employeController.getSalaryByDepartment();
		assertEquals(SUCCES_STATUS, response.getStatus());
		assertEquals(SUCCES_CODE, response.getCode());
		assertEquals(SUCCES_MESSAGE,response.getMessage());
		assertFalse(response.getDataInformation().isEmpty());
		assertEquals(response.getDataInformation().size(), 1);	
	}
}
