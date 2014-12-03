package com.awesomekids.android.quickcharades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Tony Lim on 11/27/14.
 */
public class GameActivity extends Activity {


    private ImageView mImagePortrait;
    private TextView mAnswerTextView;
    private Button mEnterButton;
    private int mCurrent;
    private int mMaxImage;
    static private Integer[] sImageIds = { R.drawable.image_001, R.drawable.image_002, R.drawable.image_003};
    static private Integer[] sLettersButtonId = {
            R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.button10, R.id.button11, R.id.button12, R.id.button13, R.id.button14
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_game);
        // To pass informatin from main screen
        Intent activityThatCalled = getIntent();
//        String previousActivity = activityThatCalled.getExtras().getString("callingActivity");

        mCurrent = 0;
        mMaxImage = sImageIds.length;
        mImagePortrait = (ImageView) findViewById (R.id.game_imageView);
        mImagePortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mImagePortrait.setBackgroundResource();
                mImagePortrait.setImageResource(sImageIds[++mCurrent % mMaxImage]);
            }
        });

        mAnswerTextView = (TextView) findViewById(R.id.game_answer_textview);
        mEnterButton    = (Button) findViewById(R.id.game_button_enter);
        mEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // TODO : Make a system to check whether the text input is equal to answer
                // TODO : make a Toast to let the user know whether the answer is correct
                // TODO : Implement a score system
                // TODO : If its correct, go to next question

            }
        });


    }

    public void letterClicked(View v){
        v.setClickable(false);
        v.setVisibility(v.INVISIBLE);

        // going to have to append from somewhere else besides extracting letters from layout
        Button b = (Button)v;
        String buttonLetter = b.getText().toString();

        mAnswerTextView.append(buttonLetter);
        //text.setText(buttonLetter);
    }

    public void onGameQuitButtonClick(View view) {

        Intent goingBack = new Intent();
//        goingBack.putExtra("some key", value);

        setResult(RESULT_OK,goingBack);
        finish();
    }



    public void onGameOptionButtonClick(View view) {
        Intent getSettingsIntent = new Intent(this,
                SettingsActivity.class);
        final int result = 1; //result id

        startActivity(getSettingsIntent);
    }

    public void setupAllLettersButton(View v) {
        // setting up the visibility
        for (Integer letterId : sLettersButtonId) {
            findViewById(letterId).setClickable(true);
            findViewById(letterId).setVisibility(v.VISIBLE);
        }
        // implement randomize here later
    }

    public void clearButton(View view) {
        mAnswerTextView.setText("");

        // TODO : Also reset the HashList
        // TODO : Reset the Array

        setupAllLettersButton(view);
    }

}
