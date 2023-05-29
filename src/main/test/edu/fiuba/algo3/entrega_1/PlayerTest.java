package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayerTest {

    @Test
    public void whenNewGameIsOnPlayerHasTwentyLifePointsAndOneHundredCredits(){
        Player player = new Player("Player");

        Assertions.assertEquals(20, player.getPlayerLifePoints());
        Assertions.assertEquals(100, player.getPlayerCredits());
    }

    @Test
    public void playerGetsLifePointsReducedByTenPointDamage(){
        Player player = new Player("Player");

        player.getsDamage(10);

        Assertions.assertEquals(10, player.getPlayerLifePoints());
    }

    @Test
    public void whenPlayerGetsChargedWithTenCreditsPlayerShouldEndUpWithNinetyCredits(){
        Player player = new Player("Player");

        player.chargedCredits(10);

        Assertions.assertEquals(90, player.getPlayerCredits());
    }

    @Test
    public void whenPlayerEarnsTenCreditsPlayerShouldEndUpWithAHoundredAndTenCredits(){
        Player player = new Player("Player");

        player.getsCredit(10);

        Assertions.assertEquals(110,player.getPlayerCredits());
    }

    @Test
    public void whenPlayerHasZeroLivePointsPlayerHasLost(){
        Player player = new Player("Player");

        Assertions.assertThrows(PlayerIsDeadGameOver.class,()-> player.getsDamage(20));

    }
    @Test
    public void PlayerBuildsADefense(){
        //Player player = new Player("Player");
        //Tower towerStub = Mockito.mock(Tower.class);
        //Gameboard gameboardStub = Mockito.mock(Gameboard.class);
        //player.buildsADefense(towerStub,gameboardStub);

        //Assertions.assertEquals(90, player.getPlayerCredits() );
    }
    @Test
    public void CreatePlayerWithInvalidNameShouldThrowRuntimeError(){

        Assertions.assertThrows(InvalidPlayersName.class,()-> new Player("Roy"));

    }
}