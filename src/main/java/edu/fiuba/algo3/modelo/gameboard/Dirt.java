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
    public String display() {
        if(state.itsOccupied()){return "|&|";}
        else{
            if(enemyArrayList == null || enemyArrayList.isEmpty()){
                return "...";
            }
            return "ooo";
        }
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
    public Image printImage() {
        return new Image(getClass().getResource("/img/dirt.png").toString(),true);
    }

    @Override
    public StackPane getStackPane() {

        StackPane aStackPane = new StackPane();
        ImageView mainImageView = new ImageView(new Image(getClass().getResource("/img/dirt.png").toString(),true));
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
