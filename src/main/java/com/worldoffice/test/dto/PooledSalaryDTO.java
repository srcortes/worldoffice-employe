package com.worldoffice.test.dto;
import java.io.Serializable;
import lombok.Data;
/**
 * 
 * @author srcortes
 *
 */
@Data
public final class PooledSalaryDTO implements Serializable  {	
	private static final long serialVersionUID = 1L;
	private double salary;
	private String nameDepartment;
}
