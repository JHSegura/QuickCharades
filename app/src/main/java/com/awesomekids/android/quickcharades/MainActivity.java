package com.awesomekids.android.quickcharades;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
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
        addButtonEffectOn(mPlayButton);
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

    /**
     * A function to add the clicking effects to button
     * by : http://stackoverflow.com/users/1696653/andr%C3%A1s
     * at : http://stackoverflow.com/questions/7175873/click-effect-on-button-in-android
     * alternatively, use : style="?android:attr/buttonStyleSmall"
     * @param button
     */
    public static void addButtonEffectOn(View button){
        button.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }
}
