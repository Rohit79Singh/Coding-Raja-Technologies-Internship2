package com.libraryManagementProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BorrowingRecordDAO {

	    private Connection connection;

	    public BorrowingRecordDAO() {
	        connection = DatabaseConnection.getConnection();
	    }

	    public void addBorrowingRecord(BorrowingRecord record) {
	  
	        String sql = "INSERT INTO BorrowingRecords (BookID, PatronID, BorrowDate, IsReturned) VALUES (?, ?, ?, ?)";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setInt(1, record.getBookID());
	            stmt.setInt(2, record.getPatronID());
	            stmt.setDate(3, new java.sql.Date(record.getBorrowDate().getTime()));
	            stmt.setBoolean(4, record.isReturned());
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public List<BorrowingRecord> getAllBorrowingRecords() {
	        List<BorrowingRecord> records = new ArrayList<>();
	        String sql = "SELECT * FROM BorrowingRecords";
	        try (Statement stmt = connection.createStatement(); 
	        		ResultSet rs = stmt.executeQuery(sql)) {
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

	    public void updateBorrowingRecord(BorrowingRecord record) {
	        String sql = "UPDATE BorrowingRecords SET BookID = ?, PatronID = ?, BorrowDate = ?, ReturnDate = ?, IsReturned = ? WHERE RecordID = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setInt(1, record.getBookID());
	            stmt.setInt(2, record.getPatronID());
	            stmt.setDate(3, new java.sql.Date(record.getBorrowDate().getTime()));
	            stmt.setDate(4, new java.sql.Date(record.getReturnDate().getTime()));
	            stmt.setBoolean(5, record.isReturned());
	            stmt.setInt(6, record.getRecordID());
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}