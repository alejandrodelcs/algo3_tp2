package edu.fiuba.algo3.entrega_1.model;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.exceptions.InsufficientCredits;
import edu.fiuba.algo3.modelo.health.Damageable;
import edu.fiuba.algo3.modelo.health.Health;
import edu.fiuba.algo3.modelo.health.NotDamageable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//TODO: change name charged for subtract
public class CreditTest {
    @Test
    public void testChargeCreditIsTheCorrectOne(){
        int cant = 100;
        int creditsToLoad=50;
        int creditTotal=150;
        Credit credit = new Credit(cant);
        Credit creditLoad = new Credit(creditsToLoad);
        Credit creditFinal = new Credit(creditTotal);

        credit.chargeCredits(creditLoad);

        Assertions.assertTrue(credit.equalTo(creditFinal));
    }

    @Test
    public void testSubtractCreditsIsTheCorrectOne(){
        int cant = 100;
        int creditsSubtract=50;
        int creditTotal=50;
        Credit credit = new Credit(cant);
        Credit creditLoad = new Credit(creditsSubtract);
        Credit creditFinal = new Credit(creditTotal);

        credit.subtractCredits(creditLoad);

        Assertions.assertTrue(credit.equalTo(creditFinal));

    }

    @Test
    public void testInsufficientCreditsForWithdrawal(){
        int cant = 100;
        int creditsSubtract=200;
        Credit credit = new Credit(cant);
        Credit creditLoad = new Credit(creditsSubtract);

        assertThrows(InsufficientCredits.class,()->{
            credit.subtractCredits(creditLoad);;});
    }


}
