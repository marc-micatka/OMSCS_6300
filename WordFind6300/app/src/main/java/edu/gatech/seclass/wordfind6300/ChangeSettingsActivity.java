package edu.gatech.seclass.wordfind6300;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;


public class ChangeSettingsActivity extends AppCompatActivity {

    String boardSize;
    int boardSpinnerPos = 0;
    int gameLength = 3;
    String currentVowel;
    String currentVowelWeight;
    String currentConsonant;
    String currentConsonantWeight;
    HashMap<String, Integer> letterWeights = new HashMap<String, Integer>();

    Spinner spinnerConsonantWeights;
    TextView textConsonantWeights;
    Spinner spinnerVowelWeights;
    TextView textVowelWeights;
    Spinner spinnerBoardSize;
    TextView gameLengthTextView;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_settings);

        //Consonant entry drop down
        spinnerConsonantWeights = findViewById(R.id.spinner_consonant_entry);
        textConsonantWeights = findViewById(R.id.text_consonant_weight_entry);

        //Vowel entry drop down
        spinnerVowelWeights = findViewById(R.id.spinner_vowel_entry);
        textVowelWeights = findViewById(R.id.text_vowel_weight_entry);

        //Board size drop down
        spinnerBoardSize = findViewById(R.id.spinner_board_size);

        //Game length text field
        gameLengthTextView = findViewById(R.id.text_game_length);

        //Game length seek bar
        seekBar = findViewById(R.id.seekbar_game_length);


        //Receive Values from Main Menu
        Intent intent = getIntent();
        if (intent.getExtras() != null){
            gameLength = intent.getExtras().getInt("Game Length");
            boardSize = intent.getExtras().getString("Board Size");

            //assert boardSize != null;
            switch (boardSize){
                case "4 x 4":
                    boardSpinnerPos = 0;
                    break;
                case "5 x 5":
                    boardSpinnerPos = 1;
                    break;
                case "6 x 6":
                    boardSpinnerPos = 2;
                    break;
                case "7 x 7":
                    boardSpinnerPos = 3;
                    break;
                case "8 x 8":
                    boardSpinnerPos = 4;
                    break;
            }
            letterWeights = (HashMap<String, Integer>)intent.getSerializableExtra("Letter Weights");
        }

        //Board size drop down
        ArrayAdapter<CharSequence> boardSizeAdapter = ArrayAdapter.createFromResource(this,R.array.spinner_board_size, android.R.layout.simple_spinner_item);
        boardSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBoardSize.setAdapter(boardSizeAdapter);
        spinnerBoardSize.setSelection(boardSpinnerPos);
        spinnerBoardSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                boardSize = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Vowel entry drop down
        ArrayAdapter<CharSequence> vowelWeightAdapter = ArrayAdapter.createFromResource(this,R.array.spinner_vowel_entry, android.R.layout.simple_spinner_item);
        vowelWeightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVowelWeights.setAdapter(vowelWeightAdapter);
        spinnerVowelWeights.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tempChar = parent.getItemAtPosition(position).toString();
                Integer tempWeight;

                if (letterWeights.containsKey(tempChar)){
                    tempWeight = letterWeights.get(tempChar);
                    textVowelWeights.setText(""+tempWeight);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Consonant entry drop down
        ArrayAdapter<CharSequence> consonantWeightAdapter = ArrayAdapter.createFromResource(this,R.array.spinner_consonant_entry, android.R.layout.simple_spinner_item);
        consonantWeightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConsonantWeights.setAdapter(consonantWeightAdapter);
        spinnerConsonantWeights.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tempChar = parent.getItemAtPosition(position).toString();
                Integer tempWeight;

                if (letterWeights.containsKey(tempChar)){
                    tempWeight = letterWeights.get(tempChar);
                    textConsonantWeights.setText(""+tempWeight);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        seekBar.setProgress(gameLength-1);

        gameLength = seekBar.getProgress() + 1;
        gameLengthTextView.setText("" + gameLength + " minutes");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gameLength = progress + 1;
                gameLengthTextView.setText("" + gameLength + " minute(s)");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
    //Main Menu Button
    public void returnToMain(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //Save Settings Button
    public void saveSettings(View v){
        Intent intent = new Intent(this, MainActivity.class);

        if(boardSize != null && !boardSize.isEmpty()) {intent.putExtra("Board Size", boardSize);}
        if(gameLength != 0){intent.putExtra("Game Length", gameLength);}
        if(!letterWeights.isEmpty()){intent.putExtra("Letter Weights", letterWeights);}

        String testLetterWeights;

        startActivity(intent);
    }

    //Add vowel weights
    public void addVowelWeight(View v){
        //Save settings here...
        Integer tempWeight = Integer.parseInt(textVowelWeights.getText().toString());
        String tempChar = spinnerVowelWeights.getSelectedItem().toString();

        if (tempWeight <= 5){
            letterWeights.put(tempChar,tempWeight);
            Toast.makeText(v.getContext(), tempChar + " Weight Updated to " + tempWeight.toString(),
                    Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(v.getContext(), tempChar + " New Weight must be less than 5!",
                    Toast.LENGTH_SHORT).show();
        }



    }

    //Add consonant weight
    public void addConsonantWeight(View v){
        //Save settings here...
        Integer tempWeight = Integer.parseInt(textConsonantWeights.getText().toString());
        String tempChar = spinnerConsonantWeights.getSelectedItem().toString();

        if (tempWeight <= 5){
            letterWeights.put(tempChar,tempWeight);
            Toast.makeText(v.getContext(), tempChar + " Weight Updated to " + tempWeight.toString(),
                    Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(v.getContext(), tempChar + " New Weight must be less than 5!" + tempWeight.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Add consonant weight
    public void resetSettings(final View v){

        TextView msg = new TextView(this);
        msg.setText("Are you sure you want to reset settings?");
        msg.setGravity(Gravity.CENTER);
        msg.setTextSize(TypedValue.COMPLEX_UNIT_DIP,24);
        msg.setPadding(10,10,10,10);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true)
                .setPositiveButton("Reset Settings", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        setDefaultValues();

                    }
                });
        builder.setNegativeButton("No", null);

        builder.setView(msg);
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void setDefaultValues(){
        //Define default values if no data from settings
        //Fill map
        String abcString = "ABCDEFGHIJKLMNOPQRSTUVQXYZ";

        for (int i = 0; i < abcString.length(); i++){
            char putChar = abcString.charAt(i);

            if (putChar == 'Q'){
                letterWeights.put("QU", 1);
            }
            else{
                letterWeights.put(String.valueOf(putChar), 1);
            }

        }
        boardSize = "4 x 4";
        gameLength = 3;

        seekBar.setProgress(gameLength-1);

        switch (boardSize){
            case "4 x 4":
                boardSpinnerPos = 0;
                break;
            case "5 x 5":
                boardSpinnerPos = 1;
                break;
            case "6 x 6":
                boardSpinnerPos = 2;
                break;
            case "7 x 7":
                boardSpinnerPos = 3;
                break;
            case "8 x 8":
                boardSpinnerPos = 4;
                break;
        }
        spinnerBoardSize.setSelection(boardSpinnerPos);
        spinnerConsonantWeights.setSelection(0);
        spinnerVowelWeights.setSelection(0);



    }



}
