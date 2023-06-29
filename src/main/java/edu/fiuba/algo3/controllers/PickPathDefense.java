package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.*;
import edu.fiuba.algo3.modelo.exceptions.InsufficientCredits;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PickPathDefense extends controler {
    private Stage stage;
    private int clickedRow;
    private int clickedColumn;
    private AlgoDefense algoDefense;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
    public void setDefense (Stage stage, int clickedRow, int clickedColumn, AlgoDefense algoDefense){
        this.clickedRow = clickedRow;
        this.clickedColumn = clickedColumn;
        this.algoDefense = algoDefense;
        this.stage = stage;
    }
    @FXML
    public void sandyTrap() {
        DefenseFactory sandyTrapFactory = new SandyTrapFactory();
        try {
            Point coordinatesToAPath = new Point(clickedRow, clickedColumn);
            Defense sandyTrap = sandyTrapFactory.createDefense(coordinatesToAPath);
            algoDefense.buildsADefense(sandyTrap);
        } catch (InsufficientCredits insufficientCredits) {
            alertInssuficientCredits();
        }
        stage.close();
    }
    private void alertInssuficientCredits (){

        Sound.get().playFX("insufficientCredits");
        FXMLLoader loaderPath = new FXMLLoader(getClass().getResource("/edu.fiuba.algo3/AlertNotEnoughCredits.fxml"));
        Parent rootPath = null;
        try {
            rootPath = loaderPath.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene newScene = new Scene(rootPath);
        stage.setScene(newScene);
        stage.showAndWait();
        stage.close();
    }
    @FXML
    public void closeMe(){
        stage.close();
    }
}
