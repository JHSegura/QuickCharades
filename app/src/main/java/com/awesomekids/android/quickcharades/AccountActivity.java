package com.awesomekids.android.quickcharades;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.*;
/**
 * Created by Josue on 12/7/2014.
 */
public class AccountActivity extends ActionBarActivity {

    EditText currentUsername, newUsername, userHighscore, lowestScore, playedGames;

    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        currentUsername = (EditText) findViewById(R.id.username);
        userHighscore = (EditText) findViewById(R.id.highscore);
        lowestScore = (EditText) findViewById(R.id.lowscore);
        playedGames = (EditText) findViewById(R.id.gamesPlayed);
        newUsername = (EditText) findViewById(R.id.newName);
    }

    public void userNameChange (View view)
    {
        AccountDatabaseHandler db = new AccountDatabaseHandler(this, null, null, 1);

        db.changeName(currentUsername.getText().toString(), newUsername.getText().toString());
    }
}