package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.attack.SlowDown;
import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;

public class SandyTrap extends Trap {
    private int turns = 0;
    private int operativeTurn = 0;
    public void constructionFinished(){}
    public SandyTrap(Credit credits, Point cordinatesTrap, int operativeTurns){
        super(credits,cordinatesTrap, operativeTurns);
        Range cero = new Range(0);
        this.attack = new SlowDown(cero);
        this.operativeTurn = operativeTurns;
    }
    public void updateSandyTrap(){
        turns ++;
        if ( turns == operativeTurn ) {
            this.destructed();
        }
    }
    public String show(){
        return "Sandy Trap:\n\tPrice: " + getCredits().getQuantity() + "\n\tDescription: 50% slows down all enemies";
    }

}
