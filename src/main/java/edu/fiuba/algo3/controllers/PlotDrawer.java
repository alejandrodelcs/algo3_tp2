package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.gameboard.Plot;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class PlotDrawer {
    HashMap<String, Image> images = new HashMap<>();
    public PlotDrawer() {
        images.put("Dirt", new Image(getClass().getResource("/img/dirt.png").toString(), true));
        images.put("Stone", new Image(getClass().getResource("/img/rock2.png").toString(), true));
        images.put("Path", new Image(getClass().getResource("/img/path.png").toString(), true));
        images.put("FinishLine", new Image(getClass().getResource("/img/finish.png").toString(), true));
        images.put("Ant", new Image(getClass().getResource("/img/ant2.png").toString(), true));
        images.put("Spider", new Image(getClass().getResource("/img/spider.png").toString(), true));
        images.put("Mole", new Image(getClass().getResource("/img/mole.png").toString(), true));
        images.put("Owl", new Image(getClass().getResource("/img/owl.png").toString(), true));
        images.put("WhiteTower", new Image(getClass().getResource("/img/SilverTower.png").toString(), true));
        images.put("SilverTower", new Image(getClass().getResource("/img/magic2.png").toString(), true));
        images.put("UnderConstruction", new Image(getClass().getResource("/img/under-contruction.png").toString(), true));
        images.put("SandyTrap", new Image(getClass().getResource("/img/sandyTrap.png").toString(), true));
    }
    public StackPane drawAPlot(Plot plot) {
        System.out.println(plot.getClass());
        StackPane plotStackPane = new StackPane();
        ImageView mainImageView = new ImageView(images.get(plot.getClass().getSimpleName()));
        mainImageView.setFitHeight(50);
        mainImageView.setFitWidth(50);
        mainImageView.setPreserveRatio(true);
        plotStackPane.getChildren().add(mainImageView);

        ArrayList<Enemy> enemiesInPlot = plot.enemiesInPlot();
        Defense defenseInPlot = plot.getDefense();

        if ((defenseInPlot != null && !plot.readyToBuild()) && (defenseInPlot.isAvailable())) {
            ImageView defenseImageView = new ImageView(images.get(defenseInPlot.getClass().getSimpleName()));
            defenseImageView.setFitHeight(50);
            defenseImageView.setFitWidth(50);
            defenseImageView.setPreserveRatio(true);
            plotStackPane.getChildren().add(defenseImageView);
        }
        if ((defenseInPlot != null && !plot.readyToBuild()) && (!defenseInPlot.isAvailable())) {
            ImageView defenseImageView = new ImageView(images.get("UnderConstruction"));
            defenseImageView.setFitHeight(50);
            defenseImageView.setFitWidth(50);
            defenseImageView.setPreserveRatio(true);
            plotStackPane.getChildren().add(defenseImageView);
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
                for (Enemy enemy : enemiesInPlot) {
                    ImageView enemyImage = new ImageView(images.get(enemy.getClass().getSimpleName()));
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
