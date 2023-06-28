package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.DefenseFactory;
import edu.fiuba.algo3.modelo.defense.SandyTrapFactory;
import edu.fiuba.algo3.modelo.exceptions.InsufficientCredits;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AlertInvalidPlotToBuild extends controler{
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void setStage(Stage alertStage) {
        this.stage = alertStage;
    }
    @FXML
    public void closeMe() {
        stage.close();
    }
}
