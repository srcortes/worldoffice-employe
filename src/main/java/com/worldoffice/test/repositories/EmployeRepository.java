package com.worldoffice.test.repositories;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.worldoffice.test.dao.EmployeDAO;
import com.worldoffice.test.dto.DepartmentDTO;
import com.worldoffice.test.dto.EmployeByDepartmentDTO;
import com.worldoffice.test.dto.EmployeDTO;
import com.worldoffice.test.dto.PooledSalaryDTO;
import com.worldoffice.test.exception.ManagerApiException;
/**
 * 
 * @author srcortes
 *
 */
@Repository
public class EmployeRepository implements EmployeDAO{
	NamedParameterJdbcTemplate template;
	public EmployeRepository(NamedParameterJdbcTemplate template) {
		this.template = template;
	}
	@Override
	public void createEmploye(EmployeDTO employeDTO) throws ManagerApiException {
		KeyHolder holder = new GeneratedKeyHolder();
		Random rand = new Random();
		SqlParameterSource param = new MapSqlParameterSource()				
				.addValue("ID_DEPARTMENT", employeDTO.getDepartmentDTO().getIdDepartment())
				.addValue("FULL_NAME", employeDTO.getFullName())
				.addValue("POSITION", employeDTO.getPosition())
				.addValue("SALARY", Double.valueOf(employeDTO.getSalary()))
				.addValue("FULL_TIME", employeDTO.getFullTime());
		template.update("INSERT INTO MANAGER.EMPLOYE (ID_DEPARTMENT, FULL_NAME, POSITION, SALARY, FULL_TIME) VALUES (:ID_DEPARTMENT, :FULL_NAME, :POSITION, :SALARY, :FULL_TIME)", param, holder);
	}
	@Override
	public void createDepartment(HashSet<DepartmentDTO> departmentDTO) throws ManagerApiException {
		departmentDTO.forEach(j->{
			KeyHolder holder = new GeneratedKeyHolder();
			SqlParameterSource param = new MapSqlParameterSource()
					.addValue("ID_DEPARTMENT", j.getIdDepartment())
					.addValue("NAME_DEPARTMENT", j.getNameDepartment());
			template.update("INSERT INTO MANAGER.DEPARTMENT (ID_DEPARTMENT, NAME_DEPARTMENT) VALUES (:ID_DEPARTMENT, :NAME_DEPARTMENT)", param, holder);
		});		
	}
	@Override
	public List<EmployeDTO> salaryEmploye() throws ManagerApiException {
		final String sqlListSalary = "SELECT ID_EMPLOYE, D.ID_DEPARTMENT, D.NAME_DEPARTMENT, FULL_NAME, POSITION, SALARY, FULL_TIME FROM MANAGER.EMPLOYE E " + 
				" JOIN  MANAGER.DEPARTMENT D ON E.ID_DEPARTMENT = D.ID_DEPARTMENT ORDER BY SALARY DESC  LIMIT 5";
		return template.query(sqlListSalary, (rs, rowNumber) -> {
			EmployeDTO employeDTO = new EmployeDTO();
			employeDTO.setIdEmploye(rs.getLong("ID_EMPLOYE"));
			employeDTO.setFullName(rs.getString("FULL_NAME"));
			employeDTO.setPosition(rs.getString("POSITION"));
			employeDTO.setSalary(rs.getString("SALARY"));
			
			return employeDTO;
		});		
	}
	@Override
	public List<PooledSalaryDTO> listPooledSalary() throws ManagerApiException {
		final String sqlListSalaryByDepartemnt = "SELECT SUM(SALARY) AS  SUM, D.NAME_DEPARTMENT  FROM MANAGER.EMPLOYE E " + 
				" JOIN  MANAGER.DEPARTMENT D ON E.ID_DEPARTMENT = D.ID_DEPARTMENT GROUP BY (D.NAME_DEPARTMENT) ORDER BY D.NAME_DEPARTMENT ASC";
		return template.query(sqlListSalaryByDepartemnt, (rs, rowNumber) -> {
			PooledSalaryDTO pooled = new PooledSalaryDTO();
			pooled.setSalary(rs.getDouble("SUM"));
			pooled.setNameDepartment(rs.getString("NAME_DEPARTMENT"));
			
			return pooled;			
		});
	}
	@Override
	public List<EmployeByDepartmentDTO> listEmployeByDepartment(Long idDepartment) throws ManagerApiException {
		final String sqlListByDepartment = "SELECT E.FULL_NAME, D.NAME_DEPARTMENT  FROM MANAGER.EMPLOYE E " + 
				" JOIN  MANAGER.DEPARTMENT D ON E.ID_DEPARTMENT = D.ID_DEPARTMENT WHERE E.ID_DEPARTMENT = "+idDepartment+"";
		return template.query(sqlListByDepartment, (rs, rowNumber) -> {
			EmployeByDepartmentDTO employeByDepartmentDTO = new EmployeByDepartmentDTO();
			employeByDepartmentDTO.setFullName(rs.getString("FULL_NAME"));
			employeByDepartmentDTO.setNameDepartment(rs.getString("NAME_DEPARTMENT"));
			return employeByDepartmentDTO;
		});
	}
}
