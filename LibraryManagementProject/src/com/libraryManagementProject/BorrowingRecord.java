package com.libraryManagementProject;

import java.sql.Date;

public class BorrowingRecord {

	private int recordID;
    private int bookID;
    private int patronID;
    private Date borrowDate;
    private Date returnDate;
    private boolean isReturned;
    
	public BorrowingRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BorrowingRecord(int recordID, int bookID, int patronID, Date borrowDate, Date returnDate,
			boolean isReturned) {
		super();
		this.recordID = recordID;
		this.bookID = bookID;
		this.patronID = patronID;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
		this.isReturned = isReturned;
	}

	public int getRecordID() {
		return recordID;
	}

	public int getBookID() {
		return bookID;
	}

	public int getPatronID() {
		return patronID;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public boolean isReturned() {
		return isReturned;
	}

	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public void setPatronID(int patronID) {
		this.patronID = patronID;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}
    
	
}
