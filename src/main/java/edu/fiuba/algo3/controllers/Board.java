package edu.fiuba.algo3.controllers;
import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.DefenseFactory;
import edu.fiuba.algo3.modelo.defense.WhiteTowerFactory;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.App;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
    private GridPane gridPane;
    private GameBoard gameBoard;
    private AlgoDefense algoDefense = App.algodefense;

    private Image[][] cellImages;


    @FXML
    private void printMap() {
        cellImages = new Image[15][15];
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
            return new Image(getClass().getResource("/img/grass.png").toString(),true);
        } else {
            return new Image(getClass().getResource("/img/grass.png").toString(),true);
        }
    }

    @FXML
    private void updateImages(){

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DefenseFactory factory = new WhiteTowerFactory();
        Point coordinatesToADirt = new Point(2, 3);
        Defense whiteTower = factory.createDefense(coordinatesToADirt);
        algoDefense.buildsADefense(whiteTower);
        printMap();
    }
}
