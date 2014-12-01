package com.awesomekids.android.quickcharades;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class GameModeActivity extends ActionBarActivity {
    private Button gGoNextButton;
    private RadioGroup gModeGroup;
    private RadioButton gSingleButton;
    private RadioButton gFriendButton;
    private RadioButton gRandomButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);
        gGoNextButton = (Button) findViewById(R.id.mode_to_play_button);
        gModeGroup = (RadioGroup) findViewById(R.id.mode_button_group);
        gSingleButton = (RadioButton) findViewById(R.id.mode_single_button);
        gFriendButton = (RadioButton) findViewById(R.id.mode_friend_button);
        gRandomButton = (RadioButton) findViewById(R.id.mode_random_button);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_mode, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onGoNextButtonClick(View view){
        Intent goNextIntent = new Intent(this,GameActivity.class);
        if(gSingleButton.isSelected())
            goNextIntent.putExtra("MODE",Mode.SINGLE);
        else if(gFriendButton.isSelected())
            goNextIntent.putExtra("MODE",Mode.FRIEND);
        else if(gRandomButton.isSelected())
            goNextIntent.putExtra("MODE",Mode.RANDOM);
        else {}
        //Display error: Select a game mode before moving on
        final int result = 1;
        startActivity(goNextIntent);
    }
    public void onGameModeQuitButtonClick(View view){
        Intent goingBack = new Intent();
        setResult(RESULT_OK,goingBack);
        finish();
    }
}
