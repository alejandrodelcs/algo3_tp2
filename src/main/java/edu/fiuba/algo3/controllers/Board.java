package edu.fiuba.algo3.controllers;
import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.DefenseFactory;
import edu.fiuba.algo3.modelo.defense.WhiteTowerFactory;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.App;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.awt.*;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Board extends controler {


    @FXML
    private GridPane gridPane;
    private GameBoard gameBoard;
    private AlgoDefense algoDefense = App.algodefense;

    private Image[][] cellImages;
    @FXML
    private ImageView imageView;


    @FXML
    private void printMap() {
        this.cellImages = new Image[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                ImageView imageView = new ImageView();
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
                Image image = loadCellImage(i, j);
                imageView.setImage(image);
                cellImages[i][j] = image;
                gridPane.add(imageView, j, i);

            }
        }
    }
    private Image loadCellImage(int row, int column) {
        if ((row + column) % 2 == 0) {
            return new Image(getClass().getResource("/img/dirt.png").toString(),true);
        } else {
            return new Image(getClass().getResource("/img/dirt.png").toString(),true);
        }
    }

    @FXML
    private void updateImages(){

    }

    @FXML
    private void addTower(MouseEvent event){
        Node source = (Node) event.getTarget();
        if (source instanceof ImageView) { //esto tendría que ver como cambiar
            ImageView imageView = (ImageView) source;
            if (gridPane.getChildren().contains(imageView)) {
                Integer columnIndex = GridPane.getColumnIndex(imageView);
                Integer rowIndex = GridPane.getRowIndex(imageView);
                if (columnIndex != null && rowIndex != null) {
                    int column = columnIndex;
                    int row = rowIndex;

                    Image newImage = new Image(getClass().getResource("/img/tower2.png").toString(), true);
                    newImage.progressProperty().addListener((observable, oldValue, newValue) -> {
                        System.out.println("Current progress: "+newValue);
                        if (newValue.doubleValue() == 1.0) {
                            imageView.setImage(newImage);
                            cellImages[row][column] = newImage;
                        }
                    });
                }
            }
        }
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

