package com.awesomekids.android.quickcharades;

import android.provider.BaseColumns;

/**
 * Created by Josue on 12/7/2014.
 */
public class TableData {

    public TableData()
    {

    }

    public static abstract class TableInfo implements BaseColumns
    {
        public static final String USER_NAME = "user_name";
        public static final String HIGH_SCORE = "high_score";
        public static final String LOWEST_SCORE = "lowest_score";
        public static final String GAMES_PLAYED = "games_played";

        public static final String DATABASE_NAME = "account_info";
        public static final String TABLE_NAME = "account";
    }
}
