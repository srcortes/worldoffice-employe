package com.worldoffice.test.constants;

public enum ConstantGeneral {
	MESSAGE_INSERTION("INSERTED RECORDS -> ");
	private String value;
	private ConstantGeneral(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
