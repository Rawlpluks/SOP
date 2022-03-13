/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx.sop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author clara
 */
public class CreatePatientController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField CPR;
    @FXML
    private TextField reference;
    @FXML
    private Text errorMessage;
    
    private GeneralDatabaseMethods gdm = new GeneralDatabaseMethods();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void savePatient(ActionEvent event) throws IOException, Exception {
    errorMessage.setText(" ");
        System.out.println("test");    
        //check if all fields is filled
        if(!name.getText().isBlank()
                && !CPR.getText().isBlank()
                && !reference.getText().isBlank()){
            
            //check if user is already exist
            if(!gdm.checkFormatchingPatient(name.getText())){
                    Patient createdPatient = new Patient(name.getText(), CPR.getText(),
                    reference.getText());
                    gdm.createPatient(createdPatient);
                    
                    
                    App.setPatient(createdPatient);
                    App.setRoot("consent");
            }else{
                errorMessage.setText("User already exist");
            }
        }else{
            errorMessage.setText("All fields need to be filled");
        }
    }
    
}
