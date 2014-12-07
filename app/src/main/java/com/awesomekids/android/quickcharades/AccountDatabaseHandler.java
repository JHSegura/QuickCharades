package com.awesomekids.android.quickcharades;

/**
 * Created by Josue on 12/6/2014.
 */

import android.database.sqlite.*;
import android.content.*;
import android.database.*;

public class AccountDatabaseHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userAccount.db";
    private static final String TABLE_PRODUCTS = "accountInfo";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ACCOUNT_NAME = "accountName";
    public static final String COLUMN_SCORES = "scores";
    public static final String COLUMN_GAME_COUNT = "totalGames";

    public AccountDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
       super (context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try {
            String CREATE_ACCOUNT_TABLE = "CREATE TABLE" + TABLE_PRODUCTS + "("
                    + COLUMN_ID + "INTEGER PRIMARY KEY," + COLUMN_ACCOUNT_NAME + "TEXT,"
                    + COLUMN_SCORES + " INTEGER," + COLUMN_GAME_COUNT + " INTEGER" + ")";
            db.execSQL(CREATE_ACCOUNT_TABLE);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public boolean changeName(String currentName, String newName) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_ACCOUNT_NAME + " =  \"" + currentName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Account user = new Account();

        if (cursor.moveToFirst()) {
            user.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(user.getId()) });
            cursor.close();
            result = true;
        }
        db.close();

        user.setAccountName(newName);
        addName(user);

        return result;
    }

    public void addName(Account user) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_ACCOUNT_NAME, user.getAccountName());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }
}
