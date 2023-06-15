package edu.fiuba.algo3.entrega_1.model;

import edu.fiuba.algo3.modelo.exceptions.EnemyObjectDoesNotExists;
import edu.fiuba.algo3.modelo.exceptions.FileIsEmpty;
import edu.fiuba.algo3.modelo.parser.EnemiesParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void test01() {
        Assertions.assertThrows(FileIsEmpty.class,()->  new EnemiesParser("src\\main\\java\\edu\\fiuba\\algo3\\modelo\\files\\empty.json"));
    }
    @Test
    public void test02() {
        EnemiesParser enemiesParser = new EnemiesParser("src\\main\\java\\edu\\fiuba\\algo3\\modelo\\files\\invalidFormat.json");
        enemiesParser.getArray();
        Assertions.assertThrows(EnemyObjectDoesNotExists.class,()-> enemiesParser.parserFile());
    }
}
