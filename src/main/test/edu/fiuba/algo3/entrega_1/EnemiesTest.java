package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.enemy.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnemiesTest {

    @Test
    public void antInitializationTest() {
        Ant ant = new Ant();
        assertEquals(1, ant.getSpeed());
        assertEquals(1, ant.getDamage());
        assertEquals(1, ant.getHealth());
    }

    @Test
    public void spiderInitializationTest() {
        Spider spider = new Spider();
        assertEquals(2, spider.getSpeed());
        assertEquals(2, spider.getDamage());
        assertEquals(2, spider.getHealth());

    }

    @Test
    public void spiderReceivesOneOfDamageAndHasOneOfHealth() {
        Spider spider = new Spider();
        spider.takeDamage(1);
        assertEquals(1, spider.getHealth());
    }
}