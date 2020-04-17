package edu.gatech.seclass.wordfind6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TestWordFind {
    PlayGame game;

    int boardSize;
    HashMap<String, Integer> equalWeights = new HashMap<String, Integer>();
    HashMap<String, Integer> unequalWeights = new HashMap<String, Integer>();

    TestWordFind(int boardSize, Boolean equal){
        setup(boardSize, equal);
    }

    private void setup(int boardSize, Boolean equal) {
        equalLetterWeights(boardSize);
        unequalLetterWeights(boardSize);

        if (equal) {
            game = new PlayGame(boardSize, equalWeights);

        } else {
            game = new PlayGame(boardSize, unequalWeights);

        }
    }

    private void equalLetterWeights(int size) {
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

    private void unequalLetterWeights(int size) {
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

    Double testRandomBoard(){
        game.gameBoard.fillWithLetters();
        String vowels = "AEIOU";

        int numIters = 10000;
        double[] vowelCount = new double[5];
        double ratio;

        for (int i = 0; i < numIters; i++){
            String testChar = game.gameBoard.randomLetter(true);
            int index = vowels.indexOf(testChar);
            vowelCount[index] += 1.0/numIters;
        }

        ratio = vowelCount[0]/vowelCount[1];
        //System.out.println(ratio);
        return ratio;
    }

    double vowelCount(){
        game.gameBoard.fillWithLetters();
        //game.printBoard();
        //System.out.println(game.gameBoard.numVowels);
        String vowels = "AEIOU";
        int numIters = 1;
        double vowelCount = 0;

        for (int height = 0; height < game.gameBoard.boardHeight; height++){
            for (int width = 0; width < game.gameBoard.boardWidth; width ++){
                String tempStr = game.gameBoard.currentLayout[height][width];
                if (vowels.contains(tempStr)){
                    vowelCount += 1.0;
                }
            }
        }
        //System.out.println(vowelCount);
        return vowelCount;
    }

    int initialGamePoints(){
        game.gameBoard.fillWithLetters();
        return game.gamePoints;
    }


}





