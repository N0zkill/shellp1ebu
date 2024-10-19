package com.example.module03_basicgui_db_interface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnDbOps {
    final String DB_URL = "jdbc:mysql://karacsc311server.mysql.database.azure.com/DBname";
    final String USERNAME = "ebuadmin";
    final String PASSWORD = "Nigel8540@";

    private Connection conn;

    public boolean connectToDatabase() {
        try {
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                    + "firstName VARCHAR(200) NOT NULL,"
                    + "dept VARCHAR(200),"
                    + "major VARCHAR(200)"
                    + ")";
            statement.executeUpdate(sql);
            statement.close();
            System.out.println("Database connected and table created (if not exists).");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Add User
    public void addUser(String firstName, String dept, String major) {
        String sql = "INSERT INTO users (firstName, dept, major) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, dept);
            pstmt.setString(3, major);
            pstmt.executeUpdate();
            System.out.println("User added: " + firstName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update User
    public void updateUser(int id, String firstName, String dept, String major) {
        String sql = "UPDATE users SET firstName = ?, dept = ?, major = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, dept);
            pstmt.setString(3, major);
            pstmt.setInt(4, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User with ID " + id + " updated successfully.");
            } else {
                System.out.println("User with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete User
    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User with ID " + id + " deleted successfully.");
            } else {
                System.out.println("User with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get User by ID
    public Person getUser(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("firstName");
                String dept = rs.getString("dept");
                String major = rs.getString("major");
                return new Person(id, firstName, dept, major);  // Return the Person object
            } else {
                System.out.println("User with ID " + id + " not found.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}



