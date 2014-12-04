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
    private TextView modeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);
        //get info from game activity
        //load them into variables here:
        int score = 155555;
        double time = 222.24;
        int qAnswered = 10; //get rid of these initial values when we get the info
        int qTotal = 5;
        int streak = 777;

        Difficulty diff = Difficulty.EASY;
        Mode mode = Mode.SINGLE;
        Length length = Length.SHORT;
        QCategory cat = QCategory.HEROES;
        //Perhaps add length difficulty, category,
        TextView scoreView = (TextView) findViewById(R.id.text_score_value);
        TextView timeView = (TextView) findViewById(R.id.text_total_time_value);
        TextView qView = (TextView) findViewById(R.id.text_question_value);
        TextView streakView = (TextView) findViewById(R.id.text_streak_value);
        scoreView.setText(""+score);
        timeView.setText(""+time);
        qView.setText(qAnswered+"/"+qTotal);
        streakView.setText(""+streak);
        updatePlayerInfo(score,time,qAnswered,qTotal,streak);
        diffView = (TextView) findViewById(R.id.text_diff_value);
        lengthView = (TextView) findViewById(R.id.text_length_value);
        catView = (TextView) findViewById(R.id.text_category_value);
        modeView = (TextView) findViewById(R.id.text_mode_value);

        diffView.setText(diff.getValue());
        lengthView.setText(length.getValue());
        catView.setText(cat.getValue());
        modeView.setText(mode.getValue());
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
    public void updatePlayerInfo(int score, double time, int answered, int total, int streak){

    }

}
