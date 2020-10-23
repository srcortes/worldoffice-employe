package com.worldoffice.test.dictionary.errors;
/**
 * 
 * @author srcortes
 *
 */
public enum DictionaryErrors {
	ERROR_INTERNAL_SERVER("INTERNAL SERVER ERROR CHECK LOG PLEASE.");
	private String descriptionErrors;
	private DictionaryErrors(String descriptionErrors) {
		this.descriptionErrors = descriptionErrors;
	}
	public String getDescriptionError() {
		return descriptionErrors;
	}
}
