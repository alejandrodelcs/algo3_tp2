package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.*;
import edu.fiuba.algo3.modelo.exceptions.InsufficientCredits;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class PickDefense extends controler {
    private Stage stage;
    private int clickedRow;
    private int clickedColumn;
    private AlgoDefense algoDefense;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void setDefense (Stage stage,int clickedRow, int clickedColumn, AlgoDefense algoDefense){
        this.clickedRow = clickedRow;
        this.clickedColumn = clickedColumn;
        this.algoDefense = algoDefense;
        this.stage = stage;
    }
    @FXML
    public void whiteTower () {
        DefenseFactory whiteFactory = new WhiteTowerFactory();
        try {
            Point coordinatesToADirt = new Point(clickedRow, clickedColumn);
            Defense whiteTower = whiteFactory.createDefense(coordinatesToADirt);
            algoDefense.buildsADefense(whiteTower);
        } catch (InsufficientCredits insufficientCredits) {
            alertInssuficientCredits();
        }
        stage.close();
    }
    @FXML
    public void silverTower () {
        DefenseFactory silverFactory = new SilverTowerFactory();
        try {
            Point coordinatesToADirt = new Point(clickedRow, clickedColumn);
            Defense silverTower = silverFactory.createDefense(coordinatesToADirt);
            algoDefense.buildsADefense(silverTower);
        } catch (InsufficientCredits insufficientCredits) {
            alertInssuficientCredits();
        }
        stage.close();
    }
    private void alertInssuficientCredits () {
        Alert alertWithoutFunds = new Alert(Alert.AlertType.ERROR);
        alertWithoutFunds.setTitle("Insufficient credits");
        alertWithoutFunds.setContentText("Insufficient credits, your current balance is: " + algoDefense.getPlayer().playersBalance());
        alertWithoutFunds.showAndWait();
    }
}