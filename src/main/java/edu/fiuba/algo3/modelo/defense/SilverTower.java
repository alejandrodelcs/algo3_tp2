package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;

import java.awt.*;

public class SilverTower extends Tower{

    public SilverTower(Credit credit, Range rangeAttack, Damage damage, int constructionTurns, Point cordinatesTower){
        super(credit,rangeAttack,damage,constructionTurns, cordinatesTower);}

}
