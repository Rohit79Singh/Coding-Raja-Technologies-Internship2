package com.libraryManagementProject;

public class Book {

	private int bookID;
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;
    
    
    
	public Book() {
		super();
	}


	public Book(int bookID, String title, String author, String genre, boolean isAvailable) {
		super();
		this.bookID = bookID;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.isAvailable = isAvailable;
	}


	public int getBookID() {
		return bookID;
	}


	public String getTitle() {
		return title;
	}


	public String getAuthor() {
		return author;
	}


	public String getGenre() {
		return genre;
	}


	public boolean isAvailable() {
		return isAvailable;
	}


	public void setBookID(int bookID) {
		this.bookID = bookID;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
    
	
}
