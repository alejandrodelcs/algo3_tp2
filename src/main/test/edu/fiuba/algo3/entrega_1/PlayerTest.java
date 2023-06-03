package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.exceptions.InvalidPlayersName;
import edu.fiuba.algo3.modelo.player.Player;
import edu.fiuba.algo3.modelo.exceptions.PlayerIsDeadGameOver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void whenNewGameIsOnPlayerHasTwentyLifePointsAndOneHundredCredits(){
        Player player = new Player("Player");

        player.getsDamage(10);
        player.getsDamage(9);

        Assertions.assertTrue(player.isAlive());
        Assertions.assertEquals(100, player.getPlayerCredits().getQuantity());//TODO: change get quantity test behavior
    }

    @Test
    public void playerGetsLifePointsReducedByTenPointDamage(){
        Player player = new Player("Player");

        player.getsDamage(10);

        Assertions.assertTrue(player.isAlive());
    }

    @Test
    public void whenPlayerGetsChargedWithTenCreditsPlayerShouldEndUpWithNinetyCredits(){
        Player player = new Player("Player");
        Credit credit = new Credit(10);

        player.getsCredit(credit);

        Assertions.assertEquals(90, player.getPlayerCredits().getQuantity());//TODO: test behaviour
    }

    @Test
    public void whenPlayerEarnsTenCreditsPlayerShouldEndUpWithAHoundredAndTenCredits(){
        Player player = new Player("Player");
        Credit credit = new Credit(10);


        player.chargedCredits(credit);

        Assertions.assertEquals(110,player.getPlayerCredits().getQuantity());
    }

    @Test
    public void whenPlayerHasZeroLivePointsPlayerHasLost(){
        Player player = new Player("Player");

        Assertions.assertThrows(PlayerIsDeadGameOver.class,()-> player.getsDamage(20));

    }
    @Test
    public void PlayerBuildsADefense(){
      /*  Player player = new Player("Player");
        WhiteTower whiteTower = new WhiteTower();
        Gameboard gameboardStub = Mockito.mock(Gameboard.class);
        player.buildsADefense(whiteTower,gameboardStub);

        Assertions.assertEquals(90, player.getPlayerCredits() );
    */}
    @Test
    public void CreatePlayerWithInvalidNameShouldThrowRuntimeError(){

        Assertions.assertThrows(InvalidPlayersName.class,()-> new Player("Roy"));

    }
}