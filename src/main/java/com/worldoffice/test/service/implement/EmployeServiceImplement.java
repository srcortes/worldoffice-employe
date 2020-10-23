package com.worldoffice.test.service.implement;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.beanio.BeanReader;
import org.beanio.StreamFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.worldoffice.test.constant.ConstantFileEmploye;
import com.worldoffice.test.constants.ConstantGeneral;
import com.worldoffice.test.dictionary.errors.DictionaryErrors;
import com.worldoffice.test.dto.DepartmentDTO;
import com.worldoffice.test.dto.EmployeDTO;
import com.worldoffice.test.dto.PooledSalaryDTO;
import com.worldoffice.test.exception.ManagerApiException;
import com.worldoffice.test.json.EmployeHighSalaryRest;
import com.worldoffice.test.json.PooledSalaryRest;
import com.worldoffice.test.repositories.EmployeRepository;
import com.worldoffice.test.service.EmployeService;
import com.worldoffice.test.util.IntegrationUtil;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author srcortes
 *
 */
@Service
@Slf4j
public final class EmployeServiceImplement implements EmployeService {
	@Autowired
	EmployeRepository employeRepository;
	private HashSet<DepartmentDTO> department = new HashSet<>();
	private static final ModelMapper modelMapper = new ModelMapper();
	private static boolean isReady = true;
	@Override
	public void createDepartment() throws ManagerApiException {		
		try {
			StreamFactory factory = StreamFactory.newInstance();
			InputStream in = this.getClass().getResourceAsStream(ConstantFileEmploye.PATH_FILE_CSV.getValue());
			factory.loadResource(ConstantFileEmploye.PATH_FILE_XML.getValue());
			BeanReader reader = factory.createReader(ConstantFileEmploye.NAME_FILE_TAG_XML.getValue(),
					new InputStreamReader(in));
			Object record = null;		
			while ((record = reader.read()) != null) {
				EmployeDTO employeDTO = (EmployeDTO) record;
				DepartmentDTO departmentDTO = new DepartmentDTO();
				departmentDTO.setIdDepartment(Long.valueOf(IntegrationUtil.generateKey()));
				departmentDTO.setNameDepartment(employeDTO.getDepartmentDTO().getNameDepartment().trim());
				department.add(departmentDTO);
			}
			employeRepository.createDepartment(department);
		} catch (Exception ex) {
			log.error(DictionaryErrors.ERROR_INTERNAL_SERVER.getDescriptionError() + ex);
			throw new ManagerApiException(HttpStatus.INTERNAL_SERVER_ERROR,
					DictionaryErrors.ERROR_INTERNAL_SERVER.getDescriptionError(), ex);
		}
	}
	@Override
	public String createEmploye() throws ManagerApiException {
		List<EmployeDTO> listEmploye = new ArrayList<>();

		try {
			StreamFactory factory = StreamFactory.newInstance();
			InputStream in = this.getClass().getResourceAsStream(ConstantFileEmploye.PATH_FILE_CSV.getValue());
			factory.loadResource(ConstantFileEmploye.PATH_FILE_XML.getValue());
			BeanReader reader = factory.createReader(ConstantFileEmploye.NAME_FILE_TAG_XML.getValue(),
					new InputStreamReader(in));
			if (isReady) {
				isReady = false;
				this.createDepartment();
				Object record = null;			
				while ((record = reader.read()) != null) {
					EmployeDTO employeDTO = (EmployeDTO) record;
					EmployeDTO newEmployeDTO = new EmployeDTO();
					for (DepartmentDTO j : department) {
						if (employeDTO.getDepartmentDTO().getNameDepartment().trim().equals(j.getNameDepartment())) {
							DepartmentDTO departmentDTO = new DepartmentDTO();
							departmentDTO.setIdDepartment(j.getIdDepartment());
							newEmployeDTO.setDepartmentDTO(departmentDTO);
						}
					}
					newEmployeDTO.setFullName(employeDTO.getFullName());
					newEmployeDTO.setPosition(employeDTO.getPosition());
					newEmployeDTO.setSalary(employeDTO.getSalary());
					newEmployeDTO.setFullTime(employeDTO.getFullTime());
					employeRepository.createEmploye(newEmployeDTO);
					listEmploye.add(newEmployeDTO);
					listEmploye.size();
				}
			}
		} catch (Exception ex) {
			log.error(DictionaryErrors.ERROR_INTERNAL_SERVER.getDescriptionError() + ex);
			throw new ManagerApiException(HttpStatus.INTERNAL_SERVER_ERROR,
					DictionaryErrors.ERROR_INTERNAL_SERVER.getDescriptionError(), ex);
		}
		return ConstantGeneral.MESSAGE_INSERTION.getValue() + listEmploye.size();
	}
	@Override
	public List<EmployeHighSalaryRest> listSalaryHigh() throws ManagerApiException {
		try {
			List<EmployeDTO> listEmploye = employeRepository.salaryEmploye();
		
			return listEmploye.stream().map(j-> modelMapper.map(j, EmployeHighSalaryRest.class)).collect(Collectors.toList());			
		}catch (Exception ex) {
			log.error(DictionaryErrors.ERROR_INTERNAL_SERVER.getDescriptionError() + ex);
			throw new ManagerApiException(HttpStatus.INTERNAL_SERVER_ERROR,
					DictionaryErrors.ERROR_INTERNAL_SERVER.getDescriptionError(), ex);
		}	
	}
	@Override
	public List<PooledSalaryRest> listPooledSalary() throws ManagerApiException {
		try {
		List<PooledSalaryDTO> pooledList = employeRepository.listPooledSalary();
		return pooledList.stream().map(j-> modelMapper.map(j, PooledSalaryRest.class)).collect(Collectors.toList());
		}catch (Exception ex) {
			log.error(DictionaryErrors.ERROR_INTERNAL_SERVER.getDescriptionError() + ex);
			throw new ManagerApiException(HttpStatus.INTERNAL_SERVER_ERROR,
					DictionaryErrors.ERROR_INTERNAL_SERVER.getDescriptionError(), ex);
		}	
	}
}
