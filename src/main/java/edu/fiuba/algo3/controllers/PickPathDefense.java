package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.*;
import edu.fiuba.algo3.modelo.exceptions.InsufficientCredits;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class PickPathDefense extends controler {
    private Stage stage;
    private StackPane stackPane;
    private Point point;
    private Point backwards;
    private int clickedRow;
    private int clickedColumn;
    private AlgoDefense algoDefense;
    private Label infoLabel;
    @FXML
    public Button white;
    @FXML
    public Button silver;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void setStage (Stage stage){
        this.stage = stage;
    }
    public void setDefense (StackPane stackPane, Point point, Point backwards,int clickedRow,
                            int clickedColumn, AlgoDefense algoDefense, Label infoLabel){
        this.stackPane = stackPane;
        this.point = point;
        this.backwards = backwards;
        this.clickedRow = clickedRow;
        this.clickedColumn = clickedColumn;
        this.algoDefense = algoDefense;
        this.infoLabel = infoLabel;
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

    private void alertInssuficientCredits () {
        Alert alertWithoutFunds = new Alert(Alert.AlertType.ERROR);
        alertWithoutFunds.setTitle("Insufficient credits");
        alertWithoutFunds.setContentText("Insufficient credits, your current balance is: " + algoDefense.getPlayer().playersBalance());
        alertWithoutFunds.showAndWait();
    }
}
