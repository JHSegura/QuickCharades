package com.awesomekids.android.quickcharades;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {


    private Button mPlayButton, mSettingButton, mFriendsButton, mLeaderBoardsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayButton = (Button) findViewById(R.id.play_button);
        mSettingButton = (Button) findViewById(R.id.setting_button);
        mFriendsButton = (Button) findViewById(R.id.friends_button);
        mLeaderBoardsButton = (Button) findViewById(R.id.leaderboards_button);

    }

    public void onFriendsButtonClick(View view) {
    }

    public void onPlayButtonClick(View view) {
        Intent getGameIntent = new Intent(this,
                GameActivity.class);
        final int result = 1;
        startActivityForResult(getGameIntent, result); //GeoQuiz use 0 as default
    }

    public void onSettingButtonClick(View view) {
        Intent getSettingsIntent = new Intent(this,
                SettingsActivity.class);
        final int result = 1; //result id

        startActivity(getSettingsIntent);
        // Later on will have settings activity pass back some information, but for now, none.
//        startActivityForResult(getNameScreenIntent, result); // expect to send data back
    }

    public void onLeaderboardsButtonClick(View view) {
    }
}
