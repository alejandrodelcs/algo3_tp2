package edu.fiuba.algo3.controllers;
import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.Logger;
import edu.fiuba.algo3.modelo.defense.*;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.exceptions.InsufficientCredits;
import edu.fiuba.algo3.modelo.exceptions.NonConstructibleArea;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.gameboard.Plot;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;

import java.io.IOException;
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
    private TextArea consoleTextArea;
    @FXML
    private Button musicButton;
    StackPane lastClicked;
    @FXML
    private MenuItem fullScreenMenuBarOption;
    private PlotDrawer aPlotDrawer = new PlotDrawer();

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
                showRangeEvent(i, j, stackPane);
            }
        }
    }

    public void showRangeEvent(int row, int column, StackPane aStackPane) {
        Plot somePlot = gameBoard.getPlot(row, column);
        if (somePlot.getDefense() != null) {
            ArrayList<Point> plotsInRange = somePlot.getDefense().getPlotsInRange();
            aStackPane.setOnMouseClicked(someOtherEvent -> {
                if (lastClicked != aStackPane) {
                    for (Node stackPane: gridPane.getChildren()) {
                        stackPane.getStyleClass().remove("outOfRangePlot");
                        stackPane.getStyleClass().add("outOfRangePlot");
                    }
                    for (Point plot : plotsInRange) {
                        int plotRow = (int) plot.getX();
                        int plotColumn = (int) plot.getY();
                        if (plotRow >= 0 && plotRow < gridPane.getRowCount() && plotColumn >= 0 && plotColumn < gridPane.getColumnCount()) {
                            StackPane inRangePlot = (StackPane) gridPane.getChildren().get(plotRow * gridPane.getColumnCount() + plotColumn);
                            inRangePlot.getStyleClass().remove("outOfRangePlot");
                        }
                    }                    lastClicked = aStackPane;
                } else {
                    for (Node stackPane: gridPane.getChildren()) {
                        if (stackPane.getStyleClass().contains("outOfRangePlot")) {
                            stackPane.getStyleClass().remove("outOfRangePlot");
                        }
                    }
                    lastClicked = null;
                }
            });
        }
    };

    private void pickADefenseEvent(StackPane stackPane,Point point, Point backwards, int clickedRow, int clickedColumn) {

        stackPane.setOnMouseClicked(someEvent -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu.fiuba.algo3/PickDefense.fxml"));
                Parent root = null;

                FXMLLoader loaderPath = new FXMLLoader(getClass().getResource("/edu.fiuba.algo3/PickPathDefense.fxml"));
                Parent rootPath = null;
                try {
                    root = loader.load();
                    rootPath = loaderPath.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if(gameBoard.availableForBuilding(point) && (!gameBoard.isEnemyPath(backwards))){
                    Stage dirtDefenseStage = new Stage();
                    Scene scene = new Scene(root);
                    dirtDefenseStage.setScene(scene);
                    PickDefense pickDefense = loader.getController();
                    pickDefense.setStage(dirtDefenseStage);
                    pickDefense.setDefense(stackPane,point, backwards, clickedRow, clickedColumn, algoDefense, infoLabel);
                    dirtDefenseStage.showAndWait();
                    if(algoDefense.isOccupyByADefense(point)){
                        setConstrutable(stackPane);
                    }
                }else if ((gameBoard.isEnemyPath(backwards)) && gameBoard.availableForBuilding(point)) {
                    if (gameBoard.isStart(backwards) || (gameBoard.isFinish(backwards))) {
                        alertStartFinish();
                    } else {
                        Stage pathDefenseStage = new Stage();
                        Scene scene = new Scene(rootPath);
                        pathDefenseStage.setScene(scene);
                        PickPathDefense pickDefense = loaderPath.getController();
                        pickDefense.setStage(pathDefenseStage);
                        pickDefense.setDefense(stackPane,point, backwards, clickedRow, clickedColumn, algoDefense, infoLabel);
                        pathDefenseStage.showAndWait();
                        if(algoDefense.isOccupyByADefense(point)){
                            setConstrutable(stackPane);
                        }
                    }
                }
            } catch (NonConstructibleArea exception) {
                StackPane clickedPlot = (StackPane) gridPane.getChildren().get(clickedRow * (int)gameBoard.width() + clickedColumn);
                clickedPlot.getStyleClass().add("cannotBuild");

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                    clickedPlot.getStyleClass().remove("cannotBuild");
                }));
                timeline.play();
            }
        });
    }

    private void setConstrutable(StackPane stackPane){
        ImageView imageView = buildImageViewOfDefense(new Image(getClass().getResource("/img/under-contruction.png").toString(), true));
        String updatedStats = algoDefense.getPlayerInfo();
        infoLabel.setText(updatedStats);
        stackPane.getChildren().add(imageView);
        Sound.get().playFX("buildDefense");
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

    private StackPane loadCellImage(int row, int column) {
        StackPane stackPane = aPlotDrawer.drawAPlot(gameBoard.getPlot(row, column));
        Plot info = gameBoard.getPlot(row, column);
        String plotInfo = info.show() + " (" + row + ", " + column + ")";
        for (Enemy enemy : info.enemiesInPlot()) {
            plotInfo += "\n\n" + enemy.show();
        }
        try {
            if (!(gameBoard.availableForBuilding(new Point(row, column))) && info.getDefense().isAvailable()) {
                plotInfo += "\n\n" + info.getDefense().show();
            }
        } catch (NonConstructibleArea e) {
            plotInfo += "";
        }
        this.updateTooltip(info, plotInfo, stackPane, row, column);
        return stackPane;
    }

    private void updateTooltip(Plot info, String plotInfo, StackPane stackPane,int row, int column){
        String fullInfo = plotInfo;
        if (!fullInfo.contains(info.show())) {
            fullInfo += "\n\n" + info.show() + " (" + row + ", " + column + ")";
        }
        Tooltip tooltip = new Tooltip(fullInfo);
        tooltip.getStyleClass().add("tooltipStyle");
        Tooltip.install(stackPane, tooltip);
    }

    @FXML
    private void updateImages() throws IOException {
        gridPane.getChildren().clear();
        algoDefense.nextTurn();
        explitDatos();
        String updatedStats = algoDefense.getPlayerInfo();
        updatedStats += "\nTurn: " + algoDefense.getCurrentTurn();
        infoLabel.setText(updatedStats);
        if(algoDefense.gameOver()){
            Sound.get().stopMusic();
            if(algoDefense.getPlayer().isAlive()){
                App.setRoot("WIN");
                Sound.get().playFX("levelWin");
            }
            else{
                App.setRoot("LOSS");
                Sound.get().playFX("levelLose");
            }

        }
        printMap();
    }
    @FXML
    public void resetGame() {
        algoDefense.reset(algoDefense.getPlayer());
        gameBoard = App.algodefense.getGameboard();
        String updatedStats = algoDefense.getPlayerInfo();
        updatedStats += "\nTurn: " + algoDefense.getCurrentTurn();
        infoLabel.setText(updatedStats);
        consoleTextArea.setText("Game restarted!");
        printMap();
    }

    @FXML
    public void exit() {
        System.exit(0);
    }

    @FXML
    public void fullScreenMode() {
        if (!obtenerStageActual((Node) gridPane).isFullScreen()) {
            obtenerStageActual((Node) gridPane).setFullScreen(true);
            fullScreenMenuBarOption.setText("Exit full screen");
        } else {
            obtenerStageActual((Node) gridPane).setFullScreen(false);
            fullScreenMenuBarOption.setText("Full Screen");
        }
    }

    @FXML
    public void showHowToPlay() {
        Alert howToPlay = new Alert(Alert.AlertType.INFORMATION);
        howToPlay.setTitle("How to play");
        howToPlay.setHeaderText("Instructions");
        String instructions = "Welcome to AlgoDefense! Your goal is to avoid enemies reach the end of the path by building" +
                " defenses over the plots across the map. Just click on any plot to know which defenses you can build there.\n" +
                "Each defense has different prices and characteristics, so choose them carefully. Once you have finished building" +
                " your defenses, click \"Next turn\" button to let enemies advance.\nWe initially provided you with " +
                algoDefense.getPlayer().getPlayerCredits().getQuantity() + " credits to buy your first defenses.\nHow you gain" +
                " more credits? As your defenses destroy enemies, they will provide you with some.\n Like your defenses, different" +
                " enemies vary in skills. You can check their speed, life points and damage hovering your mouse over them.\n" +
                "Ants and spiders don't do much. They just go through the path.\nMoles are a bit more annoying. As they go" +
                " underground, towers can't harm them. You can just slow them down with sandy traps. Not only that, but also" +
                " do they speed up as the game advances.\nLastly, the owls. They will do two straight lines (first vertically" +
                " and then horizontally) to the end path. but if your towers damage them half their life points, they will go" +
                " through the shortest way to the end.\nGood luck and thanks for playing!";
        howToPlay.setContentText(instructions);
        howToPlay.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*Media media = new Media(getClass().getResource("/sound/backMusic.mp3").toString());
        this.mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.3);
        mediaPlayer.play();*/
        inicializarSonido();
        Sound.get().playMusic("temaPrincipal");
        Image aTerrainImage = new Image(getClass().getResource("/img/path.png").toString(), true);
        terrainImages.add(aTerrainImage);
        aTerrainImage = new Image(getClass().getResource("/img/dirt.png").toString(), true);
        terrainImages.add(aTerrainImage);
        aTerrainImage = new Image(getClass().getResource("/img/rock2.png").toString(), true);
        terrainImages.add(aTerrainImage);
        aTerrainImage = new Image(getClass().getResource("/img/start.png").toString(), true);
        terrainImages.add(aTerrainImage);
        aTerrainImage = new Image(getClass().getResource("/img/finish.png").toString(), true);
        terrainImages.add(aTerrainImage);
        aTerrainImage = new Image(getClass().getResource("/img/under-contruction.png").toString(), true);
        terrainImages.add(aTerrainImage);
        String updatedStats = algoDefense.getPlayerInfo();
        updatedStats += "\nTurn: " + algoDefense.getCurrentTurn();
        infoLabel.setText(updatedStats);
        consoleTextArea.setText("Events will be displayed here.");
        printMap();
    }

    private String bringStyles() {
        return "    -fx-padding: 10px 0;\n" +
                "    -fx-pref-width: 250px;\n" +
                "    -fx-letter-spacing: 2px;\n" +
                "    -fx-background-radius: 20px;\n" +
                "    -fx-font-family: 'Press Start 2P';\n" +
                "    -fx-text-fill: #ffffff;\n" +
                "    -fx-font-size: 14px;\n" +
                "    -fx-font-weight: 400;\n" +
                "    -fx-effect: innershadow(gaussian, rgba(1, 1, 1, 0.82), 3, 0, 0, 0);\n" +
                "    -fx-border-width: 4px;\n" +
                "    -fx-border-color: rgba(129, 229, 209, 0.51);\n" +
                "    -fx-border-radius: 20px;\n" +
                "    -fx-background-color: radial-gradient(center 20% 30%, radius 50%, rgba(75, 117, 137, 0.78) 0%, rgba(41, 87, 145, 0.72) 100%);";
    }
    private String setTextStyle() {
        return "    -fx-font-family: 'Press Start 2P';\n" +
                "    -fx-font-size: 14px;\n" +
                "    -fx-text-fill: #ffffff;";
    }
    @FXML
    private void explitDatos(){
        consoleTextArea.setText(Logger.getExit() + "\n ////////////////////////////////");
    }

    @FXML
    private void muteMusic() {
        ImageView innerButtonImg = (ImageView) musicButton.getGraphic();
        if (Sound.get().musicIsMute()) {
            Sound.get().muteMusic(false);
            innerButtonImg.setImage(new Image(getClass().getResource("/img/sound-on.png").toString()));
        } else {
            Sound.get().muteMusic(true);
            innerButtonImg.setImage(new Image(getClass().getResource("/img/sound-off.png").toString()));
        }
    }

    public void inicializarSonido() {
        Sound sound = Sound.get();
        sound.loadMusic("backMusic.mp3", "temaPrincipal");
        sound.loadSound("build-defense.mp3","buildDefense");
        sound.loadSound("insufficient-credits.wav","insufficientCredits");
        sound.loadSound("level-lose.wav","levelLose");
        sound.loadSound("level-win.wav","levelWin");
        sound.loadSound("spider_attack.mp3","spiderAttack");
        sound.loadSound("tower-attack.mp3","towerAttack");
        sound.modifyEffectVolume(50);
        sound.modifyMusicVolume(40);
    }
}
