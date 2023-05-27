package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void whenNewGameIsOnPlayerHasTwentyLifePointsAndOneHundredCredits(){
        Player player = new Player("Player");

        assertEquals(20, player.getPlayerLifePoints());
        assertEquals(100, player.getPlayerCredits());
    }

    @Test
    public void playerGetsLifePointsReducedByTenPointDamage(){
        Player player = new Player("Player");

        player.getsDamage(10);

        assertEquals(10, player.getPlayerLifePoints());
    }

    @Test
    public void whenPlayerGetsChargedWithTenCreditsPlayerShouldEndUpWithNinetyCredits(){
        Player player = new Player("Player");

        player.chargedCredits(10);

        assertEquals(90, player.getPlayerCredits());
    }

    @Test
    public void whenPlayerEarnsTenCreditsPlayerShouldEndUpWithAHoundredAndTenCredits(){
        Player player = new Player("Player");

        player.getsCredit(10);

        assertEquals(110,player.getPlayerCredits());
    }

    @Test
    public void whenPlayerHasZeroLivePointsPlayerHasLost(){
        Player player = new Player("Player");

        assertThrows(PlayerIsDeadGameOver.class,()->{
            player.getsDamage(20);});

    }
}