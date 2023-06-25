package edu.fiuba.algo3.controllers;
import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.defense.*;
import edu.fiuba.algo3.modelo.exceptions.InsufficientCredits;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.App;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
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

import java.net.URL;
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
                stackPane.getStyleClass().add("board-plot");
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

        stackPane.setOnMouseClicked(someEvent -> {
            if(gameBoard.availableForBuilding(point) && (!gameBoard.isEnemyPath(backwards))){
                Button whiteTowerButton = createWhiteTowerButton();
                Stage newWindow = createDefensesTowerMenuWindow();
                    whiteTowerButton.setOnAction(e->{
                        whiteTowerButtonEvent(stackPane, clickedRow, clickedColumn);
                        newWindow.close();
                    });

                    Button silverTowerButton = createSilverTowerButton();
                    silverTowerButton.setOnAction(e->{
                        silverTowerButtonEvent(stackPane, clickedRow, clickedColumn);
                        newWindow.close();
                    });

                    createDefensesMenuStack(silverTowerButton,whiteTowerButton, newWindow);
                    newWindow.showAndWait();

            }else if( (gameBoard.isEnemyPath(backwards)) ){
                if (gameBoard.isStart(backwards)  || (gameBoard.isFinish(backwards))) {
                    alertStartFinish();
                }
                else{
                    Stage newWindow = createDefensesTowerMenuWindow();
                    Button sandyTrapButton = createSandyTrapButton();
                    sandyTrapButton.setOnAction(e->{
                        sandyTrapButtonEvent(stackPane, clickedRow, clickedColumn);
                        newWindow.close();
                    });
                    createDefensesOnEnemyPathMenuStack(sandyTrapButton, newWindow);
                    newWindow.showAndWait();
                }
            }
        });
    }

    private void createDefensesOnEnemyPathMenuStack(Button sandyTrapButton, Stage newWindow) {
        var bottomStackPane = new StackPane(new HBox(
                sandyTrapButton
        ));
        Text text = new Text();
        text.setText("Available Defenses for this plot:");
        var verticalStackPane = new StackPane(new VBox(
                text,
                bottomStackPane
        ));
        verticalStackPane.setAlignment(Pos.TOP_CENTER);
        newWindow.setScene(new Scene(verticalStackPane, 400, 300));
    }

    private void sandyTrapButtonEvent(StackPane stackPane, int clickedRow, int clickedColumn) {
        DefenseFactory sandyFactory = new SandyTrapFactory();

        try{
            ImageView imageView = buildImageViewOfDefense(new Image(getClass().getResource("/img/sandyTrapCastle.jpeg").toString(), true));
            Point coordinatesToEnemyPath = new Point(clickedRow,clickedColumn);
            Defense sandyTrap = sandyFactory.createDefense(coordinatesToEnemyPath);
            algoDefense.buildsADefense(sandyTrap);

            stackPane.getChildren().add(imageView);
        }catch  (InsufficientCredits insufficientCredits){
            alertInssuficientCredits();
        }
    }

    private Button createSandyTrapButton() {
        Button sandyTrapButton = new Button("Sandy Trap: \n Price: 25\n Building Time: Instant\n Vanishes in 3 turns \n Damage: Des accelerate enemies by 50% ");
        ImageView imageViewSilverTower = new ImageView(getClass().getResource("/img/sandyTrapCastle.jpeg").toString());
        imageViewSilverTower.setFitHeight(50);
        imageViewSilverTower.setPreserveRatio(true);
        sandyTrapButton.setGraphic(imageViewSilverTower);
        sandyTrapButton.setContentDisplay(ContentDisplay.TOP);
        return sandyTrapButton;
    }

    private void createDefensesMenuStack(Button silverTowerButton, Button whiteTowerButton, Stage newWindow) {
        var bottomStackPane = new StackPane(new HBox(
                silverTowerButton,
                whiteTowerButton
        ));

        Text text = new Text();
        text.setText("Pick a Defense: ");
        var verticalStackPane = new StackPane(new VBox(
                text,
                bottomStackPane
        ));

        verticalStackPane.setAlignment(Pos.TOP_CENTER);
        newWindow.setScene(new Scene(verticalStackPane, 400, 400));
    }


    private void silverTowerButtonEvent(StackPane stackPane, int clickedRow, int clickedColumn) {
        DefenseFactory silverFactory = new SilverTowerFactory();
        try{
            ImageView imageView = buildImageViewOfDefense(new Image(getClass().getResource("/img/silverTower.png").toString(), true));
            Point coordinatesToADirt = new Point(clickedRow,clickedColumn);
            Defense silverTower = silverFactory.createDefense(coordinatesToADirt);
            algoDefense.buildsADefense(silverTower);

            stackPane.getChildren().add(imageView);
        }catch  (InsufficientCredits insufficientCredits){
            alertInssuficientCredits();
        }
    }

    private void whiteTowerButtonEvent(StackPane stackPane, int clickedRow, int clickedColumn) {
        DefenseFactory whiteFactory = new WhiteTowerFactory();
        try{
            ImageView whiteTowerImageView = buildImageViewOfDefense(new Image(getClass().getResource("/img/magic2.png").toString(), true));
            Point coordinatesToADirt = new Point(clickedRow,clickedColumn);
            Defense whiteTower = whiteFactory.createDefense(coordinatesToADirt);
            algoDefense.buildsADefense(whiteTower);

            stackPane.getChildren().add(whiteTowerImageView);
        }catch  (InsufficientCredits insufficientCredits){
            alertInssuficientCredits();
        }
    }

    private Button createSilverTowerButton() {
        Button silverTowerButton = new Button("Silver Tower: \n Price: 20 \n Building Time: 2 Turns\n Range: 5\n Damage: 2");
        ImageView imageViewSilverTower = new ImageView(getClass().getResource("/img/silverTower.png").toString());
        imageViewSilverTower.setFitHeight(50);
        imageViewSilverTower.setPreserveRatio(true);
        silverTowerButton.setGraphic(imageViewSilverTower);
        silverTowerButton.setContentDisplay(ContentDisplay.TOP);
        silverTowerButton.setId("button");
        //silverTowerButton.getStyleClass().clear();
        //silverTowerButton.getStyleClass().add("button");
        silverTowerButton.setStyle(bringStyles());
        return silverTowerButton;
    }



    private Button createWhiteTowerButton() {

        Button whiteTowerButton = new Button("White Tower: \n Price: 10 \n Building Time: 1 Turn\n Range: 3\n Damage: 1");
        ImageView imageViewWhiteTower = new ImageView(getClass().getResource("/img/magic2.png").toString());
        imageViewWhiteTower.setFitHeight(50);
        imageViewWhiteTower.setPreserveRatio(true);
        whiteTowerButton.setGraphic(imageViewWhiteTower);
        whiteTowerButton.setContentDisplay(ContentDisplay.TOP);
        whiteTowerButton.setId("button");
        whiteTowerButton.setStyle(bringStyles());
        return whiteTowerButton;
    }

    private Stage createDefensesTowerMenuWindow() {
        Label label = new Label("Pick a defense");
        StackPane layout = new StackPane();
        layout.getChildren().add(label);
        Scene secondScene = new Scene(layout, 400, 300);
        secondScene.getStylesheets().add(getClass().getResource("/edu.fiuba.algo3/styles.css").toString());
        Stage newWindow = new Stage();
        newWindow.setTitle("Pick a defense");
        newWindow.setScene(secondScene);
        return newWindow;
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

    private String bringStyles() {
        return "  -fx-padding: 10px 0;\n" +
                "    -fx-pref-width: 250px;\n" +
                "    -fx-letter-spacing: 2px;\n" +
                "    -fx-background-radius: 20px;\n" +
                "    -fx-font-family: 'Skranji', cursive;\n" +
                "    -fx-text-fill: #ffffff;\n" +
                "    -fx-font-size: 18px;\n" +
                "    -fx-font-weight: 400;\n" +
                "    -fx-effect: innershadow(gaussian, rgba(1, 1, 1, 0.82), 3, 0, 0, 0);\n" +
                "    -fx-border-width: 4px;\n" +
                "    -fx-border-color: rgba(129, 229, 209, 0.51);\n" +
                "    -fx-border-radius: 20px;\n" +
                "    -fx-background-color: radial-gradient(center 20% 30%, radius 50%, rgba(75, 117, 137, 0.78) 0%, rgba(41, 87, 145, 0.72) 100%);";
    }

}
