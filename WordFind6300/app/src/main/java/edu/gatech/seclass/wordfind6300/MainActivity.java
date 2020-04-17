package edu.gatech.seclass.wordfind6300;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    int gameLength;

    String boardSize;

    HashMap<String, Integer> letterWeights = new HashMap<String, Integer>();
    StatsDbHelper dbHelper;

    SharedPreferences gameSettings;
    private String sharedPrefFile =
            "com.example.android.WordFind6300";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new StatsDbHelper(this);

        int gameID;
        String gameIDStr;

        gameID = dbHelper.getNextID()+ 1;
        gameIDStr = Integer.toString(gameID);

        //Restore Settings
        gameSettings = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        loadFromPrefs();


        Intent intent = getIntent();
        //If coming from settings
        if (intent.getExtras() != null){
            gameLength = intent.getExtras().getInt("Game Length");
            boardSize = intent.getExtras().getString("Board Size");
            letterWeights = (HashMap<String, Integer>)intent.getSerializableExtra("Letter Weights");

        }

        //Log.d("letterweights", String.valueOf(letterWeights.get("E")));
    }
    @Override
    protected  void onPause() {
        // Update preferences
        super.onPause();

        SharedPreferences.Editor settingsEditor = gameSettings.edit();
        settingsEditor.putInt("Game Length", gameLength);
        settingsEditor.putString("Board Size", boardSize);

        for (String s : letterWeights.keySet()) {
            settingsEditor.putInt(s, letterWeights.get(s));

        }
        //Log.d("letterweights", String.valueOf(letterWeights.get("E")));
        settingsEditor.apply();
    }

    public void loadFromPrefs(){
        String abcString = "ABCDEFGHIJKLMNOPQRSTUVQXYZ";
        gameLength = gameSettings.getInt("Game Length", 3);
        boardSize = gameSettings.getString("Board Size", "4 x 4");

        int tempWeight;

        //Log.d("letterWeights", String.valueOf(gameSettings.getInt("E", 1)));

        for (int i = 0; i < abcString.length(); i++){
            char putChar = abcString.charAt(i);

            if (putChar == 'Q'){
                tempWeight = gameSettings.getInt("QU", 1);
                letterWeights.put("QU", tempWeight);
            }
            else{
                tempWeight = gameSettings.getInt(String.valueOf(putChar), 1);
                letterWeights.put(String.valueOf(putChar), tempWeight);
            }
        }
    }

    public void playGame(View v){
        Intent intent = new Intent(this, PlayGameActivity.class);

        //Send default settings to changeSettings
        if(boardSize != null && !boardSize.isEmpty()) {intent.putExtra("Board Size", boardSize);}
        if(gameLength != 0){intent.putExtra("Game Length", gameLength);}
        if(!letterWeights.isEmpty()){intent.putExtra("Letter Weights", letterWeights);}

        startActivity(intent);
    }

    public void viewStatistics(View v){
        Intent intent = new Intent(this, ViewStatisticsActivity.class);
        startActivity(intent);
    }

    public void changeSettings(View v){
        Intent intent = new Intent(this, ChangeSettingsActivity.class);

        //Send default settings to changeSettings
        if(boardSize != null && !boardSize.isEmpty()) {intent.putExtra("Board Size", boardSize);}
        if(gameLength != 0){intent.putExtra("Game Length", gameLength);}
        if(!letterWeights.isEmpty()){intent.putExtra("Letter Weights", letterWeights);}

        startActivity(intent);
    }
}
