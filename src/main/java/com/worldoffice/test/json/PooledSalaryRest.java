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
public final class PooledSalaryRest {
	@JsonProperty("Identificador Departemento")
	private long idDepartment;
	@JsonProperty("Suma Salarios")
	private double salary;
	@JsonProperty("Departamento")
	private String nameDepartment;
}
