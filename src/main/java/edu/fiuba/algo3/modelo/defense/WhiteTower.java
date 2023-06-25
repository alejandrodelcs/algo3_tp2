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

    @Override
    public ImageView getImage() {
        return new ImageView(new Image(getClass().getResource("/img/magic2.png").toString(),true));
    }
}
