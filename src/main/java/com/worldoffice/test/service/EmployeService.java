package com.worldoffice.test.service;
import java.util.List;

import org.beanio.BeanReader;

import com.worldoffice.test.dto.EmployeDTO;
import com.worldoffice.test.dto.PooledSalaryDTO;
import com.worldoffice.test.exception.ManagerApiException;
import com.worldoffice.test.json.EmployeByDepartmentRest;
import com.worldoffice.test.json.EmployeHighSalaryRest;
import com.worldoffice.test.json.PooledSalaryRest;
/**
 * 
 * @author srcortes
 *
 */
public interface EmployeService {
	void createDepartment() throws ManagerApiException; 	
	String createEmploye() throws ManagerApiException;
	List<EmployeHighSalaryRest> listSalaryHigh()  throws ManagerApiException;
	List<PooledSalaryRest> listPooledSalary() throws ManagerApiException;
	List<EmployeByDepartmentRest> listEmployeByDepartement(Long idDepartment) throws ManagerApiException;
}
