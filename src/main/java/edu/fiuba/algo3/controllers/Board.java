package edu.fiuba.algo3.controllers;
import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.DefenseFactory;
import edu.fiuba.algo3.modelo.defense.SilverTower;
import edu.fiuba.algo3.modelo.defense.WhiteTowerFactory;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.App;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Board extends controler {
    @FXML
    private void createGameboard() throws IOException {
        App.setRoot("board"); //luego lo usare para cambiar de escena a una de resultados
    }

    @FXML
    private TextField rowsTextField;

    @FXML
    private TextField columnsTextField;

    @FXML
    private VBox matrixContainer;

    @FXML
    private GridPane gridPane;
    private GameBoard gameBoard;
    private AlgoDefense algoDefense = App.algodefense;


    @FXML
    private void printMap() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                Button buttonPlot = new Button(algoDefense.getGameboard().getPlot(i,j).display());
                buttonPlot.setAlignment(Pos.CENTER);
                gridPane.add(buttonPlot, j, i);
                }
            }
        /*
        int rows = Integer.parseInt(rowsTextField.getText());
        int columns = Integer.parseInt(columnsTextField.getText());

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                TextField textField = new TextField();
                gridPane.add(textField, j, i);

                Label label = new Label("(" + i + "," + j + ")");
                label.setAlignment(Pos.CENTER);
                gridPane.add(label, j, i);
            }
        }

         */
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DefenseFactory factory = new WhiteTowerFactory();
        Point coordinatesToADirt = new Point(2, 3);
        Defense whiteTower = factory.createDefense(coordinatesToADirt);
        algoDefense.buildsADefense(whiteTower);
        printMap();
    }
}
