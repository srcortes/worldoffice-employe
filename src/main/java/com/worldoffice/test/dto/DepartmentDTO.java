package com.worldoffice.test.dto;
import java.io.Serializable;
import lombok.Data;
/**
 * 
 * @author srcortes
 *
 */
@Data
public final class DepartmentDTO implements Serializable {	
	private static final long serialVersionUID = 1L;
	private long idDepartment;
	private String nameDepartment;
	@Override
	public int hashCode() {
		return this.nameDepartment.trim().length();
	}	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)return false;		
		if(obj instanceof DepartmentDTO) {
			DepartmentDTO p = (DepartmentDTO)obj;			
			if(this.nameDepartment.equalsIgnoreCase(p.nameDepartment)) {
				return true;
			}
		}
		return false;
	}
}
