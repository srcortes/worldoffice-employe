package com.worldoffice.test.dao;

import com.worldoffice.test.exception.ManagerApiException;
import java.util.HashSet;
import java.util.List;

import com.worldoffice.test.dto.DepartmentDTO;
import com.worldoffice.test.dto.EmployeDTO;
import com.worldoffice.test.dto.PooledSalaryDTO;
/**
 * 
 * @author srcortes
 *
 */
public interface EmployeDAO {
	void createEmploye(EmployeDTO employeDTO) throws ManagerApiException;
	void createDepartment(HashSet<DepartmentDTO> departmentDTO) throws ManagerApiException;
	List<EmployeDTO> salaryEmploye() throws ManagerApiException;
	List<PooledSalaryDTO> listPooledSalary() throws ManagerApiException;
}
