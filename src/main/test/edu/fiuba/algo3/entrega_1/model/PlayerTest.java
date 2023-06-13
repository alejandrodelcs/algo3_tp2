package edu.fiuba.algo3.entrega_1.model;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.exceptions.InvalidPlayersName;
import edu.fiuba.algo3.modelo.player.Player;
import edu.fiuba.algo3.modelo.exceptions.PlayerIsDeadGameOver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void whenNewGameIsOnPlayerHasTwentyLifePointsAndOneHundredCredits(){
        Player player = new Player("Player");
        Damage tenDamage = new Damage(10);
        Damage nineDamage = new Damage(9);
        Damage deathDamage = new Damage(1);
        Credit ninetyNineCredit = new Credit(99);


        player.getsDamage(tenDamage);
        Assertions.assertTrue(player.isAlive());
        player.getsDamage(nineDamage);
        player.subtractCredits(ninetyNineCredit);

        Assertions.assertTrue(player.hasFunds());
        Assertions.assertThrows(PlayerIsDeadGameOver.class,()-> player.getsDamage(deathDamage));

    }

    @Test
    public void whenPlayerHasZeroLivePointsPlayerHasLost(){
        Player player = new Player("Player");
        Damage deathDamage = new Damage(100);

        Assertions.assertThrows(PlayerIsDeadGameOver.class,()-> player.getsDamage(deathDamage));

    }
    @Test
    public void PlayerBuildsADefense(){
      /*  Player player = new Player("Player");
        WhiteTower whiteTower = new WhiteTower();
        Gameboard gameboardStub = Mockito.mock(Gameboard.class);
        player.buildsADefense(whiteTower,gameboardStub);

        Assertions.assertEquals(90, player.getPlayerCredits() );

       */
    }
    @Test
    public void CreatePlayerWithInvalidNameShouldThrowRuntimeError(){

        Assertions.assertThrows(InvalidPlayersName.class,()-> new Player("Roy"));

    }

}