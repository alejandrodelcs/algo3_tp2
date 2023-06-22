package edu.fiuba.algo3.controllers;
import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.DefenseFactory;
import edu.fiuba.algo3.modelo.defense.WhiteTowerFactory;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.App;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.awt.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Board extends controler {


    @FXML
    private GridPane gridPane;
    private AlgoDefense algoDefense = App.algodefense;
    private GameBoard gameBoard = algoDefense.getGameboard();

    private Image[][] cellImages;
    @FXML
    private ImageView imageView;


    @FXML
    private void printMap() {
        this.cellImages = new Image[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                StackPane stackPane = loadCellImage(i, j);
                stackPane.maxHeight(50);
                stackPane.maxWidth(50);
                gridPane.add(stackPane, j, i);
                stackPane.setOnMouseClicked(someEvent -> {
                    Image image = new Image(getClass().getResource("/img/tower2.png").toString(), true);
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                    imageView.setPreserveRatio(true);
                    stackPane.getChildren().add(imageView);
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
        printMap();
    }
    @FXML
    private void createGameboard() throws IOException {
        App.setRoot("board"); //luego lo usare para cambiar de escena a una de resultadoss
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

