package edu.gatech.seclass.wordfind6300;

import android.provider.BaseColumns;

public final class StatsDbContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private StatsDbContract() {}

    /* Inner class that defines the table contents */
    public static class StatsDb implements BaseColumns {

        public static final String SETTINGS_TABLE_NAME = "currentSettings";
        public static final String SETTINGS_COLUMN_NAME_CURRENT_SIZE = "currentBoardSize";
        public static final String SETTINGS_COLUMN_NAME_GAME_LENGTH = "currentGameLength";
        public static final String SETTINGS_COLUMN_NAME_LETTER_WEIGHTS = "currentLetterWeights";

        public static final String GAMETABLE_TABLE_NAME = "gameStats";
        public static final String GAMETABLE_COLUMN_NAME_GAME_ID = "gameId";
        public static final String GAMETABLE_COLUMN_NAME_GAME_SCORE = "gameScore";
        public static final String GAMETABLE_COLUMN_NAME__RESET_COUNT = "resetCount";
        public static final String GAMETABLE_COLUMN_NAME_WORD_COUNT_ = "wordCount";
        public static final String GAMETABLE_COLUMN_NAME_BOARD_SIZE = "boardSize";
        public static final String GAMETABLE_COLUMN_NAME_MINUTES = "minutes";
        public static final String GAMETABLE_COLUMN_NAME_HIGHEST_WORD_SCORE = "highestWordScore";


        public static final String WORDTABLE_TABLE_NAME = "wordStats";
        public static final String WORDTABLE_COLUMN_NAME_WORD = "word";
        public static final String WORDTABLE_COLUMN_NAME_TIMES_PLAYED = "timesPlayed";


    }
}