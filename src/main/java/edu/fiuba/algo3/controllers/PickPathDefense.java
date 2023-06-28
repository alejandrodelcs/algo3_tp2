package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.*;
import edu.fiuba.algo3.modelo.exceptions.InsufficientCredits;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
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
    @FXML
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void setStage (Stage stage){
        this.stage = stage;
    }
    public void setDefense (StackPane stackPane, Point point, Point backwards, int clickedRow,
                            int clickedColumn, AlgoDefense algoDefense, Label infoLabel, Parent root){
        this.stackPane = stackPane;
        this.point = point;
        this.backwards = backwards;
        this.clickedRow = clickedRow;
        this.clickedColumn = clickedColumn;
        this.algoDefense = algoDefense;
        this.infoLabel = infoLabel;
        this.root = root;
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
