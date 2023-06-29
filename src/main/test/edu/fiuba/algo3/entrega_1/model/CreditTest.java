package edu.fiuba.algo3.entrega_1.model;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.exceptions.InsufficientCredits;
import edu.fiuba.algo3.modelo.parser.EnemiesParser;
import edu.fiuba.algo3.modelo.parser.MapParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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


    static class MapParserTest {
        @Test
        void testPrintMap() {
            MapParser Reader = new MapParser("src\\main\\resources\\files\\mapa.json");
            JSONObject mapaObject = Reader.getObject();
            for (int i = 1; i <= mapaObject.size(); i++) {
                String key = String.valueOf(i);
                JSONArray rowArray = (JSONArray) mapaObject.get(key);
                for (Object element : rowArray) {
                    System.out.print(element.toString() + " ");
                }
                System.out.println();
            }
        }

      /*  @Test
        void  testPrintEnemies(){
            String file = "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\parser\\enemigos.json";
            EnemiesParser Reader = new EnemiesParser(file);
            JSONArray enemyObject = Reader.getArray();

            //System.out.println(enemyObject.toString());

            for (Object o : enemyObject) {

                JSONObject rowObject = (JSONObject) o;
                System.out.println(rowObject.toString());
            }
        }*/

    }
}
