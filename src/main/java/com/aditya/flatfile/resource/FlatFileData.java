package com.aditya.flatfile.resource;
/**
 * FlatFileData Class to encapsulate multiple flat file line data and error line numbers from a given flat file.
 * @author aditya
 */
public class FlatFileData {
	private String[] errors;
	private Entry[] entries;

	public String[] getErrors() {
		return errors;
	}

	public void setErrors(String[] errors) {
		this.errors = errors;
	}

	public Entry[] getEntries() {
		return entries;
	}

	public void setEntries(Entry[] entries) {
		this.entries = entries;
	}

	@Override
	public String toString() {
		return "FlatFileData [errors = " + errors + ", entries = " + entries + "]";
	}
}