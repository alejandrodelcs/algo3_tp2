package edu.fiuba.algo3.entrega_1.model;
import edu.fiuba.algo3.modelo.defense.Tower;

public class TurnTest {/*
    @Test
    public void test01TowerDoesNotFinishBuild(){
        EnemyFacade enemyFacade = new EnemyFacade();
        Turn turn = new Turn(enemyFacade.loadEnemiesStrategy());
        Tower tower = new TowerFactory().createTower("SilverTower",new Point(2,3));
        ArrayList<Tower> listTower =  new ArrayList<Tower>();
        listTower.add(tower);
        turn.passTurn(listTower);
        Enemy newSpider = new EnemyFactory().createSpider();
        //Assertions.assertThrows(TowerIsUnderConstruction.class,() -> {
            //listTower.get(0).attack(newSpider); } );TODO: Tower Attack implementation
    }
    @Test
    public void test02TowerFinishBuilingInTwoTurnAndKillASpider(){
        EnemyFacade enemyFacade = new EnemyFacade();
        Turn turn = new Turn(enemyFacade.loadEnemiesStrategy());
        Tower tower = new TowerFactory().createTower("SilverTower",new Point(2,3));
        ArrayList<Tower> listTower =  new ArrayList<Tower>();
        listTower.add(tower);
        turn.passTurn(listTower);
        turn.passTurn(listTower);
        Enemy newSpider = new EnemyFactory().createSpider();
        //listTower.get(0).attack(newSpider);
        //Assertions.assertTrue(newSpider.enemyDied());
    }*/
}


        /*
        @Test
        public void tes01thePlayerResetTurnItShouldNotBeCounted(){
                Player player = new Player("Player01");
                Turn turn = new PlayerTurn(player);
                //Assertions.assertEquals(true,turn.passTurn());

        }

        @Test
        public void test02duringTheExecutionOfTheTurnTheEnemyShouldMakeAMoveInGameboard(){
                Turn turn = new EnemyTurn();
                turn.executeTurn();

        }

        @Test
        public void test03theTimeRequiredToBuildAWhiteTowerShouldBeLimitedToASingleTurn(){
                Player player = new Player("Player01");
                Turn turn = new PlayerTurn(player);
                turn.executeTurn();

        }

        @Test
        public void test04theTimeRequiredToBuildASilverTowerShouldBeLimitedToATwoTurns(){
                Player player = new Player("Player01");
                Turn turn = new PlayerTurn(player);
                turn.executeTurn();
                turn.executeTurn();

        }
        */

