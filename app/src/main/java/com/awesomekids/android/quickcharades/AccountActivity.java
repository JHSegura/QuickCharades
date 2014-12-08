package com.awesomekids.android.quickcharades;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.*;
/**
 * Created by Josue on 12/7/2014.
 */
public class AccountActivity extends ActionBarActivity {

    EditText newUsername;
    TextView currentUsername, userHighscore, lowestScore, playedGames, bestStreak;
    Button mChangeName;
    String name, oldName;

    Context context = this;

    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        currentUsername = (TextView) findViewById(R.id.username);
        userHighscore = (TextView) findViewById(R.id.highscore);
        lowestScore = (TextView) findViewById(R.id.lowscore);
        bestStreak = (TextView) findViewById(R.id.bestStreak);
        playedGames = (TextView) findViewById(R.id.gamesPlayed);
        newUsername = (EditText) findViewById(R.id.newName);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("Username", "PLayer123");
        editor.putInt("Highscore", 0);
        editor.commit();

        editor.putInt("Highscore",200);
        editor.commit();
    }

    public void buttonOnClick (View v)
    {
        TextView currentUsername = (TextView) findViewById(R.id.username);
        name = newUsername.getText().toString();
        currentUsername.setText("Changed");
    }

    public void userNameChange (View view)
    {
        AccountDatabaseHandler db = new AccountDatabaseHandler(context);

        db.changeName(currentUsername.getText().toString(), newUsername.getText().toString());
    }

    public void getUsername (AccountDatabaseHandler db)
    {
        String name;

       // name = db.getAccountName();

        //return name;
    }
}