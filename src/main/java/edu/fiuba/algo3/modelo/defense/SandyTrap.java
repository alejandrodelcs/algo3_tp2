package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.attack.SlowDown;
import edu.fiuba.algo3.modelo.credit.Credit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;

public class SandyTrap extends Trap {
    public void constructionFinished(){}
    public SandyTrap(Credit credits, Point cordinatesTrap, int operativeTurns){
        super(credits,cordinatesTrap, operativeTurns);
        Range cero = new Range(0);
        this.attack = new SlowDown(cero);
    }

    @Override
    public ImageView getImage() {
        return new ImageView(new Image(getClass().getResource("/img/sandyTrapCastle.png").toString(),true));
    }

    public String show(){
        return "Sandy Trap:\n\tPrice: " + getCredits().getQuantity() + "\n\tDescription: 50% slows down all enemies";
    }

}
