package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.attack.SimpleRangeAttack;
import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;

public class WhiteTower extends Tower{

    public WhiteTower(Credit credit, Range rangeAttack, Damage damage, int constructionTurns, Point cordinatesTower){
        super(credit,constructionTurns, cordinatesTower);
        this.attack = new SimpleRangeAttack(rangeAttack,damage);
    }
    public String show(){
        return "White Tower:\n\tPrice: " + getCredits().getQuantity() + "\n\tRange:  3" + "\n\tDamage: 1";
    }
}
