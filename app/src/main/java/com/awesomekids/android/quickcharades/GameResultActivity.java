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

    private int score, time, qAnswered, qTotal, streak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);

        //get info from game activity
        time = getIntent().getIntExtra(GameActivity.KEY_TOTALTIME, 0); // by default its zero
        qTotal = getIntent().getIntExtra(GameActivity.KEY_TOTALQUESTION, 0); // by default its zero

        // get info from player
        try {
            loadPlayerInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Difficulty diff = Difficulty.EASY;
        Mode mode = Mode.SINGLE;
        Length length = Length.SHORT;
        QCategory cat = QCategory.HEROES;
        //Maybe make these instance vars
        TextView scoreView = (TextView) findViewById(R.id.text_view_score);
        TextView timeView = (TextView) findViewById(R.id.text_view_time_elapsed);
        TextView qView = (TextView) findViewById(R.id.text_view_question);
        TextView streakView = (TextView) findViewById(R.id.text_view_streak);

        scoreView.setText("Score: "+score);
        timeView.setText("Total Time: "+time);
        qView.setText("Correct Questions: "+qAnswered+"/"+qTotal);
        streakView.setText("Max Streak: "+streak);

        diffView = (TextView) findViewById(R.id.text_view_diff);
        lengthView = (TextView) findViewById(R.id.text_view_length);
        catView = (TextView) findViewById(R.id.text_view_category);

        diffView.setText("Difficulty: "+diff.getValue());
        lengthView.setText("Length: "+length.getValue());
        catView.setText("Category: "+cat.getValue());
        playAgainButton = (Button) findViewById(R.id.play_again_button);
        goMainMenuButton = (Button) findViewById(R.id.from_result_to_menu_button);
    }

    public void onPlayAgainButtonClick(View view){
        Intent playAgainIntent = new Intent(this,GameActivity.class);
        //Get game mode info from textviews (Of Difficulty, Length, Category, Mode)
        startActivity(playAgainIntent);
    }
    public void onGoMainMenuButtonClick(View view){
        Intent goMainMenuIntent = new Intent(this,MainActivity.class);
        startActivity(goMainMenuIntent);
    }


    public void loadPlayerInfo() {
        score = GameActivity.mPlayer.currentScore;
        qAnswered = GameActivity.mPlayer.currentQanswered;
        streak = GameActivity.mPlayer.currentStreak;
    }
}
