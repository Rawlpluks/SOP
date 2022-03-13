/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx.sop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author clara
 */
public class UserDatabaseMethods {
    
    private final String connectionString = "jdbc:sqlite:mydatabase.sqlite";
    
    //check for matching users
    public boolean checkForMatchingUser(String _username) throws SQLException, Exception {
        String databaseName = " ";
        _username = _username.toLowerCase();
        
        Connection conn = null;
        Class.forName("org.sqlite.JDBC");
        
        //Forbindelse til databasen
        try {
            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            //Fejlhåndtering 
            System.out.println("\n Database error (check for matching user): " + e.getMessage() + "\n");
        }

        try {
            Statement stat = conn.createStatement();

            ResultSet rs = stat.executeQuery("SELECT username from users WHERE username = ('" + _username + "');");

            rs.next();

            databaseName = rs.getString("username");

            rs.close();
        } catch (SQLException e) {
            //Skrive fejlhåndtering her
            System.out.println("\n Database error (check for matching user): " + e.getMessage() + "\n");
        }

        if (_username.equalsIgnoreCase(databaseName)) {
            return true;
        }
        return false;
    }
    //Check for matching password
    public boolean checkForMatchingPassword(String _username, String _password) throws SQLException, Exception {
       String databasePassword = "";
        _username = _username.toLowerCase();

        Connection conn = null;
        Class.forName("org.sqlite.JDBC");

        //Skab forbindelse til databasen...
        try {
            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            //Skrive fejlhåndtering her
            System.out.println("\n Database error (check for matching password): " + e.getMessage() + "\n");
        }

        try {
            Statement stat = conn.createStatement();

            ResultSet rs = stat.executeQuery("select password from users WHERE username = ('" + _username + "');");

            rs.next();

            databasePassword = rs.getString("Password");

            rs.close();
        } catch (SQLException e) {
            //Skrive fejlhåndtering her
            System.out.println("\n Database error (check for matching password): " + e.getMessage() + "\n");
        }

        if (_password.equals(databasePassword)) {
            return true;
        }

        return false; 
    }
    //Get logged in user
    public User getloggedInUser(String _username) throws SQLException, Exception {
        User loggedInUser = new User();
        _username = _username.toLowerCase();

        Connection conn = null;
        Class.forName("org.sqlite.JDBC");

        //Skab forbindelse til databasen...
        try {
            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            //Skrive fejlhåndtering her
            System.out.println("\n Database error (get logged ind user): " + e.getMessage() + "\n");
        }
        
        //get user info
        try {
            Statement stat = conn.createStatement();

            ResultSet rs = stat.executeQuery("select * from users WHERE username = ('" + _username + "');");

            rs.next();

            loggedInUser = new User(rs.getInt("user_ID"), rs.getString("username"), rs.getString("password"), rs.getString("job"), rs.getString("name"), null);

            rs.close();
        } catch (SQLException e) {
            //Skrive fejlhåndtering her
            System.out.println("\n Database error (get logged ind user (info): " + e.getMessage() + "\n");
        }
              
        conn.close();

        return loggedInUser;
    }
    
    //create user
    public void createUser(User _newUser) throws SQLException, Exception {
        Connection conn = null;
        Class.forName("org.sqlite.JDBC");
        String sql;
        
        _newUser.setUsername(_newUser.getUsername().toLowerCase());
        
        //Skab forbindelse
        try {
            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            System.out.println("\n Database error (create user): " + e.getMessage() + "\n");
        }
        sql = "INSERT INTO users(username, password, job, name) VALUES" 
                + "('" + _newUser.getUsername() + "','" + _newUser.getPassword()
                + "','" + _newUser.getJob() + "','" + _newUser.getName() + "');";
        
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("\n Database error (create user): " + e.getMessage() + "\n");
        }
    }
    
}
