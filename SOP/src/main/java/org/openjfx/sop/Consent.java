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
public class Consent {
    private int consent_ID;
    private String consent1;
    private String consent2;
    private String consent3;
    private String consent4;
    private int patient_ID;
    
    public Consent(int consent_ID, String consent1, String consent2, String consent3, String consent4, int patient_ID){
        this.consent_ID = consent_ID;
        this.consent1 = consent1;
        this.consent2 = consent2;
        this.consent3 = consent3;
        this.consent4 = consent4;
        this.patient_ID = patient_ID;
    }
    public Consent(String consent1, String consent2, String consent3, String consent4, int patient_ID){
        this.consent1 = consent1;
        this.consent2 = consent2;
        this.consent3 = consent3;
        this.consent4 = consent4;
        this.patient_ID = patient_ID;
    }
    public int getConsent_ID(){
        return consent_ID;
    }
    public String getConsent1(){
        return consent1;
    }
    public void setConset1(String consent1){
        this.consent1 = consent1;
    }
    public String getConsent2(){
        return consent2;
    }
    public void setConset2(String consent2){
        this.consent2 = consent2;
    }
    public String getConsent3(){
        return consent3;
    }
    public void setConset3(String consent3){
        this.consent3 = consent3;
    }
    public String getConsent4(){
        return consent4;
    }
    public void setConset4(String consent4){
        this.consent4 = consent4;
    }
    public int getPatient_ID(){
        return patient_ID;
    }
    public void setPatient_ID(int patient_ID){
        this.patient_ID = patient_ID;
    }
    
}
