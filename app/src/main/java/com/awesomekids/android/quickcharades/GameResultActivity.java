package com.awesomekids.android.quickcharades;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class GameResultActivity extends ActionBarActivity {

    private Button playAgainButton;
    private Button goMainMenuButton;
    private TextView diffView;
    private TextView lengthView;
    private TextView catView;

    private Button mPlayAgainButton;
    private Button mMainMenuButton;

    private int score, time, qAnswered, qTotal, streak;
    private String rCat, rDiff, rLen, rMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);

        //get info from game activity
        time = getIntent().getIntExtra(GameActivity.KEY_TOTALTIME, 0); // by default its zero
        qTotal = getIntent().getIntExtra(GameActivity.KEY_TOTALQUESTION, 0); // by default its zero
        rCat = getIntent().getStringExtra(GameSetupActivity.KEY_CAT);
        rLen = getIntent().getStringExtra(GameSetupActivity.KEY_LEN);
        rDiff = getIntent().getStringExtra(GameSetupActivity.KEY_DIFF);
        rMode = getIntent().getStringExtra(GameSetupActivity.KEY_MODE);
        // get info from player
        try {
            loadPlayerInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView scoreView = (TextView) findViewById(R.id.text_view_score);
        TextView timeView = (TextView) findViewById(R.id.text_view_time_elapsed);
        TextView qView = (TextView) findViewById(R.id.text_view_question);
        TextView streakView = (TextView) findViewById(R.id.text_view_streak);

        mPlayAgainButton = (Button) findViewById(R.id.button_play_again);
        mPlayAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //How to send info to game from here?
                GameActivity.mPlayer = null;
                setResult(1);
                finish();
            }
        });
        MainActivity.addButtonEffectOn(mPlayAgainButton);

        mMainMenuButton = (Button) findViewById(R.id.button_return_to_menu);
        mMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameActivity.mPlayer = null; // java does garbage collection automatically
                setResult(0);
                finish();
            }
        });
        MainActivity.addButtonEffectOn(mMainMenuButton);

        scoreView.setText("Score: "+score);
        timeView.setText("Total Time: "+time);
        qView.setText("Correct Questions: "+qAnswered+"/"+qTotal);
        streakView.setText("Max Streak: "+streak);

        diffView = (TextView) findViewById(R.id.text_view_diff);
        lengthView = (TextView) findViewById(R.id.text_view_length);
        catView = (TextView) findViewById(R.id.text_view_category);

        diffView.setText("Difficulty: "+rDiff);
        lengthView.setText("Length: "+rLen);
        catView.setText("Category: "+rCat);
    }


    public void loadPlayerInfo() {
        score = GameActivity.mPlayer.currentScore;
        qAnswered = GameActivity.mPlayer.currentQanswered;
        streak = GameActivity.mPlayer.getStats().maxStreak;
    }
}
