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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author clara
 */
public class CameraController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Text pictureTitel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pictureTitel.setText(App.getPicture().getTitel());
    }    

    @FXML
    private void save(ActionEvent event) throws IOException {
        App.setRoot("viewPatient");
    }
    
}
