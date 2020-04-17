package edu.gatech.seclass.wordfind6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class WordFindTests {
    PlayGame game;

    int boardSize;
    HashMap<String, Integer> equalWeights = new HashMap<String, Integer>();
    HashMap<String, Integer> unequalWeights = new HashMap<String, Integer>();

    public WordFindTests(int boardSize, Boolean equal){
        setup(boardSize, equal);
    }

    public void setup(int boardSize, Boolean equal) {
        equalLetterWeights(boardSize);
        unequalLetterWeights(boardSize);

        if (equal) {
            game = new PlayGame(boardSize, equalWeights);
        } else {
            game = new PlayGame(boardSize, unequalWeights);
        }
    }

    public void equalLetterWeights(int size) {
        String abcString = "ABCDEFGHIJKLMNOPQRSTUVQXYZ";

        for (int i = 0; i < abcString.length(); i++){
            char putChar = abcString.charAt(i);

            if (putChar == 'Q'){
                equalWeights.put(String.valueOf("QU"), 1);
            }
            else{
                equalWeights.put(String.valueOf(putChar), 1);
            }
        }
    }

    public void unequalLetterWeights(int size) {
        String abcString = "ABCDEFGHIJKLMNOPQRSTUVQXYZ";

        for (int i = 0; i < abcString.length(); i++) {
            char putChar = abcString.charAt(i);

            if (putChar == 'Q') {
                unequalWeights.put(String.valueOf("QU"), 1);
            }
            else if (putChar == 'A') {
                unequalWeights.put(String.valueOf(putChar), 5);
            }
            else {
                unequalWeights.put(String.valueOf(putChar), 1);
            }
        }
    }

    public Double testRandomness(){
        game.generateInitialBoard();
        Board board = game.gameBoard;

        String vowels = "AEIOU";

        int numIters = 10000;
        double[] vowelCount = new double[5];
        double ratio;

        for (int i = 0; i < numIters; i++){
            String testChar = board.randomLetter(true);
            int index = vowels.indexOf(testChar);
            vowelCount[index] += 1.0/numIters;
        }

        ratio = vowelCount[0]/vowelCount[1];
        System.out.println(ratio);
        return ratio;
    }
}





