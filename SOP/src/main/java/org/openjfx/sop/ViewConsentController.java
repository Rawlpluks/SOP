/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx.sop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author clara
 */
public class ViewConsentController implements Initializable{
    /**
     * Initializes the controller class.
     */
    @FXML
    private Text errorMessage;
    @FXML
    private TextField consent1;
    @FXML 
    private TextField consent2;
    @FXML
    private TextField consent3;
    @FXML
    private TextField consent4;
    @FXML
    private Label cprText;
    
    GeneralDatabaseMethods gdm = new GeneralDatabaseMethods();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(App.getConsent());
        try {
            Consent consent = App.getConsent();
            consent1.setText(consent.getConsent1());
            consent2.setText(consent.getConsent2());
            consent3.setText(consent.getConsent3());
            consent4.setText(consent.getConsent4());
            cprText.setText(App.getPatient().getCPR());
            
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
    }    
    @FXML
    public void saveConsent(ActionEvent event) throws IOException, Exception{
        
    }
    @FXML
    public void backToMain(ActionEvent event) throws IOException, Exception{
        
    }
}
