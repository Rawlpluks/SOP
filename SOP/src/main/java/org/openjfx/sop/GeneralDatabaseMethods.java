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
import java.util.ArrayList;

/**
 *
 * @author clara
 */
public class GeneralDatabaseMethods {
    
    private final String connectionString = "jdbc:sqlite:mydatabase.sqlite";
    
    //load patients
    private ArrayList<Patient> loadPatients(ResultSet rs, Connection conn) throws SQLException, Exception{
        ArrayList<Patient> loadedPatients = new ArrayList<>();
    //get patient info
        try{
            while(rs.next()){
              loadedPatients.add(new Patient(rs.getInt("patient_ID"),rs.getString("name"),rs.getString("cpr"),rs.getString("reference"), null));
            }
        } catch (SQLException e) {
            System.out.println("\n Database error (load patient (info):" + e.getMessage() + "\n");
        }
    //get patients pictures
    for(Patient patient : loadedPatients){
        try {
            Statement stat = conn.createStatement();
            rs = stat.executeQuery("SELECT * FROM pictures WHERE patient_ID = ('" + patient.getPatient_ID() + "');");
            ArrayList<Picture> pictures = loadPictures(rs,conn);
            
            patient.setPictures(pictures);
        }catch (SQLException e){
            System.out.println("\n Database error (load patients (picture ID's): " + e.getMessage() + "\n");
        }
    }
    return loadedPatients;
    }
    
    //load patient
    private Patient loadPatient (int _patient_ID, Connection conn) throws SQLException, Exception {
        Patient patient = new Patient();
        
        try{
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM patients WHERE patient_ID = ('" + _patient_ID + "');");
            patient = new Patient(_patient_ID, rs.getString("name"), rs.getString("cpr"), rs.getString("reference"),null);
        } catch (SQLException e){
            System.out.println("\n Database error (load patient (info) :" + e.getMessage() + "\n");
        }
        try{
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM picture WHERE patient_ID = ('" + _patient_ID + "');");
            ArrayList<Picture> pictures = loadPictures(rs,conn);
            
            patient.setPictures(pictures);
           
        } catch (SQLException e) {
            System.out.println("\n Database error (load patient (picture ID's):" + e.getMessage() + "\n");
        }
        return patient;
    }
    
   public int getPatient_ID(String patientName) throws Exception{
       Connection conn = null;
        Class.forName("org.sqlite.JDBC");
        
        try{
            conn = DriverManager.getConnection(connectionString);
        }catch (SQLException e) {
            System.out.println("\n Database error (check for matching patient): " + e.getMessage() + "\n");
        }
       
       int patient_ID = 0;
       try{
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT patient_ID FROM patients WHERE name = ('" + patientName + "');");
            patient_ID = rs.getInt("patient_ID");
        } catch (SQLException e){
            System.out.println("\n Database error (load patient (info) :" + e.getMessage() + "\n");
        }
       conn.close();
       return patient_ID;
       
   }
    
    
    public boolean checkFormatchingPatient(String _name) throws SQLException, Exception {
        String databaseName = " ";
        _name = _name.toLowerCase();
        
        Connection conn = null;
        Class.forName("org.sqlite.JDBC");
        
        try{
            conn = DriverManager.getConnection(connectionString);
        }catch (SQLException e) {
            System.out.println("\n Database error (check for matching patient): " + e.getMessage() + "\n");
        }
        try{
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT name FROM patients WHERE name = ('" + _name + "');");
            rs.next();
            databaseName = rs.getString("name");
            rs.close();
        }catch (SQLException e) {
            System.out.println("\n Database error (check for matching patient): " + e.getMessage() + "\n");
        }
        if(_name.equalsIgnoreCase(databaseName)){
            return true;
        }
        conn.close();
        return false;
    }
    
    public void createPatient(Patient _newPatient) throws SQLException, Exception {
      Connection conn = null;
        Class.forName("org.sqlite.JDBC");
        String sql;
        
        _newPatient.setName(_newPatient.getName().toLowerCase());
        
        //Skab forbindelse
        try {
            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            System.out.println("\n Database error (create patient): " + e.getMessage() + "\n");
        }
        sql = "INSERT INTO patients(name, cpr, reference) VALUES ('" + _newPatient.getName() + 
                "','" + _newPatient.getCPR() + "','" + _newPatient.getReference() + "');";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("\n Database error (create patient): " + e.getMessage() + "\n");
        }
        conn.close();
    }
    
    public ArrayList<Patient> getAllPatients () throws SQLException, Exception{
        Connection conn = null;
        Class.forName("org.sqlite.JDBC");
        try{
            conn = DriverManager.getConnection(connectionString);
        }catch (SQLException e) {
            System.out.println("\n Database error (get all patients): " + e.getMessage() + "\n");
        }
        ArrayList<Patient> allPatients = new ArrayList<>();
        try{
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM patients;");
            allPatients = loadPatients(rs, conn);
            rs.close();
        }catch (SQLException e){
            System.out.println("\n Database error (get all patients): " + e.getMessage() + "\n");
        }
        conn.close();
        return allPatients;
    }
    
    public void deletePatient(int _patient_ID) throws SQLException, Exception {
        Connection conn = null;
        Class.forName("org.sqlite.JDBC");

        try {
            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            System.out.println("\n Database error (delete patient): " + e.getMessage() + "\n");
        }
        //delete  event info
        String sql = "DELETE FROM patients WHERE patient_ID = ('" + _patient_ID + "');";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("\n Database error (delete patient): " + e.getMessage() + "\n");
        }
        conn.close();
    }
    
     //load patients
    private ArrayList<Picture> loadPictures(ResultSet rs, Connection conn) throws SQLException, Exception{
        ArrayList<Picture> loadedPictures = new ArrayList<>();
    //get patient info
        try{
            while(rs.next()){
              loadedPictures.add(new Picture(rs.getInt("picture_ID"),rs.getString("titel"), rs.getInt("patient_ID")));
            }
        } catch (SQLException e) {
            System.out.println("\n Database error (load picture :" + e.getMessage() + "\n");
        }
    return loadedPictures;
    }
    
    //load patient
    private Picture loadPicture (int _picture_ID, Connection conn) throws SQLException, Exception {
        Picture picture = new Picture();
        
        try{
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM pictures WHERE picture_ID = ('" + _picture_ID + "');");
            picture = new Picture(rs.getInt("picture_ID"), rs.getString("titel"), rs.getInt("patient_ID"));
        } catch (SQLException e){
            System.out.println("\n Database error (load picture :" + e.getMessage() + "\n");
        }
        return picture;
    }
    
    public ArrayList<Picture> getAllPictures() throws SQLException, Exception{
        Connection conn = null;
        Class.forName("org.sqlite.JDBC");
        try{
            conn = DriverManager.getConnection(connectionString);
        }catch (SQLException e) {
            System.out.println("\n Database error (get all pictures): " + e.getMessage() + "\n");
        }
        ArrayList<Picture> allPictures = new ArrayList<>();
        try{
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM pictures;");
            allPictures = loadPictures(rs, conn);
            rs.close();
        }catch (SQLException e){
            System.out.println("\n Database error (get all picture): " + e.getMessage() + "\n");
        }
        conn.close();
        return allPictures;
    }
    
    public void deletePicture (int _picture_ID) throws SQLException, Exception {
        Connection conn = null;
        Class.forName("org.sqlite.JDBC");

        try {
            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            System.out.println("\n Database error (delete picture): " + e.getMessage() + "\n");
        }
        //delete  event info
        String sql = "DELETE FROM pictures WHERE picture_ID = ('" + _picture_ID + "');";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("\n Database error (delete picture): " + e.getMessage() + "\n");
        }
        conn.close();
    }
    
    public void savePicture(Picture _newPicture) throws SQLException, Exception {
        Connection conn = null;
        Class.forName("org.sqlite.JDBC");
        String sql;
        
        _newPicture.setTitel(_newPicture.getTitel().toLowerCase());
        
        //Skab forbindelse
        try {
            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            System.out.println("\n Database error (create patient): " + e.getMessage() + "\n");
        }
        sql = "INSERT INTO pictures(titel, patient_ID) VALUES ('" + _newPicture.getTitel() + 
                "','" + App.getPatient().getPatient_ID() + "');";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("\n Database error (create picture): " + e.getMessage() + "\n");
        }
        conn.close();
    }
    
    public ArrayList<Consent> loadConsents (ResultSet rs, Connection conn){
          ArrayList<Consent> loadedConsents = new ArrayList<>();
    //get consent
        try{
            while(rs.next()){
              loadedConsents.add(new Consent(rs.getInt("consent_ID"),rs.getString("consent1"), rs.getString("consent2"),
              rs.getString("consent3"),rs.getString("consent4"),rs.getInt("patient_ID")));
            }
        } catch (SQLException e) {
            System.out.println("\n Database error (load picture :" + e.getMessage() + "\n");
        }
    return loadedConsents;
    }
    public void saveConsent(Consent _newConsent) throws SQLException, Exception {
        Connection conn = null;
        Class.forName("org.sqlite.JDBC");
        String sql;
        
        //Skab forbindelse
        try {
            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            System.out.println("\n Database error (create patient): " + e.getMessage() + "\n");
        }
        sql = "INSERT INTO consent(consent1, consent2, consent3, consent4, patient_ID) VALUES ('" + _newConsent.getConsent1() + 
                "','" + _newConsent.getConsent2() + "','" + _newConsent.getConsent3() + "','" + _newConsent.getConsent4() + "','"
                + _newConsent.getPatient_ID() + "');";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("\n Database error (create consent): " + e.getMessage() + "\n");
        }
        conn.close();
    }
    
    public Consent setConsent(int _patient_ID) throws SQLException, Exception{
        Connection conn = null;
        Class.forName("org.sqlite.JDBC");
        
        try{
            conn = DriverManager.getConnection(connectionString);
        }catch (SQLException e) {
            System.out.println("\n Database error (check for matching patient): " + e.getMessage() + "\n");
        }
       
       Consent selectedConsent = null;
       try{
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM consent WHERE patient_ID = ('" + _patient_ID + "');");
            selectedConsent = new Consent(rs.getInt("consent_ID"),rs.getString("consent1"), rs.getString("consent2"), rs.getString("consent3"), rs.getString("consent4"), rs.getInt("patient_ID"));
        } catch (SQLException e){
            System.out.println("\n Database error (load patient (info) :" + e.getMessage() + "\n");
        }
       conn.close();
        return selectedConsent;
    }
}
