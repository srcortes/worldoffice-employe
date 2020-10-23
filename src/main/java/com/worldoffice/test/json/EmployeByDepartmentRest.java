package com.worldoffice.test.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * @author srcortes
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeByDepartmentRest {
	@JsonProperty("Nombre Empleado")
	private String fullName;
	@JsonProperty("Pertenece al departemento")
	private String nameDepartment; 

}
