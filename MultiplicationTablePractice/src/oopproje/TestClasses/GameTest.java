package oopproje;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;
    @Test
    void addAndGetScore() {
        game=new Game(3,4,5);
        ScoreTable score=new ScoreTable("furkan","sınav",50);
        game.addScore(score);
        assertTrue(game.getScores().contains(score));
    }

    @Test
    void getLimit1() {
        game=new Game(3,4,5);
        assertTrue(game.getLimit1()==3);
    }

    @Test
    void getLimit2() {
        game=new Game(3,4,5);
        assertTrue(game.getLimit2()==4);
    }

    @Test
    void getQuestionCount() {
        game=new Game(3,4,5);
        assertTrue(game.getQuestionCount()==5);
    }
}