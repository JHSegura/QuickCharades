package com.awesomekids.android.quickcharades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tony Lim on 11/27/14.
 */
public class GameActivity extends Activity {


    private ImageView mImagePortrait;
    private TextView mAnswerTextView;
    private Button mEnterButton;
    private int mCurrentQuestion;
    private int mMaxQuestion;

    //TODO : find a way to store ad retrieve image from database
    private Integer[] mImageIds = { R.drawable.image_001, R.drawable.image_002, R.drawable.image_003};

    // TODO : find a way to store and retrive strings from database
    private String[] mImageNames = {
            "ironman",
            "avatar",
            "mario"
    };

    private ArrayList<Question> mGameQuestions;

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
        // TODO : Use information from category to generate different Questions into mGameQuestions

        loadAllQuestions(); // this will allocate and get resources for the game
                            // New Question object automatically generated randomized set of characters

        mCurrentQuestion = 0;
        mMaxQuestion = mImageIds.length;
        mImagePortrait = (ImageView) findViewById (R.id.game_imageView);
        mImagePortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mImagePortrait.setBackgroundResource();
                goToNextQuestion(v);
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

    private void loadAllQuestions() {
        mGameQuestions = new ArrayList<>();
        for (int i = 0; i < mImageNames.length; i++) {
            mGameQuestions.add(new Question(mImageIds[i], mImageNames[i]));
        }
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

    /**
     * This will generate random letters according to current question
     * Display the random letters and unhide them
     * @param v
     */
    public void setupAllLettersButton(View v) {
        // setting up the visibility
        ArrayList<Character> randomLetters = mGameQuestions
                .get(mCurrentQuestion)
                .getRandomizedLetters();

        //TODO : Debug to find out if the length of mGameQuestion, letterID, etc is the same with mMaxQuestion
        Log.d("Debugging actual answer", mGameQuestions.get(mCurrentQuestion).getAnswer());
        Log.d("Debugging random letters", randomLetters.toString());
        for (int i = 0; i < mMaxQuestion; i++) {
            ((Button) findViewById(sLettersButtonId[i]))
                    .setText(randomLetters.get(i));

            findViewById(sLettersButtonId[i]).setClickable(true);
            findViewById(sLettersButtonId[i]).setVisibility(v.VISIBLE);
        }
    }

    public void clearButton(View view) {
        mAnswerTextView.setText("");

        // TODO : Also reset the HashList
        // TODO : Reset the Array

        setupAllLettersButton(view);
    }

    public void goToNextQuestion(View view) {
        mCurrentQuestion = ++mCurrentQuestion % mMaxQuestion;
        mImagePortrait.setImageResource(mImageIds[mCurrentQuestion]);
        clearButton(view);
    }
}
