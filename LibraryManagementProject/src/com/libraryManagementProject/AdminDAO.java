package com.libraryManagementProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

	    private Connection connection;

	    public AdminDAO() {
	        connection = DatabaseConnection.getConnection();
	    }

	    public boolean registerAdmin(Admin admin) {
	        String sql = "INSERT INTO Admins (Username, Password) VALUES (?, ?)";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, admin.getUsername());
	            stmt.setString(2, admin.getPassword());
	            stmt.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public Admin loginAdmin(String username, String password) {
	        String sql = "SELECT * FROM Admins WHERE Username = ? AND Password = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, username);
	            stmt.setString(2, password);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                Admin admin = new Admin();
	                admin.setAdminID(rs.getInt("AdminID"));
	                admin.setUsername(rs.getString("Username"));
	                admin.setPassword(rs.getString("Password"));
	                return admin;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	}