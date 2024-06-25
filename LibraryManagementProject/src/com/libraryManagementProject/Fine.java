package com.libraryManagementProject;

public class Fine {

	private int fineID;
    private int recordID;
    private double amount;
    private boolean isPaid;
    
	public Fine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Fine(int fineID, int recordID, double amount, boolean isPaid) {
		super();
		this.fineID = fineID;
		this.recordID = recordID;
		this.amount = amount;
		this.isPaid = isPaid;
	}

	public int getFineID() {
		return fineID;
	}

	public int getRecordID() {
		return recordID;
	}

	public double getAmount() {
		return amount;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setFineID(int fineID) {
		this.fineID = fineID;
	}

	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
    
	
}
