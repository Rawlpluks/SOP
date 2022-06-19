/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx.sop;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.util.Comparator;
import java.util.Collections;

/**
 * FXML Controller class
 *
 * @author clara
 */
public class ViewPatientController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField pictureName;
    @FXML
    private TextField reference;
    @FXML
    private ListView<String> pictureList;
    @FXML
    private Text errorMessage;
    @FXML
    private Label cprText;
    private ArrayList<Picture> overviewPicture = new ArrayList<>();
    
    private GeneralDatabaseMethods gdm = new GeneralDatabaseMethods();
    
    Comparator<Picture> sortUserNameAlphabeticAscending = new Comparator<Picture>() {
        @Override
        public int compare(Picture p1, Picture p2) {
            return p1.getTitel().compareTo(p2.getTitel());
        }
    };

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            Patient patient = App.getPatient();
            overviewPicture = patient.getPictures();
            cprText.setText(patient.getCPR());
            name.setText(patient.getName());
            reference.setText(patient.getReference());
            updateList();
        }catch (Exception e){
            System.out.println(e.getMessage());            
        }
    }    
    
    private void updateList() throws Exception {
        pictureList.getItems().clear();
        
        Collections.sort(overviewPicture, sortUserNameAlphabeticAscending);
            for(Picture picture : overviewPicture){
                pictureList.getItems().add(picture.getTitel());
            }
    }
    
    @FXML
    private void savePicture (ActionEvent event) throws IOException, Exception {
        errorMessage.setText(" ");
        //check for titel
        if(!pictureName.getText().isBlank()){
            Picture createdPicture = new Picture(pictureName.getText(), App.getPatient().getPatient_ID());
            gdm.savePicture(createdPicture);
            App.setPicture(createdPicture);
            overviewPicture.add(createdPicture);
            updateList();
            App.setRoot("camera");
        }else{
            errorMessage.setText("Giv venligst billedet et navn");
        }
        
    }

    @FXML
    private void removePicture (ActionEvent event) throws Exception{
        if(pictureList.getSelectionModel().getSelectedIndex() >= 0){
            gdm.deletePicture(overviewPicture.get(pictureList.getSelectionModel().getSelectedIndex()).getPicture_ID());
            overviewPicture.remove(pictureList.getSelectionModel().getSelectedIndex());
            updateList();
        }else{
            errorMessage.setText("Vælg venligst det billede du vil slette");
        }
    }

    @FXML
    private void backToMain(ActionEvent event) throws IOException {
        App.setRoot("main");
    }
    
    @FXML
    private void viewConsent(ActionEvent event) throws IOException, Exception{
        App.setConsent(gdm.setConsent(App.getPatient().getPatient_ID()));
        App.setRoot("viewConsent");
                
    }

    
}
