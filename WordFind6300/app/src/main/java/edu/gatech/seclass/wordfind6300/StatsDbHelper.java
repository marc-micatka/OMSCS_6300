package edu.gatech.seclass.wordfind6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsDbHelper extends SQLiteOpenHelper {
    // Source:
    //https://developer.android.com/training/data-storage/sqlite

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "StatsDb.db";
    Map<String, Integer> wordListCopy;



    private static final String SQL_CREATE_GAMETABLE_ENTRIES =
            "CREATE TABLE " + StatsDbContract.StatsDb.GAMETABLE_TABLE_NAME + " (" +
                    StatsDbContract.StatsDb._ID + " INTEGER PRIMARY KEY," +
                    StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_GAME_ID + " TEXT," +
                    StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_GAME_SCORE + " INTEGER," +
                    StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME__RESET_COUNT + " INTEGER," +
                    StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_WORD_COUNT_ + " INTEGER," +
                    StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_BOARD_SIZE + " TEXT," +
                    StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_MINUTES + " TEXT," +
                    StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_HIGHEST_WORD_SCORE + " INTEGER)";

    private static final String SQL_DELETE_GAMETABLE_ENTRIES =
            "DROP TABLE IF EXISTS " + StatsDbContract.StatsDb.GAMETABLE_TABLE_NAME;

    private static final String SQL_CREATE_WORDTABLE_ENTRIES =
            "CREATE TABLE " + StatsDbContract.StatsDb.WORDTABLE_TABLE_NAME + " (" +
                    StatsDbContract.StatsDb._ID + " INTEGER PRIMARY KEY," +
                    StatsDbContract.StatsDb.WORDTABLE_COLUMN_NAME_WORD + " TEXT," +
                    StatsDbContract.StatsDb.WORDTABLE_COLUMN_NAME_TIMES_PLAYED + " INTEGER)";

    private static final String SQL_DELETE_WORDTABLE_ENTRIES =
            "DROP TABLE IF EXISTS " + StatsDbContract.StatsDb.WORDTABLE_TABLE_NAME;

/*
    private static final String SQL_DELETE_DEFAULT_ENTRIES =
            "DROP TABLE IF EXISTS " + StatsDbContract.StatsDb.SETTINGS_TABLE_NAME;
*/
    private Context context;

    public StatsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_GAMETABLE_ENTRIES);
        db.execSQL(SQL_CREATE_WORDTABLE_ENTRIES);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_GAMETABLE_ENTRIES);
        db.execSQL(SQL_DELETE_WORDTABLE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    public void storeGameSummary(String gameId, int gameScore, int resetCount, int wordCount,
                                 String boardSize, int minutes, String highestWord) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_GAME_ID, gameId);
        values.put(StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_GAME_SCORE, gameScore);
        values.put(StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME__RESET_COUNT, resetCount);
        values.put(StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_WORD_COUNT_, wordCount);
        values.put(StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_BOARD_SIZE, boardSize);
        values.put(StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_MINUTES, minutes);
        values.put(StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_HIGHEST_WORD_SCORE, highestWord);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(StatsDbContract.StatsDb.GAMETABLE_TABLE_NAME, null, values);

//        System.out.println("newRowId: " + newRowId);

    }

    public List<List<String>> getGameStatistics() {
        // Gets the data repository in read mode
        SQLiteDatabase db = this.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] columns = {
                BaseColumns._ID,
                StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_GAME_ID,
                StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_GAME_SCORE,
                StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME__RESET_COUNT,
                StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_WORD_COUNT_,
                StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_BOARD_SIZE,
                StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_MINUTES,
                StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_HIGHEST_WORD_SCORE,

        };

        // sort results by game score, descending order
        String sortOrder =
                StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_GAME_SCORE + " DESC";

        Cursor cursor = db.query(
                StatsDbContract.StatsDb.GAMETABLE_TABLE_NAME,   // The table to query
                columns,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause. null for all data
                null,          // The values for the WHERE clause. null for all data
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );


        List<List<String>> allGameStatistics = new ArrayList<>();
        List<String> tempStats;

        int j = 0;
        while(cursor.moveToNext()) {
            tempStats = new ArrayList<>();
            tempStats.add(cursor.getString(
                    cursor.getColumnIndexOrThrow(StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_GAME_ID)));
            tempStats.add(cursor.getString(
                    cursor.getColumnIndexOrThrow(StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_GAME_SCORE)));
            tempStats.add(cursor.getString(
                    cursor.getColumnIndexOrThrow(StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME__RESET_COUNT)));
            tempStats.add(cursor.getString(
                    cursor.getColumnIndexOrThrow(StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_WORD_COUNT_)));
            tempStats.add(cursor.getString(
                    cursor.getColumnIndexOrThrow(StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_BOARD_SIZE)));
            tempStats.add(cursor.getString(
                    cursor.getColumnIndexOrThrow(StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_MINUTES)));
            tempStats.add(cursor.getString(
                    cursor.getColumnIndexOrThrow(StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_HIGHEST_WORD_SCORE)));

            //Log.d("Tag", String.valueOf(j));
            //Log.d("Tag", String.valueOf(tempStats));
            allGameStatistics.add(tempStats);
            j++;
        }
        cursor.close();
        return allGameStatistics;
    }

    public int getNextID() {
        // Gets the data repository in read mode
        SQLiteDatabase db = this.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] columns = {
                BaseColumns._ID,
                StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_GAME_ID
        };

        // sort results by game score, descending order
        String sortOrder =
                BaseColumns._ID + " DESC";

        Cursor cursor = db.query(
                StatsDbContract.StatsDb.GAMETABLE_TABLE_NAME,   // The table to query
                columns,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause. null for all data
                null,          // The values for the WHERE clause. null for all data
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List<String> singleGameStatistics = new ArrayList<String>();
        List<List<String>> allGameStatistics = new ArrayList<List<String>>();

        int j = 0;
        while(cursor.moveToNext()) {
            j++;
            singleGameStatistics.add(cursor.getString(
                    cursor.getColumnIndexOrThrow(StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_GAME_ID)));
            allGameStatistics.add(singleGameStatistics);
        }
        cursor.close();

        return j;
    }

    public List<List<String>> recentSettings() {
        // Gets the data repository in read mode
        SQLiteDatabase db = this.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] columns = {
                BaseColumns._ID,
                StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_GAME_ID,
                StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_BOARD_SIZE,
                StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_MINUTES,
        };

        // sort results by game score, descending order
        String sortOrder =
                BaseColumns._ID + " DESC";

        Cursor cursor = db.query(
                StatsDbContract.StatsDb.GAMETABLE_TABLE_NAME,   // The table to query
                columns,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause. null for all data
                null,          // The values for the WHERE clause. null for all data
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );


        List<List<String>> settings = new ArrayList<>();
        List<String> tempSettings;

        int j = 0;
        while(cursor.moveToNext()) {
            tempSettings = new ArrayList<>();
            tempSettings.add(cursor.getString(
                    cursor.getColumnIndexOrThrow(StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_GAME_ID)));
            tempSettings.add(cursor.getString(
                    cursor.getColumnIndexOrThrow(StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_BOARD_SIZE)));
            tempSettings.add(cursor.getString(
                    cursor.getColumnIndexOrThrow(StatsDbContract.StatsDb.GAMETABLE_COLUMN_NAME_MINUTES)));
            settings.add(tempSettings);
            j++;
        }
        cursor.close();
        return settings;
    }

    public Map<String, Integer> getWordStatistics() {
        // Gets the data repository in read mode
        SQLiteDatabase db = this.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] columns = {
                BaseColumns._ID,
                StatsDbContract.StatsDb.WORDTABLE_COLUMN_NAME_WORD,
                StatsDbContract.StatsDb.WORDTABLE_COLUMN_NAME_TIMES_PLAYED,

        };

        // sort results by game score, descending order
        String sortOrder =
                StatsDbContract.StatsDb.WORDTABLE_COLUMN_NAME_TIMES_PLAYED + " DESC";

        Cursor cursor = db.query(
                StatsDbContract.StatsDb.WORDTABLE_TABLE_NAME,   // The table to query
                columns,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause. null for all data
                null,          // The values for the WHERE clause. null for all data
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        Map<String, Integer> wordList = new HashMap<String, Integer>();

        int j = 0;
        while(cursor.moveToNext()) {
            j++;
            wordList.put(cursor.getString(
                    cursor.getColumnIndexOrThrow(StatsDbContract.StatsDb.WORDTABLE_COLUMN_NAME_WORD)),
                    cursor.getInt(
                            cursor.getColumnIndexOrThrow(StatsDbContract.StatsDb.WORDTABLE_COLUMN_NAME_TIMES_PLAYED)));
//            System.out.println("word loop #: " + j);;
        }
        cursor.close();

//        System.out.println("wordList: " + wordList);
        return wordList;
    }

    public void ingestWordList(List<String> wordListToIngest) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        wordListCopy = getWordStatistics();
        //loop through all words in wordListToIngest
        for (String word : wordListToIngest) {
            //if local wordList contains word, increment its count
            if (wordListCopy.containsKey(word)) {
                //grab the current count for the existing word
                int currentTimesPlayedVal = wordListCopy.get(word);

                // New value for one column
                int newTimesPlayedVal = currentTimesPlayedVal + 1;
                ContentValues values = new ContentValues();
                values.put(StatsDbContract.StatsDb.WORDTABLE_COLUMN_NAME_TIMES_PLAYED, newTimesPlayedVal);

                // Which row to update, based on the title
                String selection = StatsDbContract.StatsDb.WORDTABLE_COLUMN_NAME_WORD + " LIKE ?";
                String[] selectionArgs = { word };

                int count = db.update(
                        StatsDbContract.StatsDb.WORDTABLE_TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
//                System.out.println("word existed. updated " + count + " rows.");

            }
            //if local wordList doesn't contain word, add it
            else {
                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(StatsDbContract.StatsDb.WORDTABLE_COLUMN_NAME_WORD, word);
                values.put(StatsDbContract.StatsDb.WORDTABLE_COLUMN_NAME_TIMES_PLAYED, 1);

                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(StatsDbContract.StatsDb.WORDTABLE_TABLE_NAME, null, values);
//                System.out.println("word did not exist. created new row at location: " + newRowId);
            }
        }
//        System.out.println("word list ingested successfully. wordList: " + getWordStatistics());
    }


    private void updateWordListCopy() {
        this.wordListCopy = getWordStatistics();
    }


}

