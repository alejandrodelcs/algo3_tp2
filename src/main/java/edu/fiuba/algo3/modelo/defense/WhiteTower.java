package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;

import java.awt.*;

public class WhiteTower extends Tower{

    public WhiteTower(Credit credit, Range rangeAttack, Damage damage, int constructionTurns, Point cordinatesTower){
        super(credit,rangeAttack,damage,constructionTurns, cordinatesTower);}

}
