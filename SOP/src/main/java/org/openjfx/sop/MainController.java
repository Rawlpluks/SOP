/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx.sop;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import java.util.Comparator;

/**
 * FXML Controller class
 *
 * @author clara
 */
public class MainController implements Initializable {

    @FXML
    private ListView<String> patientView;
    @FXML
    private Text nameField;
    @FXML
    private Text errorMessage;
    private ArrayList<Patient> overviewPatient = new ArrayList<>();
    
    Comparator<Patient> sortUserNameAlphabeticAscending = new Comparator<Patient>() {
        @Override
        public int compare(Patient p1, Patient p2) {
            return p1.getName().compareTo(p2.getName());
        }
    };
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        try{
            updateList();
            nameField.setText(App.getLoggedInUser().getName());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }    
    
    private void updateList() throws Exception {
         GeneralDatabaseMethods gdm = new GeneralDatabaseMethods();
            patientView.getItems().clear();
            overviewPatient = gdm.getAllPatients();
            
            Collections.sort(overviewPatient, sortUserNameAlphabeticAscending);
            for(Patient patient : overviewPatient){
                patientView.getItems().add(patient.getName());
            }
    }
    
    

    @FXML
    private void createPatient(ActionEvent event) throws IOException {
        App.setRoot("createPatient");
    }

    @FXML
    private void viewPatient(ActionEvent event) throws Exception {
       if(patientView.getSelectionModel().getSelectedIndex() >=0){
           App.setPatient(overviewPatient.get(patientView.getSelectionModel().getSelectedIndex()));
           App.setRoot("viewPatient"); 
       }else{
           errorMessage.setText("Vælg venligst en patient at se");
       }
    }

    @FXML
    private void deletePatient(ActionEvent event) throws Exception{
        GeneralDatabaseMethods gdm = new GeneralDatabaseMethods();
        if(patientView.getSelectionModel().getSelectedIndex() >= 0){
            gdm.deletePatient(overviewPatient.get(patientView.getSelectionModel().getSelectedIndex()).getPatient_ID());
            updateList();
        }else{
            errorMessage.setText("Vælg venligst den patient du vil slette");
        }
    }
    
    @FXML
    private void logoff(ActionEvent event) throws IOException {
        App.setRoot("login");
    }
}
