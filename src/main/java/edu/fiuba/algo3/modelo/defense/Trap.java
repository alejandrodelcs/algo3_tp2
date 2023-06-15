package edu.fiuba.algo3.modelo.defense;
import edu.fiuba.algo3.modelo.attack.SlowDown;
import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.attack.SimpleRangeAttack;
import edu.fiuba.algo3.modelo.damage.Damage;

import java.awt.*;

public abstract class Trap extends Defense {
        public Trap(Credit credits, Point cordinatesTrap){
                this.credits = credits;
                this.coordinates = cordinatesTrap;
                this.state = new TemporallyState(attack);
        }


}
