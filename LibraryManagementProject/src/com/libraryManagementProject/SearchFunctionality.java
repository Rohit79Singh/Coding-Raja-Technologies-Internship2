package com.libraryManagementProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchFunctionality {

	    private Connection connection;

	    public SearchFunctionality() {
	        connection = DatabaseConnection.getConnection();
	    }

	    public List<Book> searchBooks(String keyword) {
	        List<Book> books = new ArrayList<>();
	        String sql = "SELECT * FROM Books WHERE Title LIKE ? OR Author LIKE ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, "%" + keyword + "%");
	            stmt.setString(2, "%" + keyword + "%");
	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    Book book = new Book();
	                    book.setBookID(rs.getInt("BookID"));
	                    book.setTitle(rs.getString("Title"));
	                    book.setAuthor(rs.getString("Author"));
	                    book.setGenre(rs.getString("Genre"));
	                    book.setAvailable(rs.getBoolean("IsAvailable"));
	                    books.add(book);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return books;
	    }

	    public List<Patron> searchPatrons(String keyword) {
	        List<Patron> patrons = new ArrayList<>();
	        String sql = "SELECT * FROM Patrons WHERE Name LIKE ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, "%" + keyword + "%");
	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    Patron patron = new Patron();
	                    patron.setPatronID(rs.getInt("PatronID"));
	                    patron.setName(rs.getString("Name"));
	                    patron.setContactInfo(rs.getString("ContactInfo"));
	                    patrons.add(patron);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return patrons;
	    }
	}