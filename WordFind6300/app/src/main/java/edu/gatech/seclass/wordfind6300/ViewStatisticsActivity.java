package edu.gatech.seclass.wordfind6300;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewStatisticsActivity extends AppCompatActivity {
    TextView headerText;
    Button wordStatisticsButton;
    Button gameStatisticsButton;
    TableLayout layout;
    StatsDbHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_statistics);
        dbHelper = new StatsDbHelper(this);

        wordStatisticsButton = findViewById(R.id.btn_word_statistics);
        gameStatisticsButton = findViewById(R.id.btn_game_statistics);


        gameStatisticsButton.setEnabled(false);
        wordStatisticsButton.setEnabled(true);
        headerText = findViewById(R.id.text_statistics_header);
        layout = findViewById(R.id.statistics_layout);

        //Default to game stats
        generateGameStatistic();

    }

    public void viewGameStatistics(View v){
        headerText.setText("Game Statistics");
        gameStatisticsButton.setEnabled(false);
        wordStatisticsButton.setEnabled(true);
        generateGameStatistic();

    }

    public void viewWordStatistics(View v){
        headerText.setText("Word Statistics");
        gameStatisticsButton.setEnabled(true);
        wordStatisticsButton.setEnabled(false);
        generateWordStatistics();
    }

    public void generateGameStatistic() {
        //Source:
        //https://stackoverflow.com/questions/7117533/how-to-give-border-of-cells-in-tablelayout-in-android
        //https://stackoverflow.com/questions/18207470/adding-table-rows-dynamically-in-android
        //https://stackoverflow.com/questions/7008916/how-to-get-tablerow-id-dynamically-in-android


        layout.removeAllViews();
        TableRow tbrow0 = new TableRow(this);

        TextView tv0 = new TextView(this);
        TextView tv1 = new TextView(this);
        TextView tv2 = new TextView(this);
        TextView tv3 = new TextView(this);

        tv0.setText(" Game ID ");
        tv0.setTypeface(null, Typeface.BOLD);
        tv0.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);

        tv1.setText(" Score ");
        tv1.setTypeface(null, Typeface.BOLD);
        tv1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);

        tv2.setText(" Resets ");
        tv2.setTypeface(null, Typeface.BOLD);
        tv2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);

        tv3.setText(" Words Played ");
        tv3.setTypeface(null, Typeface.BOLD);
        tv3.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);

        tbrow0.addView(tv0);

        tbrow0.addView(tv1);
        tbrow0.addView(tv2);
        tbrow0.addView(tv3);
        layout.addView(tbrow0);

        List<List<String>> gameStatistics = dbHelper.getGameStatistics();
        Log.d("Tag", String.valueOf(gameStatistics));

        if (gameStatistics.size() > 0) {
            //Log.d("Tag", String.valueOf(gameStatistics.get(0).get(0)));
            //Log.d("Tag", String.valueOf(gameStatistics.get(0).get(1)));
            //Log.d("Tag", String.valueOf(gameStatistics.get(1).get(0)));

            for (int i = 0; i < gameStatistics.size(); i++){
                //
                String tempGameID = gameStatistics.get(i).get(0);
                String tempGameScore = gameStatistics.get(i).get(1);;
                String tempResetCount = gameStatistics.get(i).get(2);
                String tempWordCount = gameStatistics.get(i).get(3);
                final String tempBoardSize = gameStatistics.get(i).get(4);
                final String tempGameLength = gameStatistics.get(i).get(5);
                final String tempHighestWord = gameStatistics.get(i).get(6);
                //Log.d("Tag", tempGameID);
                TableRow row = new TableRow(this);
                row.setId(i);

                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);

                final TextView text_id = new TextView(this);
                TextView text_score = new TextView(this);
                TextView text_resets = new TextView(this);
                TextView text_words = new TextView(this);

                text_id.setText(tempGameID);
                text_id.setGravity(Gravity.CENTER);
                text_score.setText(tempGameScore);
                text_score.setGravity(Gravity.CENTER);
                text_resets.setText(tempResetCount);
                text_resets.setGravity(Gravity.CENTER);
                text_words.setText(tempWordCount);
                text_words.setGravity(Gravity.CENTER);

                row.addView(text_id);
                row.addView(text_score);
                row.addView(text_resets);
                row.addView(text_words);

                row.setClickable(true);
                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        detailedGameStats(v.getId(), text_id.getText().toString(), tempBoardSize, tempGameLength, tempHighestWord);
                    }
                });

                layout.addView(row);

            }
            TableRow row = new TableRow(this);
            row.setId(gameStatistics.size());

            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            final TextView text_id = new TextView(this);
            TextView text_score = new TextView(this);
            TextView text_resets = new TextView(this);
            TextView text_words = new TextView(this);

            text_id.setText("");
            text_id.setGravity(Gravity.CENTER);
            text_score.setText("");
            text_score.setGravity(Gravity.CENTER);
            text_resets.setText("");
            text_resets.setGravity(Gravity.CENTER);
            text_words.setText("");
            text_words.setGravity(Gravity.CENTER);

            row.addView(text_id);
            row.addView(text_score);
            row.addView(text_resets);
            row.addView(text_words);

            layout.addView(row);

        }

    }

    private void detailedGameStats(int id, String GameID, String boardSize, String gameLength, String highestWord) {
        TextView msg = new TextView(this);
        String str1 = "Board Size: " + boardSize;
        String str2 = "Game Length: " + gameLength + " minutes";
        String str3 = "Highest Scoring Word: " + highestWord;
        msg.setText(str1 +"\n"+ str2 +"\n"+ str3);

        msg.setGravity(Gravity.CENTER);
        msg.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
        msg.setPadding(10,10,10,10);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Statistics for Game # "+ GameID)
                .setNegativeButton("Return", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        builder.setView(msg);
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void generateWordStatistics() {
        //Source:
        //https://stackoverflow.com/questions/7117533/how-to-give-border-of-cells-in-tablelayout-in-android
        //https://stackoverflow.com/questions/18207470/adding-table-rows-dynamically-in-android
        //Alternate Colors:
        //https://stackoverflow.com/questions/34823756/possible-to-alternate-row-colors-in-textview

        layout.removeAllViews();
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        TextView tv1 = new TextView(this);

        tv0.setText(" Word ");
        tv0.setTypeface(null, Typeface.BOLD);
        tv0.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);

        tv1.setText(" Number of Plays ");
        tv1.setTypeface(null, Typeface.BOLD);
        tv1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);

        tbrow0.addView(tv0);
        tbrow0.addView(tv1);
        layout.addView(tbrow0);

        Map<String, Integer> wordList = new HashMap<String, Integer>();
        wordList = dbHelper.getWordStatistics();
        int wordListSize = wordList.size();
        //Log.d("Tag", Integer.toString(wordListSize));

        int counter = 0;
        for (HashMap.Entry wordElement: wordList.entrySet()){
            String word = (String) wordElement.getKey();
            int frequency = (int) wordElement.getValue();

            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            row.setId(counter);

            final TextView text_word = new TextView(this);
            TextView text_plays = new TextView(this);

            text_word.setText(word);
            text_word.setGravity(Gravity.CENTER);
            text_plays.setText(""+frequency);
            text_plays.setGravity(Gravity.CENTER);

            row.addView(text_word);
            row.addView(text_plays);

            layout.addView(row);
            counter += 1;

        }
        TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        row.setId(counter);

        final TextView text_word = new TextView(this);
        TextView text_plays = new TextView(this);

        text_word.setText("");
        text_word.setGravity(Gravity.CENTER);
        text_plays.setText("");
        text_plays.setGravity(Gravity.CENTER);

        row.addView(text_word);
        row.addView(text_plays);

        layout.addView(row);

    }

    //Forgot there was no detailed word stats
//    private void detailedWordStats(int id, String word) {
//        TextView msg = new TextView(this);
//        msg.setText("Word Statistics for "+ word);
//        msg.setGravity(Gravity.CENTER);
//        msg.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
//        msg.setPadding(10,10,10,10);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Word Statistics for "+ word)
//                .setNegativeButton("Return", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//
//                    }
//                });
//        builder.setView(msg);
//        AlertDialog alert = builder.create();
//        alert.show();
//    }

    //Main Menu Button
    public void returnToMain(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



}
