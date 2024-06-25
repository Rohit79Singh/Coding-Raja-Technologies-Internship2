package com.libraryManagementProject;

public class Patron {

	private int patronID;
    private String name;
    private String contactInfo;
    
    
	public Patron() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Patron(int patronID, String name, String contactInfo) {
		super();
		this.patronID = patronID;
		this.name = name;
		this.contactInfo = contactInfo;
	}


	public int getPatronID() {
		return patronID;
	}


	public String getName() {
		return name;
	}


	public String getContactInfo() {
		return contactInfo;
	}


	public void setPatronID(int patronID) {
		this.patronID = patronID;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
    
	
}
