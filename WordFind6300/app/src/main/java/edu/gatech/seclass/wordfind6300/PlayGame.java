package edu.gatech.seclass.wordfind6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PlayGame extends AppCompatActivity {
    int gameID;
    Boolean isOver = false;
    int gamePoints = 0;
    Board gameBoard;
    int wordCount = 0;
    int rerollCount = 0;
    int highestWordScore = 0;
    int boardSize;

    String enteredWord;
    int enteredWordScore;

    String highestWord;
    ArrayList<String> wordList;

    HashMap<String, Integer> letterWeights;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        Button btn_reroll_board =(Button)findViewById(R.id.btn_reroll_board);
        btn_reroll_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayGame.this.rerollBoard();
            }
        });
        Button btn_quit_game =(Button)findViewById(R.id.btn_quit_game);
        btn_quit_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quitGame = new Intent(PlayGame.this,  MainActivity.class);
                PlayGame.this.startActivity(quitGame);            }
        });
    }
    public PlayGame() {

    }

    public PlayGame(int size, HashMap<String, Integer> weights){
        letterWeights = weights;
        boardSize = size;
        gameBoard = new Board(boardSize, letterWeights);

    }

    public void generateInitialBoard(){
        gameBoard.fillWithLetters();
    }

    //Reroll board and fill with random letters
    public void rerollBoard(){
        gameBoard.fillWithLetters();
        gamePoints += -5;
    }

    //Subtract 5 points for reroll
    public void addRerollPoints(){
        gamePoints = gamePoints - 5;
    }


    public void enterWord(String userEnteredWord){
        enteredWord = userEnteredWord;
        addWordPoints();
        addWordToList();

    }

    //Calculate points for entered word
    private void addWordPoints(){
        enteredWordScore = enteredWord.length();

        if (enteredWordScore > highestWordScore){
            highestWordScore = enteredWordScore;
            highestWord = enteredWord;
        }
        gamePoints += enteredWordScore;

    }

    //Append entered word to list
    private void addWordToList(){
        wordList.add(enteredWord);
    }


    public void printBoard(){
        for (int i = 0; i < gameBoard.boardWidth; i++){
            System.out.println(Arrays.toString(gameBoard.currentLayout[i]));
        }
    }

}
