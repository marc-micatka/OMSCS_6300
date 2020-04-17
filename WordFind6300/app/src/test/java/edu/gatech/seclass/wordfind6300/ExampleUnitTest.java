package edu.gatech.seclass.wordfind6300;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private TestWordFind wordFind;
    private double delta_equal = 0.075;
    double delta_unequal = 0.50;
    double delta_vowel = 0.01;

    /*##############################################################################################*/
    @Test
    //UNIT TEST 1 - Testing random board, size 4, equal distribution
    public void testRandomBoard(){
        wordFind = new TestWordFind(4, true);
        double ratio = wordFind.testRandomBoard();
        assertEquals(1.0, ratio, delta_equal);
    }

    @Test
    //UNIT TEST 2 - Testing random board, size 5, equal distribution
    public void testRandomBoard2(){
        wordFind = new TestWordFind(5, true);
        double ratio = wordFind.testRandomBoard();
        assertEquals(1.0, ratio, delta_equal);
    }

    @Test
    //UNIT TEST 3 - Testing random board, size 6, equal distribution
    public void testRandomBoard3(){
        wordFind = new TestWordFind(6, true);
        double ratio = wordFind.testRandomBoard();
        assertEquals(1.0, ratio, delta_equal);
    }

    @Test
    //UNIT TEST 4 - Testing random board, size 7, equal distribution
    public void testRandomBoard4(){
        wordFind = new TestWordFind(7, true);
        double ratio = wordFind.testRandomBoard();
        assertEquals(1.0, ratio, delta_equal);
    }

    @Test
    //UNIT TEST 5 - Testing random board, size 8, equal distribution
    public void testRandomBoard5(){
        wordFind = new TestWordFind(8, true);
        double ratio = wordFind.testRandomBoard();
        assertEquals(1.0, ratio, delta_equal);
    }

    /*##############################################################################################*/
    @Test
    //UNIT TEST 6 - Testing random board, size 4, unequal distribution
    public void testRandomBoard6(){
        wordFind = new TestWordFind(4, false);
        double ratio = wordFind.testRandomBoard();
        assertEquals(5.0, ratio, delta_unequal);
    }

    @Test
    //UNIT TEST 7 - Testing random board, size 5, unequal distribution
    public void testRandomBoard7(){
        wordFind = new TestWordFind(5, false);
        double ratio = wordFind.testRandomBoard();
        assertEquals(5.0, ratio, delta_unequal);
    }

    @Test
    //UNIT TEST 8 - Testing random board, size 6, unequal distribution
    public void testRandomBoard8(){
        wordFind = new TestWordFind(6, false);
        double ratio = wordFind.testRandomBoard();
        assertEquals(5.0, ratio, delta_unequal);
    }

    @Test
    //UNIT TEST 9 - Testing random board, size 7, unequal distribution
    public void testRandomBoard9(){
        wordFind = new TestWordFind(7, false);
        double ratio = wordFind.testRandomBoard();
        assertEquals(5.0, ratio, delta_unequal);
    }

    @Test
    //UNIT TEST 10 - Testing random board, size 8, unequal distribution
    public void testRandomBoard10(){
        wordFind = new TestWordFind(8, false);
        double ratio = wordFind.testRandomBoard();
        assertEquals(5.0, ratio, delta_unequal);
    }

    /*##############################################################################################*/
    @Test
    //UNIT TEST 11 - vowel distribution
    public void testLetterCount(){
        int boardSize = 4;
        wordFind = new TestWordFind(boardSize, true);
        double numVowels = Math.ceil((float) (boardSize * boardSize) / 5.0);
        double vowelCount = wordFind.vowelCount();

        assertEquals(numVowels, vowelCount, delta_vowel);
    }

    @Test
    //UNIT TEST 12 - vowel distribution
    public void testLetterCount2(){
        int boardSize = 5;
        wordFind = new TestWordFind(boardSize, true);
        double numVowels = Math.ceil((float) (boardSize * boardSize) / 5.0);
        double vowelCount = wordFind.vowelCount();

        assertEquals(numVowels, vowelCount, delta_vowel);
    }

    @Test
    //UNIT TEST 13 - vowel distribution
    public void testLetterCount3(){
        int boardSize = 6;
        wordFind = new TestWordFind(boardSize, true);
        double numVowels = Math.ceil((float) (boardSize * boardSize) / 5.0);
        double vowelCount = wordFind.vowelCount();

        assertEquals(numVowels, vowelCount, delta_vowel);
    }

    @Test
    //UNIT TEST 14 - vowel distribution
    public void testLetterCount4(){
        int boardSize = 7;
        wordFind = new TestWordFind(boardSize, true);
        double numVowels = Math.ceil((float) (boardSize * boardSize) / 5.0);
        double vowelCount = wordFind.vowelCount();

        assertEquals(numVowels, vowelCount, delta_vowel);
    }

    @Test
    //UNIT TEST 15 - vowel distribution
    public void testLetterCount6(){
        int boardSize = 8;
        wordFind = new TestWordFind(boardSize, true);
        double numVowels = Math.ceil((float) (boardSize * boardSize) / 5.0);
        double vowelCount = wordFind.vowelCount();

        assertEquals(numVowels, vowelCount, delta_vowel);
    }

    /*##############################################################################################*/
    @Test
    //UNIT TEST 16 - game points calculator
    public void testGamePoints(){
        int boardSize = 4;
        wordFind = new TestWordFind(boardSize, true);
        int points = wordFind.initialGamePoints();

        assertEquals(0, points);
    }



}