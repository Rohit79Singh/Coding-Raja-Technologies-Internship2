package com.libraryManagementProject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Reports {

	    private Connection connection;

	    public Reports() {
	        connection = DatabaseConnection.getConnection();
	    }

	    public List<Book> generateBookAvailabilityReport() {
	        List<Book> books = new ArrayList<>();
	        String sql = "SELECT * FROM Books WHERE IsAvailable = TRUE";
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

	    public List<BorrowingRecord> generateBorrowingHistoryReport() {
	        List<BorrowingRecord> records = new ArrayList<>();
	        String sql = "SELECT * FROM BorrowingRecords";
	        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
	            while (rs.next()) {
	                BorrowingRecord record = new BorrowingRecord();
	                record.setRecordID(rs.getInt("RecordID"));
	                record.setBookID(rs.getInt("BookID"));
	                record.setPatronID(rs.getInt("PatronID"));
	                record.setBorrowDate(rs.getDate("BorrowDate"));
	                record.setReturnDate(rs.getDate("ReturnDate"));
	                record.setReturned(rs.getBoolean("IsReturned"));
	                records.add(record);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return records;
	    }

	    public List<Fine> generateFinesReport() {
	        List<Fine> fines = new ArrayList<>();
	        String sql = "SELECT * FROM Fines";
	        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
	            while (rs.next()) {
	                Fine fine = new Fine();
	                fine.setFineID(rs.getInt("FineID"));
	                fine.setRecordID(rs.getInt("RecordID"));
	                fine.setAmount(rs.getDouble("Amount"));
	                fine.setPaid(rs.getBoolean("IsPaid"));
	                fines.add(fine);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return fines;
	    }
	}