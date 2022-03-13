package org.openjfx.sop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;
    private static User loggedInUser = new User();
    private static Patient patient = null;
    private static Picture picture = null;
    private static Consent consent = null;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"));
        stage.setScene(scene);
        stage.show();
        
        this.stage = stage;
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        stage.sizeToScene();
        stage.centerOnScreen();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static User getLoggedInUser(){
        return loggedInUser;
    }
    
    public static void setLoggedInUser(User _loggedUser) {
        App.loggedInUser = _loggedUser;
    }
    
    public static Patient getPatient(){
        return patient;
    }
    
    public static void setPatient(Patient chosenPatient) {
        App.patient = chosenPatient;
    }
    
    public static Picture getPicture(){
        return picture;
    }
    
    public static void setPicture(Picture chosenPicture){
        App.picture = chosenPicture;
    }
    
    public static Consent getConsent(){
        return consent;
    }
    
    public static void setConsent(Consent chosenConsent){
        App.consent = chosenConsent;
    }

}