package edu.fiuba.algo3.controllers;
import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.DefenseFactory;
import edu.fiuba.algo3.modelo.defense.SilverTowerFactory;
import edu.fiuba.algo3.modelo.defense.WhiteTowerFactory;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.gameboard.Plot;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Label;

import java.awt.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Board extends controler {

    @FXML
    private GridPane gridPane;
    @FXML
    private AlgoDefense algoDefense = App.algodefense;
    private GameBoard gameBoard = algoDefense.getGameboard();
    private Image[][] cellImages;
    @FXML
    private ImageView imageView;
    @FXML
    private Label infoLabel;

    @FXML
    private void printMap() {
        int height = (int) gameBoard.height();
        int width = (int) gameBoard.width();
        DefenseFactory factory = new SilverTowerFactory();
        this.cellImages = new Image[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                StackPane stackPane = loadCellImage(i, j);
                stackPane.maxHeight(50);
                stackPane.maxWidth(50);
                gridPane.add(stackPane, j, i);
                final int clickedRow = i;
                final int clickedColumn = j;
                stackPane.setOnMouseClicked(someEvent -> {

                    Image image = new Image(getClass().getResource("/img/magic2.png").toString(), true);
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                    imageView.setPreserveRatio(true);
                    stackPane.getChildren().add(imageView);
                    Point coordinatesToADirt = new Point(clickedRow,clickedColumn);
                    Defense whiteTower = factory.createDefense(coordinatesToADirt);
                    algoDefense.buildsADefense(whiteTower);
                });
            }
        }
    }

    private StackPane loadCellImage(int row, int column) {
        return gameBoard.getPlot(row, column).getStackPane();
    }

    @FXML
    private void updateImages(){
        gridPane.getChildren().clear();
        algoDefense.nextTurn();
        String updatedStats = algoDefense.getPlayerInfo();
        infoLabel.setText(updatedStats);
        printMap();
    }
    @FXML
    private void createGameboard() throws IOException {
        App.setRoot("board"); //luego lo usare para cambiar de escena a una de resultadoss
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String updatedStats = algoDefense.getPlayerInfo();
        infoLabel.setText(updatedStats);
        printMap();
    }

}


