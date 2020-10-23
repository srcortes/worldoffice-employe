package com.worldoffice.test.constant;
/**
 * 
 * @author srcortes
 *
 */
public enum ConstantFileEmploye {
	PATH_FILE_XML("employe.xml"), PATH_FILE_CSV("/data/empleados.csv"),NAME_FILE_TAG_XML("employe");
	private String value;
	private ConstantFileEmploye(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
