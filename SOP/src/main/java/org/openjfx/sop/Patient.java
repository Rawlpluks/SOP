/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx.sop;

import java.util.ArrayList;

/**
 *
 * @author clara
 */
public class Patient {
    private int patient_ID;
    private String name;
    private String cpr;
    private String reference;
    private ArrayList<Picture> pictures = new ArrayList<>();
 
    public Patient(int patient_ID, String name, String cpr, String reference, ArrayList<Picture> pictures){
        this.patient_ID = patient_ID;
        this.name = name;
        this.cpr = cpr;
        this.reference = reference;
        this.pictures = pictures;        
    }
    public Patient(String name, String cpr, String reference){
        this.name = name;
        this.cpr = cpr;
        this.reference = reference;
    }
    
    public Patient() {
        
    }
    public int getPatient_ID() {
        return patient_ID;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getCPR(){
        return cpr;
    }
    public void setCPR(String cpr){
        this.cpr = cpr;
    }
    public String getReference(){
        return reference;
    }
    public void setReference(String reference){
        this.reference = reference;
    }
    public ArrayList<Picture> getPictures(){
        return pictures;
    }    
    public void setPictures(ArrayList<Picture> picture_IDs){
        this.pictures = picture_IDs;
    }
    
    
    
}
