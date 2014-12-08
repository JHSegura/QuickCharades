package com.awesomekids.android.quickcharades;

/**
 * Created by Josue on 12/6/2014.
 */

import android.database.sqlite.*;
import android.content.*;
import android.database.*;
import android.util.Log;

public class AccountDatabaseHandler extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public String CREATE_QUERY = "CREATE TABLE "+TableData.TableInfo.TABLE_NAME+"("+TableData.TableInfo.USER_NAME+" TEXT,"+TableData.TableInfo.HIGH_SCORE
            + " INT,"+ TableData.TableInfo.LOWEST_SCORE+" INT,"+ TableData.TableInfo.GAMES_PLAYED+" INT);";

    public AccountDatabaseHandler(Context context)
    {
       super(context, TableData.TableInfo.DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database Operations", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_QUERY);
        Log.d("Database Operations", "Table Created");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TableData.TableInfo.TABLE_NAME);
        onCreate(db);
    }

    public boolean changeName(String currentName, String newName) {

        boolean result = false;
        String query = "Select * FROM " + TableData.TableInfo.TABLE_NAME + " WHERE " + TableData.TableInfo.USER_NAME + " =  \"" + currentName + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Account user = new Account();

        if (cursor.moveToFirst()) {
            user.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TableData.TableInfo.TABLE_NAME, TableData.TableInfo.USER_NAME + " = ?",
                    new String[]{String.valueOf(user.getId())});
            cursor.close();
            result = true;
        }

        user.setAccountName(newName);
        addName(this, user.getAccountName());

        return result;
    }

    public void addName(AccountDatabaseHandler db, String name) {

        SQLiteDatabase SQ = db.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TableData.TableInfo.USER_NAME, name);

        long a = SQ.insert(TableData.TableInfo.TABLE_NAME, null, values);

        Log.d("Database Operations", "One Row Inserted");
    }

    public void addHighScore(AccountDatabaseHandler db, int highScore) {

        SQLiteDatabase SQ = db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TableData.TableInfo.HIGH_SCORE, highScore);

        SQ.insert(TableData.TableInfo.TABLE_NAME, null, values);
    }

    public void getAccountName (AccountDatabaseHandler db)
    {
        SQLiteDatabase SQ = db.getReadableDatabase();

    }
}
