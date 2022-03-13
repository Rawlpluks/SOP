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
 * FXML Controller class
 *
 * @author clara
 */
public class ConsentController implements Initializable {

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
        try {
            Patient patient = App.getPatient();
            cprText.setText(patient.getCPR());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }    

    @FXML
    private void saveConsent(ActionEvent event) throws IOException, Exception {
        errorMessage.setText(" ");
        //check if all fields is filled
        if(!consent1.getText().isBlank()
                && !consent2.getText().isBlank()
                && !consent3.getText().isBlank()
                && !consent4.getText().isBlank()){
            Consent createdConsent = new Consent(consent1.getText(), consent2.getText(),
                    consent3.getText(), consent4.getText(), gdm.getPatient_ID(App.getPatient().getName()));
            gdm.saveConsent(createdConsent);
            App.setRoot("main");            
        }else{
            errorMessage.setText("Alle felterne skal være udfyldt");
        }
    }

    
}
