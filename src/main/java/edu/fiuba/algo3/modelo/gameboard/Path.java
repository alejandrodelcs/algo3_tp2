package edu.fiuba.algo3.modelo.gameboard;

import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.SandyTrap;
import edu.fiuba.algo3.modelo.defense.Trap;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;

public class Path extends Plot{
    public ArrayList<Enemy> enemyArrayList;

    public Path(){
        this.state = new Available();
        this.enemyArrayList = new ArrayList<Enemy>();
    }
    @Override
    public void setEnemy(ArrayList<Enemy> enemyList) {this.enemyArrayList = enemyList;}
    public void addEnemyToPath(Enemy newEnemy){ this.enemyArrayList.add(newEnemy); }
    @Override
    public String show() {
        return "Path";
    }
    @Override
    public ArrayList<Enemy> enemiesInPlot(){
        if (enemyArrayList != null) {
            return enemyArrayList;
        }
        return new ArrayList<Enemy>();
    }

    @Override
    public void setDefense(Defense defense) {
        if (defense.getClass() == SandyTrap.class) {
            this.defense = defense;
            this.state = new Occupied();
        }
    }

    @Override
    public Image printImage() {
        return new Image(getClass().getResource("/img/path.png").toString(),true);
    }

    @Override
    public void removeDefense(Defense defense) {
            this.state = new Available();
    }
    @Override
    public StackPane getStackPane(ArrayList<Image> terrainImages) {

        StackPane aStackPane = new StackPane();
        ImageView mainImageView = new ImageView(terrainImages.get(0));
        mainImageView.setFitHeight(50);
        mainImageView.setFitWidth(50);
        mainImageView.setPreserveRatio(true);
        aStackPane.getChildren().add(mainImageView);

        if (defense != null && this.state.itsOccupied() && defense.isAvailable()) {
            ImageView defenseImageView = defense.getImage();
            defenseImageView.setFitHeight(50);
            defenseImageView.setFitWidth(50);
            defenseImageView.setPreserveRatio(true);
            aStackPane.getChildren().add(defenseImageView);
        }

        if (enemyArrayList != null) {
            int enemiesAmount = enemyArrayList.size();
            if (enemiesAmount > 1) {
                Circle amountIndicator = new Circle(12);
                amountIndicator.setFill(Color.rgb(200, 0, 0, 0.6));
                Text text = new Text("" + enemiesAmount);
                Insets innerMargin = new Insets(1, 25, 25, 1);
                aStackPane.setMargin(amountIndicator, innerMargin);
                aStackPane.setMargin(text, innerMargin);
                aStackPane.getChildren().addAll(amountIndicator, text);
            } else {
                for (Enemy enemy : enemyArrayList) {
                    ImageView enemyImage = enemy.getImage();
                    enemyImage.setFitWidth(40);
                    enemyImage.setFitHeight(40);
                    enemyImage.setPreserveRatio(true);
                    aStackPane.getChildren().add(enemyImage);
                }
            }
        }

        aStackPane.maxHeight(50);
        aStackPane.maxWidth(50);
        return aStackPane;
    }
}
