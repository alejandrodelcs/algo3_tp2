package edu.fiuba.algo3.controllers;
import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.defense.*;
import edu.fiuba.algo3.modelo.exceptions.InsufficientCredits;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.App;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

                    Label label = new Label("Pick a defense");
                    StackPane layout = new StackPane();
                    layout.getChildren().add(label);
                    Scene secondScene = new Scene(layout, 400, 400);
                    Stage newWindow = new Stage();
                    newWindow.setTitle("Pick a defense");
                    Text text = new Text();
                    text.setText("Pick a Defense: ");
                    newWindow.setScene(secondScene);

                    Button whiteTowerButton = new Button("White Tower: \n 10 credits");
                    ImageView imageViewWhiteTower = new ImageView(getClass().getResource("/img/magic2.png").toString());
                    imageViewWhiteTower.setFitHeight(50);
                    imageViewWhiteTower.setPreserveRatio(true);
                    whiteTowerButton.setGraphic(imageViewWhiteTower);
                    whiteTowerButton.setContentDisplay(ContentDisplay.TOP);

                    whiteTowerButton.setOnAction(e->{
                        try{
                        ImageView imageView = buildImageViewOfDefense(new Image(getClass().getResource("/img/magic2.png").toString(), true));
                        Point coordinatesToADirt = new Point(clickedRow,clickedColumn);
                        Defense whiteTower = whiteFactory.createDefense(coordinatesToADirt);
                        algoDefense.buildsADefense(whiteTower);

                        stackPane.getChildren().add(imageView);
                        newWindow.close();
                        }catch  (InsufficientCredits insufficientCredits){
                        alertInssuficientCredits();
                        }
                        newWindow.close();
                    });

                    Button silverTowerButton = new Button("Silver Tower: \n 20 credits");
                    ImageView imageViewSilverTower = new ImageView(getClass().getResource("/img/tower2.png").toString());
                    imageViewSilverTower.setFitHeight(50);
                    imageViewSilverTower.setPreserveRatio(true);
                    silverTowerButton.setGraphic(imageViewSilverTower);
                    silverTowerButton.setContentDisplay(ContentDisplay.TOP);
                    silverTowerButton.setOnAction(e->{
                        try{
                            ImageView imageView = buildImageViewOfDefense(new Image(getClass().getResource("/img/tower2.png").toString(), true));
                            Point coordinatesToADirt = new Point(clickedRow,clickedColumn);
                            Defense silverTower = silverFactory.createDefense(coordinatesToADirt);
                            algoDefense.buildsADefense(silverTower);

                            stackPane.getChildren().add(imageView);
                            newWindow.close();
                        }catch  (InsufficientCredits insufficientCredits){
                            alertInssuficientCredits();
                        }
                        newWindow.close();
                    });
                    var bottomStackPane = new StackPane(new HBox(
                            silverTowerButton,
                            whiteTowerButton
                    ));
                    var verticalStackPane = new StackPane(new VBox(
                            text,
                            bottomStackPane
                    ));

                    verticalStackPane.setAlignment(Pos.TOP_CENTER);
                    newWindow.setScene(new Scene(verticalStackPane, 400, 400));

                    newWindow.showAndWait();


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
