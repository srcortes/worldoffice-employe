package com.worldoffice.test.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.worldoffice.test.dto.DepartmentDTO;
import com.worldoffice.test.dto.EmployeDTO;
import com.worldoffice.test.dto.PooledSalaryDTO;
import com.worldoffice.test.exception.ManagerApiException;
import com.worldoffice.test.json.EmployeHighSalaryRest;
import com.worldoffice.test.json.PooledSalaryRest;
import com.worldoffice.test.repositories.EmployeRepository;
import com.worldoffice.test.service.implement.EmployeServiceImplement;
import com.worldoffice.test.util.IntegrationUtil;

public class EmployeServiceTest {
	private static HashSet<DepartmentDTO> DEPARTMENT_LIST = new HashSet<>();
	private static DepartmentDTO DEPARTMENT_DTO = new DepartmentDTO();
	private static List<EmployeDTO> LIST_EMPLOYE_DTO = new ArrayList<>();
	private static List<PooledSalaryDTO> LIST_POOLED_SALARY_DTO = new ArrayList<>();
	@Mock
	private EmployeRepository employeRepository;
	@InjectMocks
	EmployeServiceImplement employeServiceImplement;
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		DEPARTMENT_DTO.setIdDepartment(Long.valueOf(IntegrationUtil.generateKey()));
		DEPARTMENT_DTO.setNameDepartment("Admin - Executive");
		DEPARTMENT_LIST.add(DEPARTMENT_DTO);
	}
	@Test
	public void createDepartmentTest() throws ManagerApiException {
		Mockito.doNothing().when(employeRepository).createDepartment(DEPARTMENT_LIST);
		employeServiceImplement.createDepartment();
	}
	@Test(expected = ManagerApiException.class)
	public void createDepartmentErrorTest() throws ManagerApiException {		
		Mockito.doThrow(Exception.class).when(employeRepository).createDepartment(Mockito.any(HashSet.class));
		employeServiceImplement.createDepartment();
		fail();
	}
	@Test
	public void createEmployeTest() throws ManagerApiException{
		Mockito.doNothing().when(employeRepository).createEmploye(Mockito.any(EmployeDTO.class));
		employeServiceImplement.createEmploye();
	}
	@Test( expected = ManagerApiException.class)
	public void createEmployeErrorTest() throws ManagerApiException{
		Mockito.doThrow(Exception.class).when(employeRepository).createEmploye(Mockito.any(EmployeDTO.class));
		employeServiceImplement.createEmploye();
		fail();
	}
	@Test
	public void listSalaryHighTest() throws ManagerApiException {
		Mockito.when(employeRepository.salaryEmploye()).thenReturn(LIST_EMPLOYE_DTO);
		List<EmployeHighSalaryRest> employeHighSalaryRest = employeServiceImplement.listSalaryHigh();
		assertNotNull(employeHighSalaryRest);
		assertEquals(employeHighSalaryRest, 1);
	}
	@Test( expected = ManagerApiException.class)
	public void listSalaryHighErrorTest() throws ManagerApiException{
		Mockito.doThrow(Exception.class).when(employeRepository).salaryEmploye();
		employeServiceImplement.listSalaryHigh();
		fail();
	}
	@Test
	public void listPooledSalaryTest() throws ManagerApiException{
		Mockito.when(employeRepository.listPooledSalary()).thenReturn(LIST_POOLED_SALARY_DTO);
		List<PooledSalaryRest> pooledSalaryRest  = employeServiceImplement.listPooledSalary();
		assertFalse(pooledSalaryRest.isEmpty());
		assertNotNull(pooledSalaryRest);
	}
	@Test(expected = ManagerApiException.class)
	public void listPooledSalaryErrorTest() throws ManagerApiException{
		Mockito.doThrow(Exception.class).when(employeRepository).listPooledSalary();
		employeServiceImplement.listPooledSalary();
		fail();		
	}
	
}
