package com.worldoffice.test.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldoffice.test.dto.DepartmentDTO;

import lombok.Data;

/**
 * 
 * @author srcortes
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class EmployeHighSalaryRest {
	@JsonProperty("Id Asignado")
	private long idEmploye;	
	@JsonProperty("Nombres Completos")
	private String fullName;
	@JsonProperty("Cargo")
	private String position;
	@JsonProperty("Salario")
	private String salary;
	@JsonProperty("Tiempo Completo")
	private String fullTime;
	@JsonProperty("Departamento")
	private DepartmentDTO departmentDTO;
}
