package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.App;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Board {
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

    @FXML
    private void createMap() {
        gridPane.getChildren().clear();

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
    }
}
