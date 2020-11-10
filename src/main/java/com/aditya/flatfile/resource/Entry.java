package com.aditya.flatfile.resource;
/**
 * Entry Class to encapsulate flat file line data.
 * @author aditya
 */
public class Entry {
	
	private String phonenumber;
	private String color;
	private String zipcode;
	private String lastname;
	private String firstname;

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Override
	public String toString() {
		return "Entry [phonenumber = " + phonenumber + ", color = " + color + ", zipcode = " + zipcode + ", lastname = "
				+ lastname + ", firstname = " + firstname + "]";
	}
}