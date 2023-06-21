package edu.fiuba.algo3.controllers;
import edu.fiuba.algo3.modelo.facade.GameboardFacade;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.App;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    @FXML
    private void createMap() {
        gridPane.getChildren().clear();
        GameboardFacade gameboardFacade = new GameboardFacade();
        gameBoard = gameboardFacade.loadMap();
        gameBoard.constructPath();



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

        createMap();
    }
}
