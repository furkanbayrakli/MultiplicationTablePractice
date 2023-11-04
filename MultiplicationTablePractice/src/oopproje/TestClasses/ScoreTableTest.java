package oopproje;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTableTest {
    private ScoreTable scoreTable;

    @Test
    void getName() {
        scoreTable=new ScoreTable("furkan","sınav",50);
        assertTrue(scoreTable.getName().compareToIgnoreCase("furkan")==0);
    }

    @Test
    void getExamName() {
        scoreTable=new ScoreTable("furkan","sınav",50);
        assertTrue(scoreTable.getExamName().compareToIgnoreCase("sınav")==0);
    }

    @Test
    void getScore() {
        scoreTable=new ScoreTable("furkan","sınav",50);
        assertTrue(scoreTable.getScore()==50);
    }
}