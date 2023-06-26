package edu.fiuba.algo3.modelo.gameboard;

import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.SandyTrap;
import edu.fiuba.algo3.modelo.defense.Trap;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.enemy.EnemyFactory;
import edu.fiuba.algo3.modelo.exceptions.NonConstructibleArea;
import edu.fiuba.algo3.modelo.exceptions.NonTowerContructibleArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

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
        if(defense.equals(Trap.class)){
            this.state = new Available();
        }
    }
    @Override
    public StackPane getStackPane(ArrayList<Image> terrainImages) {
        StackPane aStackPane = new StackPane();
        ImageView mainImageView = new ImageView(terrainImages.get(0));
        mainImageView.setFitHeight(50);
        mainImageView.setFitWidth(50);
        mainImageView.setPreserveRatio(true);
        aStackPane.getChildren().add(mainImageView);
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
