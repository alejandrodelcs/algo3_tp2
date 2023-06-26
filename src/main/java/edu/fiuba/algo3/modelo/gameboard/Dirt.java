package edu.fiuba.algo3.modelo.gameboard;

import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.exceptions.NonTrapConstructibleArea;
import edu.fiuba.algo3.modelo.exceptions.TheEnemyCannotBeOutsideTheRunway;
import edu.fiuba.algo3.modelo.exceptions.ThereCannotBeEnemiesInThisPlot;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class Dirt extends Plot{
    public Dirt(){
        state = new Available();
        this.enemyArrayList = new ArrayList<>();
    }
    public ArrayList<Enemy> enemyArrayList;

    @Override
    public void setEnemy(ArrayList<Enemy> enemyList) {this.enemyArrayList = enemyList;}
    @Override
    public String show() {
        return "Dirt";
    }
    @Override
    public ArrayList<Enemy> enemiesInPlot(){
        if (enemyArrayList != null) {
            return enemyArrayList;
        }
        return new ArrayList<Enemy>();
    }
    public void addEnemyToPath(Enemy newEnemy){this.enemyArrayList.add(newEnemy);};
    public void setDefense(Defense defense){
        this.defense = defense;
        this.state = new Occupied();
    }
    @Override
    public void removeDefense(Defense defense){
        this.state = new Available();
    }

    @Override
    public Image printImage() {
        return new Image(getClass().getResource("/img/dirt.png").toString(),true);
    }

    @Override
    public StackPane getStackPane(ArrayList<Image> terrainImages) {

        StackPane aStackPane = new StackPane();
        ImageView mainImageView = new ImageView(terrainImages.get(1));
        mainImageView.setFitHeight(50);
        mainImageView.setFitWidth(50);
        mainImageView.setPreserveRatio(true);
        aStackPane.getChildren().add(mainImageView);

        if (defense != null && this.state.itsOccupied()) {
            ImageView defenseImageView = defense.getImage();
            defenseImageView.setFitHeight(50);
            defenseImageView.setFitWidth(50);
            defenseImageView.setPreserveRatio(true);
            aStackPane.getChildren().add(defenseImageView);
        }
        if (enemyArrayList != null) {
            for (Enemy enemy : enemyArrayList) {
                ImageView enemyImage = enemy.getImage();
                enemyImage.setFitWidth(50);
                enemyImage.setFitHeight(50);
                enemyImage.setPreserveRatio(true);
                aStackPane.getChildren().add(enemyImage);
            }
        }
        aStackPane.maxHeight(50);
        aStackPane.maxWidth(50);
        return aStackPane;
    }
}
