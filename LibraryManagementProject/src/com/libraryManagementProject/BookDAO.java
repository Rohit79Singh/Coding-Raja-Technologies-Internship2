package com.libraryManagementProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

	    private Connection connection;

	    public BookDAO() {
	        connection = DatabaseConnection.getConnection();
	    }

	    public void addBook(Book book) {
	        String sql = "INSERT INTO Books (Title, Author, Genre, IsAvailable) VALUES (?, ?, ?, ?)";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, book.getTitle());
	            stmt.setString(2, book.getAuthor());
	            stmt.setString(3, book.getGenre());
	            stmt.setBoolean(4, book.isAvailable());
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public List<Book> getAllBooks() {
	        List<Book> books = new ArrayList<>();
	        String sql = "SELECT * FROM Books";
	        try (Statement stmt = connection.createStatement(); 
	        		ResultSet rs = stmt.executeQuery(sql)) {
	            while (rs.next()) {
	                Book book = new Book();
	                book.setBookID(rs.getInt("BookID"));
	                book.setTitle(rs.getString("Title"));
	                book.setAuthor(rs.getString("Author"));
	                book.setGenre(rs.getString("Genre"));
	                book.setAvailable(rs.getBoolean("IsAvailable"));
	                books.add(book);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return books;
	    }

	    public void updateBook(Book book) {
	        String sql = "UPDATE Books SET Title = ?, Author = ?, Genre = ?, IsAvailable = ? WHERE BookID = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, book.getTitle());
	            stmt.setString(2, book.getAuthor());
	            stmt.setString(3, book.getGenre());
	            stmt.setBoolean(4, book.isAvailable());
	            stmt.setInt(5, book.getBookID());
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void deleteBook(int bookID) {
	        String sql = "DELETE FROM Books WHERE BookID = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setInt(1, bookID);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}