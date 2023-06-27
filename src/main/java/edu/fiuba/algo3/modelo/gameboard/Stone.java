package edu.fiuba.algo3.modelo.gameboard;

import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.exceptions.NonConstructibleArea;
import edu.fiuba.algo3.modelo.exceptions.TheEnemyCannotBeOutsideTheRunway;
import edu.fiuba.algo3.modelo.exceptions.ThereCannotBeEnemiesInThisPlot;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.awt.*;
import java.util.ArrayList;

public class Stone extends Plot{
    public ArrayList<Enemy> enemyArrayList;
    public Stone(){
        state = new NotPlotAvailability();
        this.enemyArrayList = new ArrayList<Enemy>();
    }

    @Override
    public void setEnemy(ArrayList<Enemy> enemyList) { this.enemyArrayList = enemyList; }
    @Override
    public String show() {
        return "Stone";
    }
    @Override
    public ArrayList<Enemy> enemiesInPlot(){
        if (enemyArrayList != null) {
            return enemyArrayList;
        }
        return new ArrayList<Enemy>();}
    public void addEnemyToPath(Enemy newEnemy){this.enemyArrayList.add(newEnemy);};

    @Override
    public void setDefense(Defense defense) {
        throw new NonConstructibleArea();
    }
    @Override
    public Image printImage() {
        return new Image(getClass().getResource("/img/rock2.png").toString(),true);
    }
    @Override
    public void removeDefense(Defense defense) {
        throw new NonConstructibleArea();
    }

    @Override
    public StackPane getStackPane(ArrayList<Image> terrainImages) {

        StackPane aStackPane = new StackPane();
        ImageView mainImageView = new ImageView(terrainImages.get(2));
        mainImageView.setFitHeight(50);
        mainImageView.setFitWidth(50);
        mainImageView.setPreserveRatio(true);
        aStackPane.getChildren().add(mainImageView);
        for (Enemy enemy : enemyArrayList) {
            ImageView enemyImage = enemy.getImage();
            enemyImage.setFitWidth(50);
            enemyImage.setFitHeight(50);
            enemyImage.setPreserveRatio(true);
            aStackPane.getChildren().add(enemyImage);
        }
        aStackPane.maxHeight(50);
        aStackPane.maxWidth(50);
        return aStackPane;
    }
}
