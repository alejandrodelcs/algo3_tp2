package edu.fiuba.algo3.entrega_1.model;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.exceptions.InsufficientCredits;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreditTest {
    @Test
    public void testChargedCreditIsTheCorrectOne(){
        int cant = 100;
        int creditsToLoad=50;
        int creditTotal=150;
        Credit credit = new Credit(cant);
        Credit creditLoad = new Credit(creditsToLoad);
        Credit creditFinal = new Credit(creditTotal);

        credit.chargedCredits(creditLoad);

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
        int creditTotal=50;
        Credit credit = new Credit(cant);
        Credit creditLoad = new Credit(creditsSubtract);
        Credit creditFinal = new Credit(creditTotal);

        assertThrows(InsufficientCredits.class,()->{
            credit.subtractCredits(creditLoad);;});
    }

}
