package com.libraryManagementProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatronDAO {
	
	    private Connection connection;

	    public PatronDAO() {
	        connection = DatabaseConnection.getConnection();
	    }

	    public void addPatron(Patron patron) {
	        String sql = "INSERT INTO Patrons (Name, ContactInfo) VALUES (?, ?)";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, patron.getName());
	            stmt.setString(2, patron.getContactInfo());
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public List<Patron> getAllPatrons() {
	        List<Patron> patrons = new ArrayList<>();
	        String sql = "SELECT * FROM Patrons";
	        try (Statement stmt = connection.createStatement(); 
	        		ResultSet rs = stmt.executeQuery(sql)) {
	            while (rs.next()) {
	                Patron patron = new Patron();
	                patron.setPatronID(rs.getInt("PatronID"));
	                patron.setName(rs.getString("Name"));
	                patron.setContactInfo(rs.getString("ContactInfo"));
	                patrons.add(patron);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return patrons;
	    }

	    public void updatePatron(Patron patron) {
	        String sql = "UPDATE Patrons SET Name = ?, ContactInfo = ? WHERE PatronID = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, patron.getName());
	            stmt.setString(2, patron.getContactInfo());
	            stmt.setInt(3, patron.getPatronID());
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void deletePatron(int patronID) {
	        String sql = "DELETE FROM Patrons WHERE PatronID = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setInt(1, patronID);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}