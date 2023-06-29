package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.modelo.gameboard.Plot;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PlotDrawer {
    GameBoard gameBoard;
    HashMap<String, Image> images = new HashMap<>();
    HashMap<String, Integer> rotationValues = new HashMap<>();
    public PlotDrawer(GameBoard gameboard) {
        this.gameBoard = gameboard;
        images.put("Dirt", new Image(getClass().getResource("/img/dirt.png").toString(), true));
        images.put("Stone", new Image(getClass().getResource("/img/rock2.png").toString(), true));
        images.put("Path", new Image(getClass().getResource("/img/path.png").toString(), true));
        images.put("FinishLine", new Image(getClass().getResource("/img/finish.png").toString(), true));
        images.put("Ant", new Image(getClass().getResource("/img/ant2.png").toString(), true));
        images.put("Spider", new Image(getClass().getResource("/img/spider.png").toString(), true));
        images.put("Mole", new Image(getClass().getResource("/img/mole.png").toString(), true));
        images.put("Owl", new Image(getClass().getResource("/img/owl.png").toString(), true));
        images.put("WhiteTower", new Image(getClass().getResource("/img/magic2.png").toString(), true));
        images.put("SilverTower", new Image(getClass().getResource("/img/SilverTower.png").toString(), true));
        images.put("UnderConstruction", new Image(getClass().getResource("/img/under-contruction.png").toString(), true));
        images.put("SandyTrap", new Image(getClass().getResource("/img/sandyTrap.png").toString(), true));
        rotationValues.put("North", 0);
        rotationValues.put("West", 270);
        rotationValues.put("South", 180);
        rotationValues.put("East", 90);
    }
    public StackPane drawAPlot(Plot plot, int row, int column) {
        StackPane plotStackPane = new StackPane();
        ImageView mainImageView = new ImageView(images.get(plot.getClass().getSimpleName()));
        mainImageView.setFitHeight(50);
        mainImageView.setFitWidth(50);
        mainImageView.setPreserveRatio(true);
        plotStackPane.getChildren().add(mainImageView);
        String enemiesRotation = "North";
        Point plotCoords = new Point(column, row);

        ArrayList<Enemy> enemiesInPlot = plot.enemiesInPlot();
        Defense defenseInPlot = plot.getDefense();

        if (defenseInPlot != null && !plot.readyToBuild()) {
            if (defenseInPlot.isAvailable()) {
                ImageView defenseImageView = new ImageView(images.get(defenseInPlot.getClass().getSimpleName()));
                defenseImageView.setFitHeight(50);
                defenseImageView.setFitWidth(50);
                defenseImageView.setPreserveRatio(true);
                plotStackPane.getChildren().add(defenseImageView);
            } else if (!defenseInPlot.isDestroyed()){
                ImageView defenseImageView = new ImageView(images.get("UnderConstruction"));
                defenseImageView.setFitHeight(50);
                defenseImageView.setFitWidth(50);
                defenseImageView.setPreserveRatio(true);
                plotStackPane.getChildren().add(defenseImageView);
            }
        }

        if (enemiesInPlot != null) {
            int enemiesAmount = enemiesInPlot.size();
            if (enemiesAmount > 1) {
                Circle amountIndicator = new Circle(12);
                amountIndicator.setFill(Color.rgb(200, 0, 0, 0.6));
                Text text = new Text(String.valueOf(enemiesAmount));
                Insets innerMargin = new Insets(1, 25, 25, 1);
                StackPane.setMargin(amountIndicator, innerMargin);
                StackPane.setMargin(text, innerMargin);
                plotStackPane.getChildren().addAll(amountIndicator, text);
            } else {
                if (gameBoard.getEnemyPath().contains(plotCoords)) {
                    Point followingPlotCoords = gameBoard.getNextPath(plotCoords);
                    if (followingPlotCoords.y > plotCoords.y) {
                        enemiesRotation = "South";
                    } else if (followingPlotCoords.x < plotCoords.x) {
                        enemiesRotation = "West";
                    } else if (followingPlotCoords.x > plotCoords.x) {
                        enemiesRotation = "East";
                    }
                }
                for (Enemy enemy : enemiesInPlot) {
                    ImageView enemyImage = new ImageView(images.get(enemy.getClass().getSimpleName()));
                    if (enemy.getClass().getSimpleName() != "Mole") {
                        enemyImage.setRotate(rotationValues.get(enemiesRotation));
                    }
                    enemyImage.setFitWidth(40);
                    enemyImage.setFitHeight(40);
                    enemyImage.setPreserveRatio(true);
                    plotStackPane.getChildren().add(enemyImage);
                }
            }
        }
        plotStackPane.maxHeight(50);
        plotStackPane.maxWidth(50);
        return plotStackPane;
    }
}
