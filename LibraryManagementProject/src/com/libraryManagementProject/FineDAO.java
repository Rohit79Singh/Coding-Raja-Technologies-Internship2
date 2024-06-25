package com.libraryManagementProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FineDAO {

	    private Connection connection;

	    public FineDAO() {
	        connection = DatabaseConnection.getConnection();
	    }

	    public void addFine(Fine fine) {
	        String sql = "INSERT INTO Fines (RecordID, Amount, IsPaid) VALUES (?, ?, ?)";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setInt(1, fine.getRecordID());
	            stmt.setDouble(2, fine.getAmount());
	            stmt.setBoolean(3, fine.isPaid());
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public List<Fine> getAllFines() {
	        List<Fine> fines = new ArrayList<>();
	        String sql = "SELECT * FROM Fines";
	        try (Statement stmt = connection.createStatement(); 
	        		ResultSet rs = stmt.executeQuery(sql)) {
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

	    public void updateFine(Fine fine) {
	        String sql = "UPDATE Fines SET RecordID = ?, Amount = ?, IsPaid = ? WHERE FineID = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setInt(1, fine.getRecordID());
	            stmt.setDouble(2, fine.getAmount());
	            stmt.setBoolean(3, fine.isPaid());
	            stmt.setInt(4, fine.getFineID());
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}