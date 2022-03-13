package org.openjfx.sop;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SignupController {

    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Text errorMessage;
    @FXML
    private PasswordField jobText;
    @FXML
    private PasswordField passwordRepeat;
    @FXML
    private TextField nameText;

    @FXML
    private void signup(ActionEvent event) throws Exception {
        errorMessage.setText(" ");
        
        //check if all fields is filled
        if(!usernameText.getText().isBlank()
                && !passwordText.getText().isBlank()
                && !passwordRepeat.getText().isBlank()
                && !jobText.getText().isBlank()){
            //check if user is already exist
            UserDatabaseMethods userDatabaseMethods = new UserDatabaseMethods();
            if(!userDatabaseMethods.checkForMatchingUser(usernameText.getText())){
                //check if password have a special & uppercase character & at least 8 characters
                boolean passwordMeetsRequirements = false;
                char[] passwordChars = passwordText.getText().toCharArray();
                
                Pattern pattern = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(passwordText.getText());
                
                //længde
                if(passwordRepeat.getText().length() >= 8) {
                    //specielt tegn
                    if(matcher.find()){
                        //stort bogstav
                        for(int i = 0; i < passwordChars.length; i++){
                            if(Character.isUpperCase(passwordChars[i])){
                                passwordMeetsRequirements = true;
                                break;
                            }
                        }
                        //identical passwords
                        if(passwordMeetsRequirements){
                            if(passwordText.getText().equals(passwordRepeat.getText())){
                                SecurityMethods securityMethods = new SecurityMethods();
                                userDatabaseMethods.createUser(new User(usernameText.getText(), securityMethods.hexString(passwordText.getText()),
                                nameText.getText(), jobText.getText()));
                                
                                App.setLoggedInUser(userDatabaseMethods.getloggedInUser(usernameText.getText()));
                                App.setRoot("main");
                            }else{
                                errorMessage.setText("Password meets requirements, but doesn't match");
                            }
                        }else{
                            errorMessage.setText("Password is missing an uppercase character");
                        }
                    }else{
                        errorMessage.setText("Password is missing a special character");
                    }
                }else{
                    errorMessage.setText("Password needs to be at least 8 characters long");
                }
            }else{
                errorMessage.setText("User already exist");
            }
        }else{
            errorMessage.setText("All fields need to be filled");
        }
    }

    @FXML
    private void backToLogin(ActionEvent event) throws IOException {
        App.setRoot("login");
    }
}