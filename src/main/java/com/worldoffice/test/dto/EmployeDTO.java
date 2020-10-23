package com.worldoffice.test.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
/**
 * 
 * @author srcortes
 *
 */
@Data
public final class EmployeDTO implements Serializable {
	private static final long serialVersionUID = 1L;	
	private long idEmploye;	
	private String fullName;	
	private String position;	
	private String salary;	
	private String fullTime;
	private DepartmentDTO departmentDTO;
}
