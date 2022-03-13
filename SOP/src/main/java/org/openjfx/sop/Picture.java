/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx.sop;

/**
 *
 * @author clara
 */
public class Picture {
    private int picture_ID;
    private String titel;
    private int patient_ID;
    
    public Picture(int picture_ID, String titel, int patient_ID){
        this.picture_ID = picture_ID;
        this.titel = titel;
        this.patient_ID = patient_ID;
    }
    public Picture(String titel){
        this.titel = titel;
    }
    public Picture(String titel, int patient_ID){
        this.titel = titel;
        this.patient_ID = patient_ID;
    }
    public Picture(){
        
    }
    public int getPicture_ID(){
        return picture_ID;
    }
    public String getTitel(){
        return titel;
    }
    public void setTitel(String titel){
        this.titel = titel;
    }
    public int getPatient_ID(){
        return patient_ID;
    }
    public void setPatient_ID(int patient_ID){
        this.patient_ID = patient_ID;
    }
}
