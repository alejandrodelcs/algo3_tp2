package edu.fiuba.algo3.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AlertNotEnoughCredits extends controler{
    private Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void setAlert(Stage stage) {
        this.stage = stage;
    }
    @FXML
    public void closeMe(){
        stage.close();
    }
}
