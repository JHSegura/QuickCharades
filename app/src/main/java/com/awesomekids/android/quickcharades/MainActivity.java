package com.awesomekids.android.quickcharades;

import android.app.Activity;
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
    }

    public void onSettingButtonClick(View view) {
    }

    public void onLeaderboardsButtonClick(View view) {
    }
}
