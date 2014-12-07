package com.awesomekids.android.quickcharades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {


    private Button mPlayButton, mSettingButton, mFriendsButton, mLeaderBoardsButton, mHowToButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //If these buttons won't be used elsewhere in this activity, better to make them local variables instead of instance variables
        mPlayButton = (Button) findViewById(R.id.play_button);
        mSettingButton = (Button) findViewById(R.id.setting_button);
        mFriendsButton = (Button) findViewById(R.id.friends_button);
        mLeaderBoardsButton = (Button) findViewById(R.id.leaderboards_button);
        mHowToButton = (Button) findViewById(R.id.howto_button);
    }

    public void onFriendsButtonClick(View view) {
//        Intent getFriendsListIntent = new Intent(this,FriendActivity.class);
//        final int result = 1; //what is this for
//        startActivity(getFriendsListIntent);
        Toast.makeText(getBaseContext(), "Friends COMING SOON", Toast.LENGTH_SHORT).show();
    }

    public void onPlayButtonClick(View view) {
        Intent getGameIntent = new Intent(this,
                GameSetupActivity.class);
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
//        Intent getLeaderboardIntent = new Intent(this,LeaderboardActivity.class);
//        final int result = 1;
//        startActivity(getLeaderboardIntent);
        Toast.makeText(getBaseContext(), "LeaderBoards COMING SOON", Toast.LENGTH_SHORT).show();
    }

    public void onHowToButtonClick(View view){
        Toast.makeText(getBaseContext(), "Tutorial COMING SOON", Toast.LENGTH_SHORT).show();
    }
}
