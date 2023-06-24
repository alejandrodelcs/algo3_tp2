package edu.fiuba.algo3.controllers;
import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.defense.*;
import edu.fiuba.algo3.modelo.exceptions.InsufficientCredits;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Label;

import java.awt.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
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
    private ArrayList<Image> terrainImages = new ArrayList<>();

    @FXML
    private void printMap() {
        int height = (int) gameBoard.height();
        int width = (int) gameBoard.width();
        this.cellImages = new Image[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                StackPane stackPane = loadCellImage(i, j);
                stackPane.maxHeight(50);
                stackPane.maxWidth(50);
                gridPane.add(stackPane, j, i);
                final int clickedRow = i;
                final int clickedColumn = j;
                Point point = new Point(i,j);
                Point backwards = new Point(j,i); //TODO: REVERT X Y so this variable won't be used anymore
                pickADefenseEvent(stackPane, point, backwards, clickedRow, clickedColumn);
            }
        }
    }

    private void pickADefenseEvent(StackPane stackPane,Point point, Point backwards, int clickedRow, int clickedColumn) {
        DefenseFactory silverFactory = new SilverTowerFactory();
        DefenseFactory whiteFactory = new WhiteTowerFactory();
        DefenseFactory sandyFactory = new SandyTrapFactory();

        stackPane.setOnMouseClicked(someEvent -> {
            if(gameBoard.availableForBuilding(point) && (!gameBoard.isEnemyPath(backwards))){

                try{
                    Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                    pickAdefenseDialog(dialog);
                    ButtonType silverTowerOption = new ButtonType("Silver Tower");
                    ButtonType whiteTowerOption = new ButtonType("White Tower");

                    dialog.getButtonTypes().setAll();
                    dialog.getButtonTypes().setAll(whiteTowerOption, silverTowerOption);

                    Optional<ButtonType> result = dialog.showAndWait();
                    if (result.get() == whiteTowerOption) {

                        ImageView imageView = buildImageViewOfDefense(new Image(getClass().getResource("/img/magic2.png").toString(), true));
                        Point coordinatesToADirt = new Point(clickedRow,clickedColumn);
                        Defense whiteTower = whiteFactory.createDefense(coordinatesToADirt);
                        algoDefense.buildsADefense(whiteTower);

                        stackPane.getChildren().add(imageView);

                    } else if (result.get() == silverTowerOption) {

                        ImageView imageView = buildImageViewOfDefense(new Image(getClass().getResource("/img/tower2.png").toString(), true));
                        Point coordinatesToADirt = new Point(clickedRow,clickedColumn);
                        Defense silverTower = silverFactory.createDefense(coordinatesToADirt);
                        algoDefense.buildsADefense(silverTower);

                        stackPane.getChildren().add(imageView);
                    }

                }catch  (InsufficientCredits insufficientCredits){
                    alertInssuficientCredits();
                }

            }else if( (gameBoard.isEnemyPath(backwards)) ){
                if (gameBoard.isStart(backwards)  || (gameBoard.isFinish(backwards))) {
                    alertStartFinish();
                }
                else{
                    try{
                        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                        pickAdefenseDialog(dialog);
                        ButtonType sandyTrapOption = new ButtonType("Sandy Trap");
                        dialog.getButtonTypes().setAll(sandyTrapOption);

                        Optional<ButtonType> result = dialog.showAndWait();
                        if(result.get() == sandyTrapOption){
                            ImageView imageView = buildImageViewOfDefense(new Image(getClass().getResource("/img/sandyTrap.jpg").toString(), true));
                            Point coordinatesToEnemyPath = new Point(clickedRow,clickedColumn);
                            Defense sandyTrap = sandyFactory.createDefense(coordinatesToEnemyPath);
                            algoDefense.buildsADefense(sandyTrap);

                            stackPane.getChildren().add(imageView);
                        }

                    }catch  (InsufficientCredits insufficientCredits){
                        alertInssuficientCredits();
                    }
                }
            }
        });
    }

    private void alertStartFinish() {
        Alert startFinishAlertWithoutFunds = new Alert(Alert.AlertType.ERROR);
        startFinishAlertWithoutFunds.setTitle("Invalid Plot To build");
        startFinishAlertWithoutFunds.setContentText("You cannot build on start or finish line");
        startFinishAlertWithoutFunds.showAndWait();
    }

    private ImageView buildImageViewOfDefense(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    private void pickAdefenseDialog(Alert dialog) {
        dialog.setTitle("Pick a Defense");
        dialog.setHeaderText("Available Defenses for this plot");
        dialog.setContentText("Available Defenses for this plot ");
        dialog.setResizable(true);
        dialog.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        dialog.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    }

    private void alertInssuficientCredits() {
        Alert alertWithoutFunds = new Alert(Alert.AlertType.ERROR);
        alertWithoutFunds.setTitle("Insufficient credits");
        alertWithoutFunds.setContentText("Insufficient credits, your current balance is: " + algoDefense.getPlayer().playersBalance());
        alertWithoutFunds.showAndWait();
    }

    private StackPane loadCellImage(int row, int column) {
        return gameBoard.getStackPane(row, column, terrainImages);
    }

    @FXML
    private void updateImages(){
        gridPane.getChildren().clear();
        algoDefense.nextTurn();
        String updatedStats = algoDefense.getPlayerInfo();
        infoLabel.setText(updatedStats);
        printMap();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image aTerrainImage = new Image(getClass().getResource("/img/path.png").toString(), true);
        terrainImages.add(aTerrainImage);
        aTerrainImage = new Image(getClass().getResource("/img/dirt.png").toString(), true);
        terrainImages.add(aTerrainImage);
        aTerrainImage = new Image(getClass().getResource("/img/rock2.png").toString(), true);
        terrainImages.add(aTerrainImage);
        String updatedStats = algoDefense.getPlayerInfo();
        infoLabel.setText(updatedStats);
        printMap();
    }

}
