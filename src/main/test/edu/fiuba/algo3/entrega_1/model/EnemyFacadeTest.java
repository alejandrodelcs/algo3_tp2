package edu.fiuba.algo3.entrega_1.model;

import edu.fiuba.algo3.modelo.facade.EnemyFacade;
import org.junit.jupiter.api.Test;

public class EnemyFacadeTest {
    @Test
    public void anEnemyStrategyIsCreated(){
        EnemyFacade enemyFacade = new EnemyFacade();
        System.out.println(enemyFacade.loadEnemiesStrategy().toString());
    }
}
