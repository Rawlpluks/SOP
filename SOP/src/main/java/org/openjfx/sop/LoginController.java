/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx.sop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author clara
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Text errorMessage;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SecurityMethods securityMethods = new SecurityMethods();
        try {
            System.out.println(securityMethods.hexString("Krabbe123!"));
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void login(ActionEvent event) throws IOException, Exception {
        errorMessage.setText("");

        //check if user exist
        UserDatabaseMethods userDatabasemethods = new UserDatabaseMethods();
        if (userDatabasemethods.checkForMatchingUser(usernameText.getText())) {

            //check if user and passowrd match
            SecurityMethods securityMethods = new SecurityMethods();
            if (userDatabasemethods.checkForMatchingPassword(usernameText.getText(),
                    securityMethods.hexString(passwordText.getText()))) {

                App.setLoggedInUser(userDatabasemethods.getloggedInUser(usernameText.getText()));
                App.setRoot("main");

            
    
            } else {
                errorMessage.setText("user dosen't exist or password dont match");
            }
        } else {
            errorMessage.setText("user dosen't exist or password dont match");
        }
    }

    @FXML
    private void signup(ActionEvent event) throws IOException {
        App.setRoot("signup");
    }
}
