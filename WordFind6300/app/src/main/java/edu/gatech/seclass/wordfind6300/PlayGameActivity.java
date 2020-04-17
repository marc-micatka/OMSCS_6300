package edu.gatech.seclass.wordfind6300;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class PlayGameActivity extends AppCompatActivity {
    String boardSizeStr;
    int boardSize;
    int gameLength = 3;
    HashMap<String, Integer> letterWeights = new HashMap<String, Integer>();
    String currentWord = new String();
    TextView countdownTimerText;
    CountDownTimer countdownTimer;

    int gameID;
    Boolean isOver = false;
    int gamePoints = 0;
    Board gameBoard;
    int wordCount = 0;
    int rerollCount = 0;
    int highestWordScore = 0;
    ArrayList<Pair<Integer, Integer>> wordSelection;

    String enteredWord;
    int enteredWordScore;

    String highestWord;
    ArrayList<String> wordList = new ArrayList<String>();

    TableLayout boardTable;
    StatsDbHelper dbHelper;
    String gameIDStr;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // bad solution right now

        setContentView(R.layout.activity_play_game);
        countdownTimerText = findViewById(R.id.text_countdown_timer);
        dbHelper = new StatsDbHelper(this);


        gameID = dbHelper.getNextID()+ 1;
        gameIDStr = Integer.toString(gameID);

        //Receive Values from Main Menu
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            //Source:
            //https://stackoverflow.com/questions/41588943/how-to-display-minutes-and-seconds-in-countdowntimer
            gameLength = intent.getExtras().getInt("Game Length");
            // converted boardSize to int upon retrieval to avoid need to re-encode it during use
            // might need to change settings to display 4x4 but only encode 4
            // as a workaround I pulled the first character
            boardSizeStr = intent.getExtras().getString("Board Size");
            switch (boardSizeStr){
                case "4 x 4":
                    boardSize = 4;
                    break;
                case "5 x 5":
                    boardSize = 5;
                    break;
                case "6 x 6":
                    boardSize = 6;
                    break;
                case "7 x 7":
                    boardSize = 7;
                    break;
                case "8 x 8":
                    boardSize = 8;
                    break;
            }
            letterWeights = (HashMap<String, Integer>) intent.getSerializableExtra("Letter Weights");
        }

        wordSelection = new ArrayList<Pair<Integer,Integer>>();
        boardTable = findViewById(R.id.board_tbl);
        gameBoard = new Board(boardSize,letterWeights);
        gameBoard.fillWithLetters();
        displayBoard();
        startTimer();

    }

    @Override
    protected void onPause() {
        super.onPause();
        //Update database
        dbHelper.storeGameSummary(  gameIDStr,
                                    gamePoints,
                                    rerollCount,
                                    wordList.size(),
                                    boardSizeStr,
                                    gameLength,
                                    highestWord);

        dbHelper.ingestWordList(wordList);
    }

    private void displayBoard() {
        boardTable.removeAllViews();
        for (int i = 0; i < boardSize; i++) {
            TableRow boardRow = new TableRow(this);
            TableRow.LayoutParams layout = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            for (int j = 0; j < boardSize; j++) {
                final Button letterBtn = new Button(this);
                letterBtn.setWidth(15);
                letterBtn.setHeight(15);
                letterBtn.setText(gameBoard.currentLayout[i][j]);
                boardRow.addView(letterBtn);
                final Boolean Selected = false;
                final Pair<Integer,Integer> buttonCoord = new Pair(new Integer(i),new Integer(j));
                letterBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (validateSelection(buttonCoord)) {
                            wordSelection.add(buttonCoord);
                            letterBtn.setBackgroundColor(Color.GREEN);
                            currentWord = currentWord.concat(letterBtn.getText().toString());
                            TextView wordDisplay = findViewById(R.id.textCurrentWordSelection);
                            wordDisplay.setText(currentWord);
                        }
                    }
                });
            }
            boardTable.addView(boardRow,i);
        }

    }

    boolean validateSelection(Pair<Integer,Integer> coordinate) {
        // if this is the first letter selected then it is valid
        if (wordSelection.size() == 0) {
            return true;
        }
        // if the letter has already been selected don't allow it to be selected again
        if (wordSelection.contains(coordinate)) {
            //Log.d("Tag", "False here");
            return false;
        }
        Integer lastx = wordSelection.get(wordSelection.size() - 1).first;
        Integer lasty = wordSelection.get(wordSelection.size() - 1).second;
        // attempt to validate whether the coordinate is adjacent to the last selected word and not the same word selected
        if (coordinate.first >= lastx -1 && coordinate.first <= lastx + 1 && coordinate.second >= lasty -1 && coordinate.second <= lasty + 1) {
            return true;
        }
        //Log.d("Tag", "And here");
        return false;
    }

    public void startTimer(){
        long millis = gameLength * 60 * 1000;

        countdownTimer = new CountDownTimer(millis, 1000) {

            public void onTick(long millisUntilFinished) {
                String timeLeft = String.format(Locale.getDefault(), "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);
                countdownTimerText.setText(timeLeft);
            }

            public void onFinish() {
                //Final score display
                displayFinalScore();
            }
        }.start();
    }

    //Return to Main Menu
    public void quitGame(View v){
        displayFinalScore();
    }

    public void mainMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void rerollBoard(View v){
        gameBoard = new Board(boardSize,letterWeights);
        gameBoard.fillWithLetters();
        gamePoints = gamePoints - 5;
        rerollCount += 1;
        displayBoard();
    }

    // Display Final Score
    public void displayFinalScore(){
        TextView msg = new TextView(this);
        msg.setText("Final Score: " + gamePoints);
        msg.setGravity(Gravity.CENTER);
        msg.setTextSize(TypedValue.COMPLEX_UNIT_DIP,32);
        msg.setPadding(10,10,10,10);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false)
                .setPositiveButton("Main Menu", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       mainMenu();

                    }
                });
        builder.setView(msg);
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void addWord(View v){
        if ((!currentWord.isEmpty() && wordList.isEmpty()  && currentWord.length() >= 2)|| (!wordList.contains(currentWord) && currentWord.length() >= 2)) {
            addWordPoints();
            addWordToList();
        }
        else if (wordList.contains(currentWord)){
            Toast.makeText(v.getContext(), "You already entered " + currentWord,
                    Toast.LENGTH_SHORT).show();
        }
        else if (currentWord.length() == 1){
            Toast.makeText(v.getContext(), "Words must be longer than 1 letter",
                    Toast.LENGTH_SHORT).show();
        }

        clearWord(v);
    }
    public void clearWord(View v){
        currentWord = "";
        TextView wordDisplay = findViewById(R.id.textCurrentWordSelection);
        wordDisplay.setText(currentWord);
        displayBoard();
        wordSelection = new ArrayList<Pair<Integer,Integer>>();

    }
    //Calculate points for entered word
    private void addWordPoints(){
        enteredWordScore = currentWord.length();

        if (enteredWordScore > highestWordScore){
            highestWord = currentWord;
            highestWordScore = enteredWordScore;

        }
        gamePoints += enteredWordScore;
    }

    //Append entered word to list
    private void addWordToList(){
        wordList.add(currentWord);

    }


}